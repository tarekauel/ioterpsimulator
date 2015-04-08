package de.dhbw.mannheim.erpsim.generator;

import de.dhbw.mannheim.erpsim.model.MachineOrder;
import de.dhbw.mannheim.erpsim.model.OrderPosition;
import de.dhbw.mannheim.erpsim.model.Workpiece;

/**
 * Created by tarek on 08.04.15.
 */
public class OrderPositionGenerator {

    public static OrderPosition getOrderPosition() {

        int pieces = (int) (Math.random() * 2) + 1;

        Workpiece position = getRandomWorkpiece();

        MachineOrder[] machineOrders = new MachineOrder[pieces];

        for (int i = 0; i < machineOrders.length; i++) {
            machineOrders[i] = MachineOrderGenerator.getMachineOrder();
        }

        return new OrderPosition(pieces, position, machineOrders);
    }


    private static Workpiece getRandomWorkpiece() {
        return WorkpieceGenerator.getRandomWorkpiece();
    }
}
