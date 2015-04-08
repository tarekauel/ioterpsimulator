/*
 * Copyright (c) 2015 Tarek Auel
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package de.dhbw.mannheim.erpsim;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import de.dhbw.mannheim.erpsim.generator.CustomerOrderGenerator;
import de.dhbw.mannheim.erpsim.generator.MachineOrderGenerator;
import de.dhbw.mannheim.erpsim.model.CustomerOrder;
import de.dhbw.mannheim.erpsim.model.MachineOrder;

import java.io.IOException;


/**
 * @author Tarek Auel
 * @since 08.04.2015
 */
public class ErpSimulator {

    public static final String CUSTOMER_ORDER_EXCHANGE_NAME = "CUSTOMER_ORDER_QUEUE";
    public static final String MACHINE_ORDER_EXCHANGE_NAME = "MACHINE_ORDER_QUEUE";

    /**
     *
     *
     * @param args command line parameter
     *             args[0] number of customer orders that should be created
     *             args[1] hostname of rabbitMQ
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        int numOfCustomerOrder = 10;
        String rabbitMqHostName = "localhost";
        String rabbitMqUserName = null;
        String rabbitMqPassword = null;

        if (args.length >= 1) {
            try {
                numOfCustomerOrder = Integer.parseInt(args[0]);
                System.out.println("Number of customer orders: " + numOfCustomerOrder);
            } catch (Exception e) {
                System.err.println("Could not parse number of customer orders " + args[0]);
            }
        }

        if (args.length >= 2 && args[1] != null) {
            rabbitMqHostName = args[1];
            System.out.println("Host of rabbitMq: " + rabbitMqHostName);
        }
        if(args.length >= 4 && args[2] != null && args[3] != null){
            rabbitMqUserName = args[2];
            rabbitMqPassword = args[3];
            System.out.println("Username of rabbitMq: " + rabbitMqUserName);
        }


        CustomerOrder[] customerOrders = new CustomerOrder[numOfCustomerOrder];

        for (int i = 0; i < customerOrders.length; i++) {
            customerOrders[i] = CustomerOrderGenerator.getCustomOrder();
        }

        XStream xstream = new XStream();
        xstream.registerConverter(new Converter() {
            @Override
            public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext marshallingContext) {
                MachineOrder mo = (MachineOrder) o;
                writer.startNode("id");
                writer.setValue(mo.getId());
                writer.endNode();
            }

            @Override
            public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
                return null;
            }

            @Override
            public boolean canConvert(Class aClass) {
                return aClass == MachineOrder.class;
            }
        });

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(rabbitMqHostName);
        if(rabbitMqPassword != null && rabbitMqUserName != null){
            factory.setUsername(rabbitMqUserName);
            factory.setPassword(rabbitMqPassword);
        }
        Connection connection = factory.newConnection();
        Channel channelCO = connection.createChannel();
        channelCO.exchangeDeclare(CUSTOMER_ORDER_EXCHANGE_NAME, "fanout");
        for (CustomerOrder co : customerOrders) {
            String message = xstream.toXML(co);

            channelCO.basicPublish(CUSTOMER_ORDER_EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println("Send customer order");
        }
        channelCO.close();

        Channel channelMO = connection.createChannel();
        channelMO.exchangeDeclare(MACHINE_ORDER_EXCHANGE_NAME, "fanout");
        MachineOrder mo = MachineOrderGenerator.getRandomMachineOrder();

        xstream = new XStream(); // reconstruct XStream to parse the full machine order, not just only the id
        while (mo != null) {
            int i = System.in.read();
            String message = xstream.toXML(mo);
            channelMO.basicPublish(MACHINE_ORDER_EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println("Send Machine order");
            mo = MachineOrderGenerator.getRandomMachineOrder();
        }

        channelMO.close();
        connection.close();
    }
}
