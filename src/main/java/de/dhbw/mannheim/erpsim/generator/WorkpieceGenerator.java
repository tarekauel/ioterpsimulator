package de.dhbw.mannheim.erpsim.generator;

import de.dhbw.mannheim.erpsim.model.Workpiece;

import java.util.ArrayList;

/**
 * Created by Lars on 08.04.2015.
 */

public class WorkpieceGenerator {
    private static ArrayList<Workpiece> listOfWorkpieces = new ArrayList<>();

    static {
        listOfWorkpieces.add(new Workpiece("Lenkrad"));
        listOfWorkpieces.add(new Workpiece("Bremse"));
        listOfWorkpieces.add(new Workpiece("Motorhaube"));
        listOfWorkpieces.add(new Workpiece("Tankdeckel"));
        listOfWorkpieces.add(new Workpiece("Felge"));
        listOfWorkpieces.add(new Workpiece("Radio"));
    }

    public static Workpiece getRandomWorkpiece(){
        return listOfWorkpieces.get((int)(Math.ceil(Math.random()*(listOfWorkpieces.size()-1))));
    }

    public static int getNumberOfPieces(){
        return listOfWorkpieces.size();
    }
}
