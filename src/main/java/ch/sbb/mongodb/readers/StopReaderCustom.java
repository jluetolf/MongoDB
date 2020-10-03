package ch.sbb.mongodb.readers;

import ch.sbb.mongodb.objects.Stop;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StopReaderCustom extends CustomCSVReader {
  final static String filename = "stops.txt";

  public Map<String, Stop> readStops() throws IOException, CsvValidationException {
    Map<String, Stop> stopMap = new HashMap<>();
    List<String[]> rows = getCSVRows(filename);
    rows.forEach(row -> stopMap.put(row[0], createStop(row)));
    return stopMap;
  }

  private static Stop createStop(String[] attributes) {
    return new Stop(
        null,
        attributes[0],
        attributes[1],
        attributes[2],
        attributes[3]
    );
  }
}
