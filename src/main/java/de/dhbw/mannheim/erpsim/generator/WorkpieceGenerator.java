package de.dhbw.mannheim.erpsim.generator;

import de.dhbw.mannheim.erpsim.model.Workpiece;

import java.util.ArrayList;

/**
 * Created by Lars on 08.04.2015.
 */

public class WorkpieceGenerator {
    private static ArrayList<Workpiece> listOfWorkpieces = new ArrayList<>();
    static {
        //Workpiece(String name, double speedDrillerRPM, double speedShaperRPM, double plannedSeconds);
        listOfWorkpieces.add(new Workpiece("Lenkrad",15.0,10.0,25.0));
        listOfWorkpieces.add(new Workpiece("Bremse",25.0,10.0,25.0));
        listOfWorkpieces.add(new Workpiece("Motorhaube",10.0,10.0,25.0));
        listOfWorkpieces.add(new Workpiece("Tankdeckel",15.0,25.0,25.0));
        listOfWorkpieces.add(new Workpiece("Felge",10.0,25.0,25.0));
        listOfWorkpieces.add(new Workpiece("Radio",5.0,1.0,25.0));
    }
    public static Workpiece getRandomWorkpiece(){
        return listOfWorkpieces.get((int)(Math.ceil(Math.random()*(listOfWorkpieces.size()-1))));
    }
    public static int getNumberOfPieces(){
        return listOfWorkpieces.size();
    }
}
