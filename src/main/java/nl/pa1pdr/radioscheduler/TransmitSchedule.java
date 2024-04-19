package nl.pa1pdr.radioscheduler;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;


@Entity
public class TransmitSchedule extends AbstractEntity {
    String name;
  
    Time timeOfDay;
    int repeatinterval = 24;   // in hours
    int duration;         // in minutes


    @OneToMany(mappedBy = "transmitSchedule")
    private final List<Transmitter> transmitters = new ArrayList<>();

    public TransmitSchedule () {}

    public TransmitSchedule (String name, LocalTime when, int length) {
        this.name=name;
        timeOfDay = Time.valueOf(when);
        this.duration=length;
    }


}
