package nl.pa1pdr.radioscheduler.data;

import org.springframework.data.repository.CrudRepository;
import nl.pa1pdr.radioscheduler.model.ReceptionParameters;


public interface ReceptionParameterRepository extends CrudRepository <ReceptionParameters, Integer> {}
