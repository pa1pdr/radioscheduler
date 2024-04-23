package nl.pa1pdr.radioscheduler.model;

import java.sql.Time;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;


@Entity
public class TransmitSchedule extends AbstractEntity {
    private String name;
  
    private Time timeOfDay;
    int repeatinterval = 24;   // in hours
    int duration;         // in minutes
    boolean isEnabled = false;
    String validity = "";

    @ManyToOne
    Station station;


    public TransmitSchedule () {}

    public TransmitSchedule (Station station, String name, LocalTime when, int length) {
        this.name=name;
        timeOfDay = Time.valueOf(when);
        this.duration=length;
        this.station = station;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getTimeOfDay() {
        return timeOfDay.toLocalTime();
    }

    public void setTimeOfDay(LocalTime timeOfDay) {
        this.timeOfDay = Time.valueOf(timeOfDay);
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
