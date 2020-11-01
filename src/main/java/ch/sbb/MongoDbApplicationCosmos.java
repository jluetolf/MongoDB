package ch.sbb;

import ch.sbb.mongodb.objects.Trip;
import ch.sbb.mongodb.readers.TripReaderCustom;
import ch.sbb.mongodb.repository.CustomerRepository;
import ch.sbb.mongodb.repository.DepartmentRepository;
import ch.sbb.mongodb.repository.TripRepository;
import ch.sbb.postgres.entities.TripTable;
import ch.sbb.postgres.repository.TripSQLRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@SpringBootApplication
public class MongoDbApplicationCosmos implements CommandLineRunner {

  @Autowired
  private TripReaderCustom tripReader;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private TripRepository tripRepository;

  @Autowired
  private TripSQLRepository tripSQLRepository;


  @Value("${gtfs.directory}")
  protected String directory;

  public static void main(String[] args) {
    SpringApplication.run(MongoDbApplicationCosmos.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    List<Trip> trips = tripReader.readTrips();

    log.info("trips created, trips count is ", trips.size());
    
    List<Trip> tripList = new ArrayList<>();
    for (int nIndex=0; nIndex < trips.size(); nIndex++) {
      tripList.add(trips.get(nIndex));
      
      if (trips.size() -1 == nIndex) {
        tripRepository.saveAll(tripList);
        log.info("saved {} trips", nIndex);
        return;
      }
      if (nIndex > 0 && (nIndex % 1000) == 0) {
        tripRepository.saveAll(tripList);
        log.info("saved {} trips", nIndex);
        tripList.clear();
      }
    }
  }

}
