package Main;
import Client.Employee;
import Client.Manager;
import FileManagement.FileManagement;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Menu {
    public static void start() {
        Scanner sc = new Scanner(System.in);
        FileManagement fm = new FileManagement();
        Employee em = new Employee();

        System.out.println("Welcome to Staff Management System");
        displayCommands();

        List<Employee> savedEmployees = new ArrayList<>();
        for (Object ob : fm.readFile()) {
            JSONObject emp = (JSONObject) ob;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            try {
                savedEmployees.add(new Employee(Math.toIntExact((Long) emp.get("Id")), (String) emp.get("Name"),
                        formatter.parse((String) emp.get("StartDate")), (String) emp.get("Department"),
                        (String) emp.get("Role"), (Double) emp.get("Salary")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        Manager manager = new Manager(savedEmployees); // Adding the info from the file in manager, so my program could start
                                                      //with the saved info
        boolean isRunning = true;

        while (isRunning) {
            try {
                String[] command = sc.nextLine().trim().split(" ");
                if(command[0].equals("exit")){
                    //Saving the data
                    fm.writeEmployeeListToFile(manager.employees);
                    isRunning = false;
                }
                else {
                    manager.execute(command);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
        public static void displayCommands() {
            System.out.println("add Employee");
            System.out.println("edit <id>");
            System.out.println("search <param> <id>");
            System.out.println("fire <id>");
            System.out.println("exit");
        }
}
