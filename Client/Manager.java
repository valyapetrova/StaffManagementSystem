package Client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager extends Employee {
    Scanner sc = new Scanner(System.in);
    public List<Employee> employees = new ArrayList<>();
    Service sr = new Service();

    public Manager() {}
    public Manager(List<Employee> employees){
        this.employees = employees;
    }

    public Employee execute(String[] array) throws ParseException {
        String command = array[0];

        switch (command.toLowerCase()) {
            case "add":
                String name = array[1];
                // Add a staff member <name><date><department><position><salary>
                if(name.equalsIgnoreCase("employee")) {
                    Employee employee = sr.addEmployee();
                    //SetLastEmployeeId(employees);
                    employees.add(employee);
                }
                break;
            case "edit":
                int id = Integer.parseInt(array[1]);
                // Edit current staff member
                for (Employee person : employees){
                    if (person.getId() == id) {
                        System.out.println("What do you want to edit? [name][salary][role][department]");
                        String input = sc.nextLine();
                        System.out.println("Enter new information: ");
                        String newInfo = sc.nextLine();
                        if(input.equalsIgnoreCase("name")){
                            person.setName(newInfo);
                        }else if(input.equalsIgnoreCase("salary")){
                            sr.raiseSalary(Double.valueOf(newInfo),person);
                        }else if(input.equalsIgnoreCase("role")){
                            person.setPosition(newInfo);
                        }else if(input.equalsIgnoreCase("department")){
                            person.setDepartment(newInfo);
                        }
                    }
                }
                break;
            case "list":
                // View all the staff members
                for (Employee em : employees){
                    System.out.println(em);
                }
                break;
            case "search":
                String category = array[1];
                String choose = array[2];
                // Search by category
                for (Employee em : employees){
                    if (category.equals("name")){
                        if(choose.equalsIgnoreCase(em.getName())){
                            System.out.println(em);
                        }
                    }else if(category.equals("id")){
                        if(choose.equalsIgnoreCase(String.valueOf(em.getId()))){
                            System.out.println(em);
                        }
                    }else if(category.equals("department")){
                        if(choose.equals(em.getDepartment())){
                            System.out.println(em);
                        }
                    }
                }
                break;
            case "fire":
                // Firing keeps the staff in the system, but changes their status to 'Fired'
                id = Integer.parseInt(array[1]);
                for (Employee em : employees) {
                    if (id == em.getId()) {
                        em.setPosition("Fired");
                    }
                }
                break;
            default:
                System.out.println("Invalid command. Please enter a valid command.");
                break;
        }
        return null;
    }
}
