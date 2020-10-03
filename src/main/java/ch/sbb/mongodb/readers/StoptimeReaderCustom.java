package ch.sbb.mongodb.readers;

import ch.sbb.mongodb.objects.Stop;
import ch.sbb.mongodb.objects.Stoptime;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StoptimeReaderCustom extends CustomCSVReader {

  @Autowired
  StopReaderCustom stopReader;
  
  final static String filename = "stop_times.txt";

  public Map<String, List<Stoptime>> readStoptimes() throws IOException, CsvValidationException {
    Map<String, List<Stoptime>> stoptimeMap = new HashMap<>();
    List<String[]> rows = getCSVRows(filename);

    Map<String, Stop> stops = stopReader.readStops();
    
    rows.forEach(row -> {
      Stoptime stoptime = createStoptime(row);
      if (stops.containsKey(row[3])) {
        stoptime.setStop(stops.get(row[3]));
      }
      if (stoptimeMap.containsKey(row[0])) {
        stoptimeMap.get(row[0]).add(stoptime);
      } else {
        List<Stoptime> stoptimes = new ArrayList<>();
        stoptimes.add(stoptime);
        stoptimeMap.put(row[0], stoptimes);
      }
    });
    return stoptimeMap;
  }

  private static Stoptime createStoptime(String[] attributes) {

    return new Stoptime(
        null,
        attributes[0],
        attributes[1],
        attributes[2],
        attributes[3],
        Integer.parseInt(attributes[4]),
        Integer.parseInt(attributes[5]),
        Integer.parseInt(attributes[6]),
        null
    );
  }
}
