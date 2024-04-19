package nl.pa1pdr.radioscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalTime;

@SpringBootApplication
public class RadioschedulerApplication {

	Station test = new Station("Pinneberg", "Germany");
	TransmitSchedule ts = new TransmitSchedule (test,"Test",LocalTime.of(13,00),10);
	ReceptionCommand rcvRtty = new ReceptionCommand ("RTTY 85/45","jvcomm.exe -bd45");
	Transmitter t = new Transmitter (test,"Test",147.48,TransmitMode.USB,rcvRtty);




	RadioschedulerApplication() {

	}

	public static void main(String[] args) {
		SpringApplication.run(RadioschedulerApplication.class, args);
		
	}

}
