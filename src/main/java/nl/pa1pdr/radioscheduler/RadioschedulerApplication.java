package nl.pa1pdr.radioscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalTime;

@SpringBootApplication
public class RadioschedulerApplication {

	//LocalTime lt = LocalTime.now();
	TransmitSchedule ts = new TransmitSchedule ("Test",LocalTime.of(13,00),10);
	Transmitter t = new Transmitter ("Test",147.48,TransmitMode.USB,ts);



	RadioschedulerApplication() {

	}

	public static void main(String[] args) {
		SpringApplication.run(RadioschedulerApplication.class, args);
		
	}

}
