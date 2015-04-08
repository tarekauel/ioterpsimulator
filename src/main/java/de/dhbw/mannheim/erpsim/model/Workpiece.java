package de.dhbw.mannheim.erpsim.model;

/**
 * Created by Lars on 08.04.2015.
 */
public class Workpiece {
    private String name;
    private double speedDrillerRPM;
    private double speedShaperRPM;
    private double plannedSeconds;
    public Workpiece(){}

    public Workpiece(String name, double speedDrillerRPM, double speedShaperRPM, double plannedSeconds) {
        this.name = name;
        this.speedDrillerRPM = speedDrillerRPM;
        this.speedShaperRPM = speedShaperRPM;
        this.plannedSeconds = plannedSeconds;
    }

}
