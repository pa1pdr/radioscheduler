package nl.pa1pdr.radioscheduler.model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Transmitter extends AbstractEntity {

    double frequency;
    double power = 0.0;
    TransmitMode mode;
    String name;

    @ManyToOne
    Station station;

    @ManyToOne (optional = true)
    ReceptionParameters receptionstyle;

    public Transmitter() {}

    public Transmitter (Station station, String name,double freq,TransmitMode mode, ReceptionParameters command) {
        this.name = name;
        this.frequency = freq;
        this.mode = mode;
        this.station = station;
        this.receptionstyle = command;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public TransmitMode getMode() {
        return mode;
    }

    public void setMode(TransmitMode mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public ReceptionParameters getReceptionstyle() {
        return receptionstyle;
    }

    public void setReceptionstyle(ReceptionParameters param) {
        this.receptionstyle = param;
    }

    



}
