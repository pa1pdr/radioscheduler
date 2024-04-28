package nl.pa1pdr.radioscheduler.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import nl.pa1pdr.radioscheduler.model.Station;
import nl.pa1pdr.radioscheduler.model.TransmitSchedule;

public interface TransmitScheduleRepository extends CrudRepository <TransmitSchedule, Integer> {

    List<TransmitSchedule> findByEnabledOrderByTimeOfDay (boolean enabled);
    List<TransmitSchedule> findByStation(Station station);

}
