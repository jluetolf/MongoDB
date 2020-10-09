package ch.sbb.mongodb;

import ch.sbb.mongodb.objects.Customer;
import ch.sbb.mongodb.objects.Department;
import ch.sbb.mongodb.objects.Employee;
import ch.sbb.mongodb.objects.Trip;
import ch.sbb.mongodb.readers.TripReaderCustom;
import ch.sbb.mongodb.repository.CustomerRepository;
import ch.sbb.mongodb.repository.DepartmentRepository;
import ch.sbb.mongodb.repository.TripRepository;
import ch.sbb.postgres.repository.TripTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MongoDbApplication implements CommandLineRunner {
	
	@Autowired
	private TripReaderCustom tripReader;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private TripRepository<Trip, Number> tripRepository;

	@Autowired
	private TripTableRepository tripTableRepository;

	public static void main(String[] args) {
		SpringApplication.run(MongoDbApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
//		List<Trip> trips = tripReader.readTrips();
//		customerRepository.deleteAll();
//		
//		trips.forEach(trip -> tripRepository.save(trip));

		// save a couple of customers
		customerRepository.save(new Customer("Alice", "Smith"));
		customerRepository.save(new Customer("Bob", "Smith"));

		Employee tom = new Employee("001", "Tom", 29, 200);
		Employee joerg = new Employee("001", "Joerg", 45, 200);
		List employees = new ArrayList();
		employees.add(tom);
		employees.add(joerg);

		departmentRepository.save(new Department( null, "ZIS", "Zug Information System", employees)); 
		
		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerRepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(customerRepository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : customerRepository.findByLastName("Smith")) {
			System.out.println(customer);
		}
	}

}
