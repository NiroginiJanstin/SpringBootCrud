package myapp.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {
    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String email;

    public Employee(){

    }

    public Employee(String firstName,String lastName, String mobileNo, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNo = mobileNo;
        this.email = email;
    }

    public void setEmployeeId(String employeeId){
        this.employeeId = employeeId;
    }

    public String getEmployeeId(){
        return this.employeeId;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;

    }

    public String getLastName(){
        return this.lastName;
    }

    public void setMobile(String mobileNo){
        this.mobileNo = mobileNo;
    }

    public String getMobile(){
        return this.mobileNo;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }
    
}
