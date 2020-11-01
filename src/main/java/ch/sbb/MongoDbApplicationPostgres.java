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
public class MongoDbApplicationPostgres implements CommandLineRunner {

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
    SpringApplication.run(MongoDbApplicationPostgres.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    List<Trip> trips = tripReader.readTrips();
    customerRepository.deleteAll();
    final AtomicLong id = new AtomicLong(0L);
    List<TripTable> tripTableList = new ArrayList<>();
    trips.forEach(trip -> {
      tripTableList.add(new TripTable(trip, id.getAndIncrement()));
    });


    log.info("start saving ...");

    tripSQLRepository.saveAll(tripTableList);

    log.info("finished");
    
  }

}
