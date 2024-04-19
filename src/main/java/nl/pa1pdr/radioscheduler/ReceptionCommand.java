package nl.pa1pdr.radioscheduler;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class ReceptionCommand extends AbstractEntity {

   
    String name;
    String commandline;

    @OneToMany (mappedBy = "command")
    private final List<Transmitter> transmitter = new ArrayList<>();


    public ReceptionCommand() {}

    public ReceptionCommand (String name,String command) {
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
