package Client;

import java.io.File;
import java.io.Serializable;

import FileManagement.FileManagement;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Employee {
    private int id;
    private static int lastId = 0;
    private String name;
    private Date startDate;
    private String department;
    private String position;
    private double salary;

    private static final File file = new File("C:/Users/valya/OneDrive/SirmaAcademy/Sirma/src/com/StaffManagementSystem/src/main/java/employees.json");

    public Employee(int id, String name, Date startDate, String department, String position, double salary) {
        this.id = id;
        if ( id > lastId)
        {
            this.lastId = id; //auto-increment id
        }
        this.name = name;
        this.startDate = startDate;
        this.department = department;
        this.position = position;
        this.salary = salary;
    }
    public Employee() {}

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setLastId(int lastId) {
        Employee.lastId = lastId;
    }

    public static int getLastId() {
        return lastId;
    }

    public String getStartDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        return formatter.format(startDate);
    }
    public String getName() {
        return name;
    }
    public String getDepartment() {
        return department;
    }
    public String getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "ID: " + getId() + ", " + getName() + ", " + getDepartment() + ", " + getPosition() + ", "+ getSalary();
    }
}
