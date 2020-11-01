package ch.sbb;

import ch.sbb.mongodb.client.DocumentDbFacade;
import ch.sbb.mongodb.objects.Trip;
import ch.sbb.mongodb.readers.TripReaderCustom;
import ch.sbb.mongodb.repository.CustomerRepository;
import ch.sbb.mongodb.repository.DepartmentRepository;
import ch.sbb.mongodb.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class MongoDbApplicationCosmosUsingDBFacade implements CommandLineRunner {

  @Autowired
  private TripReaderCustom tripReader;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private TripRepository tripRepository;
  

  @Value("${gtfs.directory}")
  protected String directory;

  @Value("${gtfs.csvfile}")
  protected String csvfile;

  @Value("${spring.data.mongodb.uri}")
  protected String uri;
  

  public static void main(String[] args) {
    SpringApplication.run(MongoDbApplicationCosmosUsingDBFacade.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    DocumentDbFacade documentDbFacade = new DocumentDbFacade(uri);

    log.info("trip creation started");
    List<Trip> trips = tripReader.readTrips();
    log.info("trips created, trips count is ", trips.size());
    

    List<Trip> junks = new ArrayList<>(10000);
    List<Map<String, Object>> tripsMap = new ArrayList<>(10000);
    for (int nIndex=0; nIndex < trips.size(); nIndex++) {

      junks.add(trips.get(nIndex));
      if (trips.size() - 1 == nIndex) {
        log.info("started converting {} documents", nIndex);
        junks.forEach(trip -> tripsMap.add(trip.convertToMap()));
        log.info("finished converting {} documents", nIndex);
        log.info("started saving {} documents", nIndex);
        documentDbFacade.putDocuments("trips", tripsMap);
        log.info("finished saving");
        return;
      }
      if (nIndex > 0 && (nIndex % 10000) == 0) {
        log.info("started converting {} documents", nIndex);
        tripsMap.clear();
        junks.forEach(trip -> tripsMap.add(trip.convertToMap()));
        log.info("finished converting {} documents", nIndex);

        log.info("started saving {} documents", tripsMap.size());
        documentDbFacade.putDocuments("trips", tripsMap);
        log.info("finished saving");
        junks.clear();
      }
    }
  }
}

