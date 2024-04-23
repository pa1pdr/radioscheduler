package nl.pa1pdr.radioscheduler.data;

import org.springframework.data.repository.CrudRepository;
import nl.pa1pdr.radioscheduler.model.TransmitSchedule;

public interface TransmitScheduleRepository extends CrudRepository <TransmitSchedule, Integer> {}
