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
    TransmitSchedule transmitSchedule;

    public Transmitter() {}

    public Transmitter (String nm,double freq,TransmitMode mode, TransmitSchedule txSchedule) {
        name = nm;
        frequency = freq;
        this.mode = mode;
        transmitSchedule = txSchedule;
    }


}
