package de.dhbw.mannheim.erpsim;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.thoughtworks.xstream.XStream;
import de.dhbw.mannheim.erpsim.generator.CustomerOrderGenerator;
import de.dhbw.mannheim.erpsim.generator.MachineOrderGenerator;
import de.dhbw.mannheim.erpsim.model.CustomerOrder;
import de.dhbw.mannheim.erpsim.model.MachineOrder;

import java.io.IOException;


/**
 * Created by tarek on 08.04.15.
 */
public class ErpSimulator {

    public static final String CUSTOMER_ORDER_QUEUE_NAME = "CUSTOMER_ORDER_QUEUE";
    public static final String MACHINE_ORDER_QUEUE_NAME = "MACHINE_ORDER_QUEUE";


    public static void main(String[] args) throws IOException {

        int numOfCustomerOrder = 10;
        String rabbitMqHostName = "localhost";

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


        CustomerOrder[] customerOrders = new CustomerOrder[numOfCustomerOrder];

        for (int i = 0; i < customerOrders.length; i++) {
            customerOrders[i] = CustomerOrderGenerator.getCustomOrder();
        }

        XStream xstream = new XStream();
        //System.out.println(xstream.toXML(customerOrders));

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(rabbitMqHostName);
        Connection connection = factory.newConnection();
        Channel channelCO = connection.createChannel();
        channelCO.queueDeclare(CUSTOMER_ORDER_QUEUE_NAME, false, false, false, null);
        for (CustomerOrder co : customerOrders) {
            String message = xstream.toXML(co);
            channelCO.basicPublish("", CUSTOMER_ORDER_QUEUE_NAME, null, message.getBytes());
            System.out.println("Send customer order");
        }
        channelCO.close();

        Channel channelMO = connection.createChannel();
        channelMO.queueDeclare(MACHINE_ORDER_QUEUE_NAME, false, false, false, null);
        MachineOrder mo = MachineOrderGenerator.getRandomMachineOrder();

        while (mo != null) {
            System.in.read();
            String message = xstream.toXML(mo);
            channelMO.basicPublish("", MACHINE_ORDER_QUEUE_NAME, null, message.getBytes());
            System.out.println("Send Machine order");
            mo = MachineOrderGenerator.getRandomMachineOrder();
        }

        channelMO.close();
        connection.close();

    }
}
