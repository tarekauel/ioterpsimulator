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
import de.dhbw.mannheim.erpsim.model.OrderPosition;
import de.dhbw.mannheim.erpsim.model.Workpiece;

/**
 * @author Tarek Auel
 * @since 08.04.2015
 */
public class OrderPositionGenerator {

    private static String[] positionNames = new String[] {"Motorhaube","Felge","Reifen","Lenkrad","Radio"};

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
