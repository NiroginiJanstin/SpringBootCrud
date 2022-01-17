package myapp.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import myapp.demo.Model.Employee;
import myapp.demo.Repository.EmployeeRepository;
import myapp.demo.Utils.CsvUtil;

@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/single")
    public ResponseEntity<HttpStatus> uploadSingleCSVFile(@RequestParam("csvfile") MultipartFile csvfile) {

        // Checking the upload-file's name before processing
        if (csvfile.getOriginalFilename().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // checking the upload file's type is CSV or NOT
        if (!CsvUtil.isCSVFile(csvfile)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        try {
            // save file data to database
            List<Employee> employees =  CsvUtil.parseCsvFile(csvfile.getInputStream());
            employeeRepository.saveAll(employees);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
