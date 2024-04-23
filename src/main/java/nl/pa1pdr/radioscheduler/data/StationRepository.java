package nl.pa1pdr.radioscheduler.data;

import org.springframework.data.repository.CrudRepository;

import nl.pa1pdr.radioscheduler.model.Station;

public interface StationRepository extends CrudRepository <Station, Integer> {}
