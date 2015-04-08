package de.dhbw.mannheim.erpsim.generator;

import de.dhbw.mannheim.erpsim.model.MachineOrder;
import de.dhbw.mannheim.erpsim.model.OrderPosition;

/**
 * Created by tarek on 08.04.15.
 */
public class OrderPositionGenerator {

    private static String[] positionNames = new String[] {"Motorhaube","Felge","Reifen","Lenkrad","Radio"};

    public static OrderPosition getOrderPosition() {

        int pieces = (int) (Math.random() * 2) + 1;

        String positionName = getPositionName((int) (Math.random() * positionNames.length));

        MachineOrder[] machineOrders = new MachineOrder[pieces];

        for (int i=0; i < machineOrders.length; i++) {
            machineOrders[i] = MachineOrderGenerator.getMachineOrder();
        }

        return new OrderPosition(pieces,positionName,machineOrders);
    }

    public static String getPositionName(int i) {
        return positionNames[i];
    }
}
