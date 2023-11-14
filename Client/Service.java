package Client;

import FileManagement.FileManagement;
import Interfaces.ManageEmployees;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Service extends Employee implements ManageEmployees {
    Scanner sc = new Scanner(System.in);
    public Service(){}

    @Override
    public void raiseSalary(Double salary, Employee em) {
        em.setSalary(salary);
    }
    @Override
    public  Employee addEmployee() {
        String[] employeeInfo = sc.nextLine().trim().split(",");
        String name = employeeInfo[0];
        String dateInput = employeeInfo[1];
        // yyyy-mm-dd
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        Date daty;
        try {
            daty = formatter.parse(dateInput);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String department = employeeInfo[2];
        String position = employeeInfo[3];
        Double salary = Double.parseDouble(employeeInfo[4]);
        Employee employee = new Employee(getLastId()+1, name, daty, department, position, salary);
        return employee;
    }


}
