package de.dhbw.mannheim.erpsim.model;

/**
 * Created by tarek on 08.04.15.
 */
public class OrderPosition {

    private int pieces;

    private String positionName;

    private MachineOrder[] machineOrders;

    public OrderPosition() {
    }

    public OrderPosition(int pieces, String positionName, MachineOrder[] machineOrders) {
        this.pieces = pieces;
        this.positionName = positionName;
        this.machineOrders = machineOrders;
    }
}
