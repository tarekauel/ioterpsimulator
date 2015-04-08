package de.dhbw.mannheim.erpsim.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by tarek on 08.04.15.
 */
@XmlRootElement
public class CustomerOrder {

    private String customerFirstName;

    private String customerLastName;

    private String customerNumber;

    private long orderNumber;

    private OrderPosition[] positions;

    public CustomerOrder() {
    }

    public CustomerOrder(String customerFirstName, String customerLastName, String customerNumber, long orderNumber, OrderPosition[] positions) {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerNumber = customerNumber;
        this.orderNumber = orderNumber;
        this.positions = positions;
    }
}
