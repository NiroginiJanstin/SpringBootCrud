package myapp.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import myapp.demo.Model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee,String>{

}