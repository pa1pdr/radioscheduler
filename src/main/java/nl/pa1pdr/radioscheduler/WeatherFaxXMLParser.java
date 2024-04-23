package nl.pa1pdr.radioscheduler;

import java.io.File;

import java.io.IOException;
import java.time.LocalTime;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import nl.pa1pdr.radioscheduler.controller.TransmitScheduleController;
import nl.pa1pdr.radioscheduler.model.ReceptionParameters;
import nl.pa1pdr.radioscheduler.model.Station;
import nl.pa1pdr.radioscheduler.model.TransmitMode;
import nl.pa1pdr.radioscheduler.model.TransmitSchedule;
import nl.pa1pdr.radioscheduler.model.Transmitter;


public class WeatherFaxXMLParser {

	Logger logger = LoggerFactory.getLogger(WeatherFaxXMLParser.class); 

    private TransmitScheduleController txctl;

    private ReceptionParameters rcp = new ReceptionParameters("FAX", "jvcomm32.exe -f");

    public WeatherFaxXMLParser(TransmitScheduleController tx) {
        this.txctl = tx;
    }



    public void parseWeatherfile(File faxscheds) throws IOException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(faxscheds);
            doc.getDocumentElement().normalize();
            NodeList nl = doc.getElementsByTagName("OCPNWeatherFaxSchedules");
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                logger.info("fetching master node {}",n.getNodeName());
                
                NodeList st = n.getChildNodes();
                for (int j = 0; j < st.getLength(); j++) {
                    Node s = st.item(j);
                    logger.info("fetching node {}", s.getNodeName());
                    if (s.getNodeName().equals("Station")) {
                        Station station = processStation (s);
                        txctl.saveStation(station);
                        logger.info ("Processed station {}", station);  
                    } else {
                        logger.info("found {}, value {}", s.getNodeName(), s.getNodeValue());
                    }
                }
            }
            
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException sec) {
            sec.printStackTrace();
        } 
    }


    private Station processStation (Node s) {

        logger.info ("Processing station {}", s.getTextContent());
        NamedNodeMap m = s.getAttributes();        
        String ss = m.getNamedItem("Name").getTextContent();
        
        Station station = new Station (ss.split(",")[0].trim(),ss.split(",")[1].trim());
        NodeList sc = s.getChildNodes();
        for (int i = 0; i < sc.getLength(); i++) {
            Node f = sc.item(i);

              logger.info("fetching child node {}", f.getNodeName());
            if (f.getNodeName().equals("Frequency")) {
                String[] freqs = f.getAttributes().getNamedItem("khz").getTextContent().split(",");
                for (int j = 0; j < freqs.length; j++) {
                    Transmitter t = new Transmitter (station,station.getName() + freqs[j],Double.parseDouble(freqs[j]),TransmitMode.USB,rcp);
                    t.setPower(50);
                    station.addTransmitter(t);
                }
                NodeList scheds = f.getChildNodes();
                for (int j = 0;j < scheds.getLength();j++) {
                    if (scheds.item(j).getNodeName().equals("Map")) {
                        logger.info ("Processing a map");
                        TransmitSchedule ts = processSchedule (station,scheds.item(j));
                        station.addSchedule(ts);
                    } else {
                        logger.info ("Dropping useless element {}",scheds.item(j).getNodeName());
                    }

                }
            } else {
                logger.info("found {}, value {}", f.getNodeName(), f.getTextContent());
            }
        }
        return station;
    }


    private TransmitSchedule processSchedule (Station s, Node sc) {
        TransmitSchedule sched = new TransmitSchedule();

        //parse  <Map Time="1234" Contents="EAST PACIFIC GOES IR SATELLITE IMAGE" Valid="12" Area="G" />
        NamedNodeMap nm = sc.getAttributes();
        String tm = nm.getNamedItem("Time").getNodeValue();
        String title = nm.getNamedItem ("Contents").getNodeValue();
        
        
        int hr = Integer.parseInt(tm.substring(0, 2));
        int min = Integer.parseInt(tm.substring(2));

        sched.setDuration (12);
        sched.setName(title);
        sched.setRepeatinterval(24*60);
        sched.setStation(s);
        sched.setTimeOfDay(LocalTime.of(hr, min));
        if (nm.getNamedItem("Valid") != null) {
            sched.setValidity (nm.getNamedItem("Valid").getNodeValue());
        }
        
        try {
            if (nm.getNamedItem("Duration") != null) {
                String dur = nm.getNamedItem("Duration").getNodeValue();
            
            if ((Integer.parseInt(dur)) >= 5)
                sched.setDuration (Integer.parseInt(dur));
            }

        } catch (NumberFormatException nfe) {
            logger.error("Unreadabe duration", nfe);
        }
        
        return sched;
    }

}
