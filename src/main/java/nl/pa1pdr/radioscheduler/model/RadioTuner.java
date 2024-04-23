package nl.pa1pdr.radioscheduler.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class RadioTuner extends AbstractEntity {

    double frequency;
    double bandwidth;
    TransmitMode mode;
    String name;

    @OneToMany (mappedBy = "radio")
    private final List<ReceptionParameters> parameters = new ArrayList<>();


    public RadioTuner() {}

    public RadioTuner (String name,double freq,TransmitMode mode, double bandwidth) {
        this.name = name;
        this.frequency = freq;
        this.mode = mode;
        this.bandwidth = bandwidth;
    }


}
