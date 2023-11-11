package FileManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class FileManagement {

    private static final String file = "C:/Users/valya/OneDrive/SirmaAcademy/Sirma/src/com/StaffManagementSystem/src/main/java/employees.json";

    // Reading JSON file
    public JSONArray readFile() {
        JSONArray employeeList = new JSONArray();
        try (FileReader reader = new FileReader(file)) {
            Object obj = new JSONParser().parse(reader);
            // Check if the parsed object is an instance of JSONArray
            if (obj.getClass().equals(JSONArray.class)) {
                employeeList = (JSONArray) obj;
            } else {
                System.out.println("Invalid JSON format in the file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    // Writing to JSON
    public void writeEmployeeListToFile(JSONArray employeeList) {
        try (FileWriter file = new FileWriter("C:/Users/valya/OneDrive/SirmaAcademy/Sirma/src/com/StaffManagementSystem/src/main/java/employees.json")) {
            file.write(employeeList.toJSONString());
            file.flush();
            System.out.println("Client.Employee list updated successfully.");
        } catch (IOException e) {
            System.out.println("Error with writing to the file");
        }
    }

    // Reading the employees from the file and printing their details
    public JSONArray viewEmployees() {
        JSONParser parser = new JSONParser();
        JSONArray employeeList = new JSONArray();

        try (FileReader reader = new FileReader(file)) {
            Object obj = parser.parse(reader);
                employeeList = (JSONArray) obj;
                // Print the employee details (specification)
                Iterator<JSONObject> iterator = employeeList.iterator();
                while (iterator.hasNext()) {
                    JSONObject employeeObject = iterator.next();
                    System.out.println("ID: " + employeeObject.get("Id"));
                    System.out.println("Name: " + employeeObject.get("Name"));
                    System.out.println("Department: " + employeeObject.get("Department"));
                    System.out.println("Position: " + employeeObject.get("Position"));
                    System.out.println("Salary: " + employeeObject.get("Salary"));
                    System.out.println("Start Date: " + employeeObject.get("dateOfStart"));
                    System.out.println("--------------------------------------");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

}