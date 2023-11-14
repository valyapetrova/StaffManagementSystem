package FileManagement;

import java.io.*;
import java.util.List;

import Client.Employee;
import Client.Manager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileManagement extends Manager {

    private static final File file_Path = new File("C:/Users/valya/OneDrive/StaffManagementSystem/src/main/java/employees.json");

    public FileManagement(){
 }
    public JSONArray readFile() {
        JSONArray jsonArray = new JSONArray();
        try (FileReader reader = new FileReader(file_Path)) {
            Object obj = new JSONParser().parse(reader);
            jsonArray = (JSONArray) obj;
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    // Writing to JSON
    public void writeEmployeeListToFile(List<Employee> employeeList) {
        JSONArray employeeJsonArray = new JSONArray();
        for(Employee employee : employeeList ) {
            JSONObject employeeJson = new JSONObject();
            employeeJson.put("Name",employee.getName());
            employeeJson.put("StartDate", employee.getStartDate());
            employeeJson.put("Department",employee.getDepartment());
            employeeJson.put("Role", employee.getPosition());
            employeeJson.put("Id", employee.getId());
            employeeJson.put("Salary", employee.getSalary());
            employeeJsonArray.add(employeeJson);
        }
        try (FileWriter file = new FileWriter("C:/Users/valya/OneDrive/StaffManagementSystem/src/main/java/employees.json")) {
            file.write(employeeJsonArray.toJSONString());
            file.flush();
            System.out.println("Client.Employee list updated successfully.");
        } catch (IOException e) {
            System.out.println("Error with writing to the file");
        }
    }

}