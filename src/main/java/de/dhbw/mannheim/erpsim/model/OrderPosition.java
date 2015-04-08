package de.dhbw.mannheim.erpsim.model;

/**
 * Created by tarek on 08.04.15.
 */
public class OrderPosition {

    private int pieces;

    private Workpiece position;

    private MachineOrder[] machineOrders;

    public OrderPosition() {
    }

    public OrderPosition(int pieces, Workpiece position, MachineOrder[] machineOrders) {
        this.pieces = pieces;
        this.position = position;
        this.machineOrders = machineOrders;
    }
}
