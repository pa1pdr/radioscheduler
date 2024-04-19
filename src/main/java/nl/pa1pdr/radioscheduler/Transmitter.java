package nl.pa1pdr.radioscheduler;


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

    @ManyToOne
    ReceptionCommand command;

    public Transmitter() {}

    public Transmitter (Station station, String name,double freq,TransmitMode mode, ReceptionCommand command) {
        this.name = name;
        this.frequency = freq;
        this.mode = mode;
        this.station = station;
        this.command = command;
    }


}
