package com.Frosted.Employee_Management_System.DataAccessObject;

import com.Frosted.Employee_Management_System.entity.Employees;

import java.util.List;

public interface EmployeeDAO {
    void createEmployee(Employees employee);
    Employees findEmployeeByID(int id);
    List<Employees> findAllEmployees();
    List<Employees> findEmployeesByDepartment(String department);
    List<Employees> findEmployeesByJobTitle(String jobTitle);
    List<Employees> findEmployeeByLastName(String lastName);
    List<Employees> findEmployeesWithSalaryGreaterThan(int number);
    String updateEmployeeSalary(int id,double salary);
    String deleteEmployee(int id);
}
