package nl.pa1pdr.radioscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import nl.pa1pdr.radioscheduler.controller.TransmitScheduleController;
import nl.pa1pdr.radioscheduler.data.TransmitScheduleRepository;
import nl.pa1pdr.radioscheduler.model.ReceptionParameters;
import nl.pa1pdr.radioscheduler.model.Station;
import nl.pa1pdr.radioscheduler.model.TransmitMode;
import nl.pa1pdr.radioscheduler.model.TransmitSchedule;
import nl.pa1pdr.radioscheduler.model.Transmitter;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class RadioschedulerApplication {

	Logger logger = LoggerFactory.getLogger(RadioschedulerApplication.class); 


	@Autowired
	private TransmitScheduleController txctl;

	@Autowired
	private TransmitScheduleRepository txrepo;

	Station test = new Station("Pinneberg", "Germany");
	TransmitSchedule ts = new TransmitSchedule (test,"Test",LocalTime.of(13,00),10);
	ReceptionParameters rcvRtty = new ReceptionParameters ("RTTY 85/45","jvcomm.exe -bd45");
	Transmitter t = new Transmitter (test,"Test",147.48,TransmitMode.USB,rcvRtty);




	RadioschedulerApplication() {
	}

	public void run() {
/*		TransmitSchedule ts2 = new TransmitSchedule (test,"Test2",LocalTime.of(14,00),10);
		test.addSchedule(ts);
		test.addSchedule(ts2);
		test.addTransmitter(t);
		txctl.saveStation(test);
		txctl.saveSchedule(ts);
		//recrepo.save(rcvRtty);
 	try {
			parseWeatherfile(new File ("src/resources/WeatherFaxSchedules.xml"));
		} catch (IOException ioe) {
			logger.error ("Error reading XML file",ioe);
		}
*/


		List<TransmitSchedule> enabled =  txctl.fetchEnabledSchedules();
		for (TransmitSchedule txs : enabled) {
			logger.debug("Schedule {}",txs);		
			   List<Transmitter> t = txs.getStation().getTransmitters();
			   for (Transmitter tx : t) {
			   		logger.info ("Schedule {} Transmitter {} at {} kHz",txs.getName(),tx.getName(),tx.getFrequency());
			   }
		}


	}


	public void parseWeatherfile (File file) throws IOException {
		WeatherFaxXMLParser wfp = new WeatherFaxXMLParser(txctl);
		wfp.parseWeatherfile(file);

	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(RadioschedulerApplication.class, args);
		ctx.getBean (RadioschedulerApplication.class).run();
		System.exit(0);
	}

}
