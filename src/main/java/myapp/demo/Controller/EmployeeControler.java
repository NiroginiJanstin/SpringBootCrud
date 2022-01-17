package myapp.demo.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myapp.demo.Model.Employee;
import myapp.demo.Repository.EmployeeRepository;

@RestController
@RequestMapping(value="/employee")
public class EmployeeControler {

    @Autowired
    EmployeeRepository employeeRepository;
    
    @GetMapping(value = "/")
    public String getList() {
         return "Hello World";
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
      try {
        Employee _employee = employeeRepository.save(new Employee(employee.getFirstName(), employee.getLastName(), employee.getMobile(),employee.getEmail()));
        return new ResponseEntity<>(_employee, HttpStatus.CREATED);
      } catch (Exception e) {
        System.out.println(e);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Employee>> getAllEmployees() {
      try {
        List<Employee> employees = new ArrayList<Employee>();
    
        employeeRepository.findAll().forEach(employees::add);
    
        if (employees.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    
        return new ResponseEntity<>(employees, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String employeeId) {
      Optional<Employee> employee = employeeRepository.findById(employeeId);
    
      if (employee.isPresent()) {
        return new ResponseEntity<>(employee.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") String employeeId, @RequestBody Employee emp) {
      Optional<Employee> employee = employeeRepository.findById(employeeId);
    
      if (employee.isPresent()) {
        Employee _employee = employee.get();
        _employee.setFirstName(emp.getFirstName());
        _employee.setLastName(emp.getLastName());
        _employee.setMobile(emp.getMobile());
        _employee.setEmail(emp.getEmail());
        return new ResponseEntity<>(employeeRepository.save(_employee), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") String id) {
        try {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        try {
            employeeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
