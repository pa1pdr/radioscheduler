package nl.pa1pdr.radioscheduler;

import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;


@Entity
public class Station extends AbstractEntity {
    String name;
    String location;



    @OneToMany(mappedBy = "station")
    private final List<Transmitter> transmitters = new ArrayList<>();

    @OneToMany(mappedBy = "station")
    private final List<TransmitSchedule> schedules = new ArrayList<>();


    public Station () {}

    public Station (String name, String location) {
        this.name=name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Transmitter> getTransmitters() {
        return transmitters;
    }

    public List<TransmitSchedule> getSchedules() {
        return schedules;
    }


}
