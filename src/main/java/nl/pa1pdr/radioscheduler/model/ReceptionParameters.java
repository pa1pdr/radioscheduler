package nl.pa1pdr.radioscheduler.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ReceptionParameters extends AbstractEntity {

   
    String name;
    String commandline;

    @OneToMany (mappedBy = "receptionstyle")
    private final List<Transmitter> transmitter = new ArrayList<>();

    @ManyToOne
    private RadioTuner radio;

    public ReceptionParameters() {}

    public ReceptionParameters (String name,String command) {
        this.name = name;
        this.commandline = command;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommandline() {
        return commandline;
    }

    public void setCommandline(String commandline) {
        this.commandline = commandline;
    }

    public List<Transmitter> getTransmitter() {
        return transmitter;
    }


}
