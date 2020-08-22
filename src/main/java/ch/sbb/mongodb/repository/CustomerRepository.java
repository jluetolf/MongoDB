package ch.sbb.mongodb.repository;

import ch.sbb.mongodb.objects.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	public Customer findByFirstName(String firstName);
	public List<Customer> findByLastName(String lastName);

	@Query(value = "{'customer.name': ?0}", fields = "{'customer' : 0}")
	public List<Customer> findCustomLastName(String empName);
}
