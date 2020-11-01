package ch.sbb;

import ch.sbb.mongodb.objects.Trip;
import ch.sbb.mongodb.readers.TripReaderCustom;
import ch.sbb.mongodb.repository.CustomerRepository;
import ch.sbb.mongodb.repository.DepartmentRepository;
import ch.sbb.mongodb.repository.TripRepository;
import ch.sbb.postgres.repository.TripSQLRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@SpringBootApplication
public class MongoDbApplicationJSONFileBuilder implements CommandLineRunner {

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
    SpringApplication.run(MongoDbApplicationJSONFileBuilder.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    
    // creates a file only upload to joergaccount on Azure and import the data using copy
    List<Trip> trips = tripReader.readTrips();
    String filename = directory + ".json";
    ObjectMapper mapper = new ObjectMapper();
    final AtomicLong id = new AtomicLong(0L);
    CSVWriter writer = new CSVWriter(new FileWriter(filename), '|',
        CSVWriter.NO_QUOTE_CHARACTER,
        CSVWriter.NO_ESCAPE_CHARACTER,
        CSVWriter.RFC4180_LINE_END);
    for (Trip trip : trips) {
      String[] row = new String[1];
      row[0] = mapper.writeValueAsString(trip);
      writer.writeNext(row);
      writer.flush();
    }
    writer.close();
  }

}
