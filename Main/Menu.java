package Main;

import Client.Employee;
import FileManagement.FileManagement;

import java.util.List;
import java.util.Scanner;


public class Menu {
    public static void start(){
        Employee em = new Employee();
        FileManagement fm = new FileManagement();
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while(isRunning){
            System.out.println("Welcome to Staff Management System");
            commands();
            String command = sc.nextLine();
            switch(command){
                case "1":
                    // Add employee;
                    em.addEmployee(); //creating employee
                    System.out.println("Would you like to add more employees? [Y/N]");
                    String input = sc.nextLine();
                    if (input.equalsIgnoreCase("Y")) {
                        em.addEmployee();
                        System.out.println("View employees?[Y][N]");
                        String answer = sc.nextLine();
                        if (answer.equalsIgnoreCase("Y")) {
                            fm.viewEmployees();
                            System.out.println("returning to the menu..");
                        }else{
                            System.out.println("Returning to the menu..");
                            start();
                        }
                    }else {
                        backToMenu(true);
                        return;
                    }
                case "2":
                    // Edit Employee
                    em.raiseSalary();
                    backToMenu(true);
                    return;
                case "3":
                    // List Employees
                    fm.viewEmployees();
                    backToMenu(true);
                    return;

                case "4":
                    // Fire Employee
                    em.fireEmployee();
                    backToMenu(true);
                    return;
                case "5":
                    // Exit menu
                    isRunning = false;
                    break;
            }
        }
    }
    public static void commands(){
        System.out.println("1.Add Employee");
        System.out.println("2.Edit Employee");
        System.out.println("3.List Employees");
        System.out.println("4.Fire Employee");
        System.out.println("5.Exit menu");
    }
    public static void backToMenu(boolean isRunning){
        Scanner sc = new Scanner(System.in);
        System.out.println("Return to the main menu?[Y][N]");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("N")) {
            isRunning = false;
        }else{
            start();
        }

    }
}
