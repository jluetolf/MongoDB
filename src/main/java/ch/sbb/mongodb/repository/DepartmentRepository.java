package ch.sbb.mongodb.repository;

import ch.sbb.mongodb.objects.Customer;
import ch.sbb.mongodb.objects.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String>  {

//  @Query(value = "{'employees.name': ?0}", fields = "{'employees' : 0}")
//  Department findDepartmentByEmployeeName(String empName);

  List findDepartmentByName(String name);
}
