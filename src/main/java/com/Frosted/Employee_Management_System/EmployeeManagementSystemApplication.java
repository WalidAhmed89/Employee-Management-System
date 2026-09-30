package com.Frosted.Employee_Management_System;

import com.Frosted.Employee_Management_System.DataAccessObject.EmployeeDAO;
import com.Frosted.Employee_Management_System.entity.Employees;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(EmployeeDAO employeeDAO) {
        return runner -> {
            //createEmployee(employeeDAO);

            //createMultipleEmployees(employeeDAO);

            //findEmployeeByID(employeeDAO,2);

            //finAllEmployees(employeeDAO);

            //findEmployeeByDepartment(employeeDAO,"IT");

            //findEmployeeByLastName(employeeDAO,"Ahmed");

            //findEmployeesByJobTitle(employeeDAO,"Backend Developer");

            //findEmployeesWithSalaryGreaterThan(employeeDAO,10000);

            //updateEmployeeSalary(employeeDAO,1,30000);

            //deleteEmployee(employeeDAO,2);
        };
    }

    private void createEmployee(EmployeeDAO employeeDAO) {
        System.out.println("Creating Employee...");
        Employees Walid = new Employees(
                "Walid",
                "Ahmed",
                "walid.ahmed@example.com",
                "Backend Developer",
                25000.0,
                "IT"
        );
        employeeDAO.createEmployee(Walid);
        System.out.println("Employee was Created");
    }

    private void createMultipleEmployees(EmployeeDAO employeeDAO) {
        System.out.println("Creating Employees");
        List<Employees> employeesList = new ArrayList<>(List.of(new Employees(
                        "Walid",
                        "Ahmed",
                        "walid.ahmed@example.com",
                        "Backend Developer",
                        25000.0,
                        "IT"
                ),

                new Employees(
                        "Omar",
                        "Hassan",
                        "omar.hassan@example.com",
                        "Software Engineer",
                        22000.0,
                        "IT"
                ),

                new Employees(
                        "Ahmed",
                        "Mohamed",
                        "ahmed.mohamed@example.com",
                        "Frontend Developer",
                        20000.0,
                        "IT"
                ),

                new Employees(
                        "Youssef",
                        "Ali",
                        "youssef.ali@example.com",
                        "Database Administrator",
                        24000.0,
                        "IT"
                ),

                new Employees(
                        "Karim",
                        "Mahmoud",
                        "karim.mahmoud@example.com",
                        "HR Specialist",
                        16000.0,
                        "Human Resources"
                ),

                new Employees(
                        "Mariam",
                        "Ibrahim",
                        "mariam.ibrahim@example.com",
                        "Project Manager",
                        30000.0,
                        "Management"
                ),

                new Employees(
                        "Nour",
                        "Khaled",
                        "nour.khaled@example.com",
                        "UI/UX Designer",
                        18000.0,
                        "Design"
                ),

                new Employees(
                        "Mostafa",
                        "Samir",
                        "mostafa.samir@example.com",
                        "QA Engineer",
                        19000.0,
                        "Quality Assurance"
                ),

                new Employees(
                        "Salma",
                        "Adel",
                        "salma.adel@example.com",
                        "Marketing Specialist",
                        17000.0,
                        "Marketing"
                ),

                new Employees(
                        "Hossam",
                        "Tarek",
                        "hossam.tarek@example.com",
                        "DevOps Engineer",
                        27000.0,
                        "IT"
                ),

                new Employees(
                        "Farah",
                        "Nabil",
                        "farah.nabil@example.com",
                        "Financial Analyst",
                        21000.0,
                        "Finance"
                )));

        for(Employees employee : employeesList){
            employeeDAO.createEmployee(employee);
        }
        System.out.println("Employees Created");
    }

    private void findEmployeeByID(EmployeeDAO employeeDAO, int id) {
        System.out.println("Searching for the employee..");
        Employees employee = employeeDAO.findEmployeeByID(id);
        if (employee == null) {
            System.out.println("Can't find this employee,Check the ID is correct");
            return;
        }
        System.out.println("We found this Employee: " + employee);
    }

    private void finAllEmployees(EmployeeDAO employeeDAO) {
        System.out.println("Finding all employees...");
        List<Employees> employees = employeeDAO.findAllEmployees();
        if (employees.size() == 1) {
            System.out.println(employees.get(0));
            return;
        }
        for (Employees employee : employees) {
            System.out.println(employee);
        }
    }

    private void findEmployeeByDepartment(EmployeeDAO employeeDAO, String department) {
        System.out.println("Finding employees by Departments...");
        List<Employees> employees = employeeDAO.findEmployeesByDepartment(department);
        if (employees.isEmpty()) {
            System.out.println("Can't find this employees,Check the Department is correct");
            return;
        }
        if (employees.size() == 1) {
            System.out.println(employees.get(0));
            return;
        }
        for (Employees employee : employees) {
            System.out.println(employee);
        }
    }

    private void findEmployeesByJobTitle(EmployeeDAO employeeDAO, String jobTitle) {
        System.out.println("Finding employees by jobTitle...");
        List<Employees> employees = employeeDAO.findEmployeesByJobTitle(jobTitle);
        if (employees.isEmpty()) {
            System.out.println("Can't find this employees,Check the jobTitle is correct");
            return;
        }
        if (employees.size() == 1) {
            System.out.println(employees.get(0));
            return;
        }
        for (Employees employee : employees) {
            System.out.println(employee);
        }
    }

    private void findEmployeeByLastName(EmployeeDAO employeeDAO, String lastName) {
        System.out.println("Finding employees by lastName...");
        List<Employees> employees = employeeDAO.findEmployeeByLastName(lastName);
        if (employees.isEmpty()) {
            System.out.println("Can't find this employees,Check the lastName is correct");
            return;
        }
        if (employees.size() == 1) {
            System.out.println(employees.get(0));
            return;
        }
        for (Employees employee : employees) {
            System.out.println(employee);
        }
    }

    private void findEmployeesWithSalaryGreaterThan(EmployeeDAO employeeDAO, int number) {
        System.out.println("Finding employees salary greater than " + number + "...");
        List<Employees> employees = employeeDAO.findEmployeesWithSalaryGreaterThan(number);
        if (employees.isEmpty()) {
            System.out.println("Can't find this employees salary greater than " + number);
            return;
        }
        if (employees.size() == 1) {
            System.out.println(employees.get(0));
            return;
        }
        for (Employees employee : employees) {
            System.out.println(employee);
        }
    }

    private void updateEmployeeSalary(EmployeeDAO employeeDAO, int id, double salary) {
        System.out.println("Updating Employee...");
        if (employeeDAO.findEmployeeByID(id) == null) {
            System.out.println("Can't find this employee,Check the ID is correct");
            return;
        }
        System.out.println(employeeDAO.updateEmployeeSalary(id, salary));
    }

    private void deleteEmployee(EmployeeDAO employeeDAO, int id) {
        System.out.println(employeeDAO.deleteEmployee(id));
    }

}
