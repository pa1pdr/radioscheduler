package nl.pa1pdr.radioscheduler.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.pa1pdr.radioscheduler.data.ReceptionParameterRepository;
import nl.pa1pdr.radioscheduler.data.StationRepository;
import nl.pa1pdr.radioscheduler.data.TransmitScheduleRepository;
import nl.pa1pdr.radioscheduler.data.TransmitterRepository;
import nl.pa1pdr.radioscheduler.model.Station;
import nl.pa1pdr.radioscheduler.model.TransmitSchedule;
import nl.pa1pdr.radioscheduler.model.Transmitter;

@Component
public class TransmitScheduleController {

    @Autowired
    private TransmitScheduleRepository schedulerepo;
    @Autowired
    private StationRepository stationrepo;
    @Autowired
    private TransmitterRepository transmitrepo;

    @Autowired
    private ReceptionParameterRepository rcprepo;

    public TransmitScheduleController() {
    }

    public void saveSchedule(TransmitSchedule s) {
        stationrepo.save(s.getStation());
        schedulerepo.save(s);
    }

    /**
     * Save the station with all known schedules and transmitters
     * 
     * @param s
     */
    public void saveStation(Station s) {

        stationrepo.save(s);

        // first save all transmitters
        for (Iterator<Transmitter> i = s.getTransmitters().iterator(); i.hasNext();) {
            Transmitter t = i.next();
            if (t.getReceptionstyle() != null) {
                rcprepo.save(t.getReceptionstyle());
            }
            transmitrepo.save(t);
        }
        // then all schedules
        for (Iterator<TransmitSchedule> i = s.getSchedules().iterator(); i.hasNext();) {
            schedulerepo.save(i.next());
        }

    }

    public Iterable<TransmitSchedule> fetchAllSchedules() {
        return schedulerepo.findAll();
    }

    public Iterable<TransmitSchedule> fetchAllSchedules(Station s) {
        return s.getSchedules();
    }

}
