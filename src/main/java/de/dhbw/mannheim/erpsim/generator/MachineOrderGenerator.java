package de.dhbw.mannheim.erpsim.generator;

import de.dhbw.mannheim.erpsim.model.MachineOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by tarek on 08.04.15.
 */
public class MachineOrderGenerator {

    private static HashMap<Integer, String> machineUuid = new HashMap<>();

    private static ArrayList<MachineOrder> generatedMachineOrder = new ArrayList<>();

    public static MachineOrder getMachineOrder() {
        String uuid = UUID.randomUUID().toString();

        String machine = getMachineName(1);

        double plannedSeconds = 8 + 2 * Math.random();

        double speedShaperRPM = 10 + 10 * Math.random();

        double speedDrillerRPM = 10 + 10 * Math.random();

        MachineOrder mo =  new MachineOrder(uuid, machine, plannedSeconds, speedShaperRPM, speedDrillerRPM);
        generatedMachineOrder.add(mo);

        return mo;
    }

    public static String getMachineName(int number) {
        String uuid = machineUuid.get(number);
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
            machineUuid.put(number, uuid);
        }
        return uuid;
    }

    public static MachineOrder getRandomMachineOrder() {
        if (generatedMachineOrder.size() == 0) return null;
        int i = (int) Math.ceil(Math.random() * (generatedMachineOrder.size() - 1));

        return generatedMachineOrder.remove(i);
    }

}
