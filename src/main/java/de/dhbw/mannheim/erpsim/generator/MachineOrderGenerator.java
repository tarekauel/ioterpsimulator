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

package de.dhbw.mannheim.erpsim.generator;

import de.dhbw.mannheim.erpsim.model.MachineOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author Tarek Auel
 * @since 08.04.2015
 */
public class MachineOrderGenerator {

    private final static HashMap<Integer, String> machineUuid = new HashMap<>();

    private final static ArrayList<MachineOrder> generatedMachineOrder = new ArrayList<>();

    public static MachineOrder getMachineOrder() {
        String uuid = UUID.randomUUID().toString();

        String machine = getMachineName(1);

        double plannedSeconds = 25 + 2 * Math.random();

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
