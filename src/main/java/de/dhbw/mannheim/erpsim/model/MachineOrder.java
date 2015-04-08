package de.dhbw.mannheim.erpsim.model;

/**
 * Created by tarek on 08.04.15.
 */
public class MachineOrder {

    private String id;

    private String machine;

    private double plannedSeconds;

    private double speedShaperRPM;

    private double speedDrillerRPM;

    public MachineOrder() {
    }

    public MachineOrder(String id, String machine, double plannedSeconds, double speedShaperRPM, double speedDrillerRPM) {
        this.id = id;
        this.machine = machine;
        this.plannedSeconds = plannedSeconds;
        this.speedShaperRPM = speedShaperRPM;
        this.speedDrillerRPM = speedDrillerRPM;
    }
}
