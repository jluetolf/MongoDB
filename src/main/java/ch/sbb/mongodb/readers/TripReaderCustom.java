package ch.sbb.mongodb.readers;

import ch.sbb.mongodb.objects.Stoptime;
import ch.sbb.mongodb.objects.Trip;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TripReaderCustom extends CustomCSVReader {
  
  @Autowired
  StoptimeReaderCustom stoptimeReader;
  
  final static String tripFileName = "trips.txt";

  public List<Trip> readTrips() throws IOException, CsvValidationException {
    List<Trip> trips = new ArrayList<>();
    List<String[]> rows = getCSVRows(tripFileName);
    Map<String, List<Stoptime>> stoptimes = stoptimeReader.readStoptimes();
    
/*    rows(row -> {
      Trip trip = createTrip(row);
      if (stoptimes.containsKey(row[2])) {
        trip.setStoptimes(stoptimes.get(row[2]));
      }
      trips.add(trip);
    });*/

    Trip trip = createTrip(rows.get(0));
    trips.add(trip);
    return trips;
  }

  private static Trip createTrip(String[] attributes) {

    return new Trip(
        null,
        attributes[0],
        attributes[1],
        attributes[2],
        attributes[3],
        Integer.parseInt(attributes[4]),
        Integer.parseInt(attributes[5]),
        null
    );
  }
}
