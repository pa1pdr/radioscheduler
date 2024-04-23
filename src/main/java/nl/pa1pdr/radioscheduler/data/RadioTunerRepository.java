package nl.pa1pdr.radioscheduler.data;

import org.springframework.data.repository.CrudRepository;
import nl.pa1pdr.radioscheduler.model.RadioTuner;


public interface RadioTunerRepository extends CrudRepository <RadioTuner, Integer>{}
