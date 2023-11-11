package Client;

import java.io.File;
import java.io.Serializable;

import FileManagement.FileManagement;
import Interfaces.ManageEmployees;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class Employee implements ManageEmployees, Serializable {
    FileManagement fm = new FileManagement();
    private int id;
    private static int lastId = 1;
    private String name;

    private Date startDate;
    private String department;
    private String position;
    private double salary;

    private static final File file = new File("C:/Users/valya/OneDrive/SirmaAcademy/Sirma/src/com/StaffManagementSystem/src/main/java/employees.json");

    public Employee(int id, String name, Date startDate, String department, String position, double salary) {
        this.id = lastId++;   // Auto increment the id
        this.name = name;
        try {
            this.startDate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.department = department;
        this.position = position;
        this.salary = salary;
    }
    public Employee() {
        this.id = lastId++;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Date getStartDate() {
        return startDate;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Edit the employee's salary
    @Override
    public void raiseSalary() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose id of worker: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Choose new salary for him: ");
        Double newSalary = Double.parseDouble(sc.nextLine());
        JSONArray employeeList = fm.readFile();
        for (Object obj : employeeList) {
            JSONObject employeeObject = (JSONObject) obj;
            int employeeId = Math.toIntExact((Long) employeeObject.get("Id"));
            if (employeeId == id) {
                // Update the salary for the matched employee
                employeeObject.put("Salary", newSalary);
                System.out.println("Salary updated successfully.");

                // Write the updated JSONObject back to the file
                fm.writeEmployeeListToFile(employeeList);
                break;
            }
        }
    }

    // Fire (remove) employee
    @Override
    public void fireEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose id of worker: ");
        int id = Integer.parseInt(sc.nextLine());
        JSONArray employeeList = fm.readFile();

        for (int i = 0; i < employeeList.size(); i++) {
            JSONObject employeeObject = (JSONObject) employeeList.get(i);
            int currentEmployeeId = Math.toIntExact((Long) employeeObject.get("Id"));

            if (currentEmployeeId == id) {
                // Remove the employee with the specified ID
                employeeList.remove(i);
                System.out.println("Client.Employee deleted successfully.");

                // Write the updated JSONArray back to the file
                fm.writeEmployeeListToFile(employeeList);
                return; // Exit the method once the employee is deleted
            }
        }

        System.out.println("Employee with ID " + id + " not found.");
    }

    @Override
    public Employee addEmployee() {
        FileManagement fm = new FileManagement();
        Scanner sc = new Scanner(System.in);
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("Enter start date: (YYYY-MM-DD)");
        String dateInput = sc.nextLine();
        Date startDate = null;
        try {
            LocalDate localDate = LocalDate.parse(dateInput);
            startDate = java.sql.Date.valueOf(localDate);    // Parsing date input
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("Department: ");
        String department = sc.nextLine();
        System.out.println("Position: ");
        String position = sc.nextLine();
        System.out.println("Salary: ");
        Double salary = Double.parseDouble(sc.nextLine());
        Employee employee = new Employee(getId(), name, startDate, department, position, salary);

        // Create a JSON object for the Employee
        JSONObject employeeObject = new JSONObject();
        employeeObject.put("Id", employee.getId());
        employeeObject.put("dateOfStart", new SimpleDateFormat("yyyy-MM-dd").format(employee.getStartDate()));
        employeeObject.put("Name", employee.getName());
        employeeObject.put("Department", employee.getDepartment());
        employeeObject.put("Position", employee.getPosition());
        employeeObject.put("Salary", employee.getSalary());
        JSONArray employeeList = fm.readFile();
        employeeList.add(employeeObject);

        fm.writeEmployeeListToFile(employeeList);

        return employee;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", " + getName() + ", " + getDepartment() + ", " + getPosition() + ", "+ getSalary();
    }
}
