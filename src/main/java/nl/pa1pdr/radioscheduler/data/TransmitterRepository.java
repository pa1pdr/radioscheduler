package nl.pa1pdr.radioscheduler.data;

import org.springframework.data.repository.CrudRepository;
import nl.pa1pdr.radioscheduler.model.Transmitter;

public interface TransmitterRepository extends CrudRepository <Transmitter, Integer> {}
