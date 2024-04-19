package nl.pa1pdr.radioscheduler;

import java.sql.Time;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;


@Entity
public class TransmitSchedule extends AbstractEntity {
    String name;
  
    Time timeOfDay;
    int repeatinterval = 24;   // in hours
    int duration;         // in minutes

    @ManyToOne
    Station station;

    public TransmitSchedule () {}

    public TransmitSchedule (Station station, String name, LocalTime when, int length) {
        this.name=name;
        timeOfDay = Time.valueOf(when);
        this.duration=length;
        this.station = station;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(Time timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public int getRepeatinterval() {
        return repeatinterval;
    }

    public void setRepeatinterval(int repeatinterval) {
        this.repeatinterval = repeatinterval;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }


}
