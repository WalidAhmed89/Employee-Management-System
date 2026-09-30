package com.Frosted.Employee_Management_System.DataAccessObject;

import com.Frosted.Employee_Management_System.entity.Employees;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeesDAOImpl implements EmployeeDAO {
    private final EntityManager entityManager;

    public EmployeesDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void createEmployee(Employees employee) {
        entityManager.persist(employee);
    }

    @Override
    public Employees findEmployeeByID(int id) {
        return entityManager.find(Employees.class, id);
    }

    @Override
    public List<Employees> findAllEmployees() {
        TypedQuery<Employees> employees = entityManager.createQuery("FROM Employees", Employees.class);
        return employees.getResultList();
    }

    @Override
    public List<Employees> findEmployeesByDepartment(String department) {
        TypedQuery<Employees> employees = entityManager.createQuery("FROM Employees WHERE department =:department", Employees.class);
        employees.setParameter("department", department);
        return employees.getResultList();
    }

    @Override
    public List<Employees> findEmployeesByJobTitle(String jobTitle) {
        TypedQuery<Employees> employees = entityManager.createQuery("FROM Employees WHERE jobTitle =:jobTitle", Employees.class);
        employees.setParameter("jobTitle", jobTitle);
        return employees.getResultList();
    }

    @Override
    public List<Employees> findEmployeeByLastName(String lastName) {
        TypedQuery<Employees> employees = entityManager.createQuery("FROM Employees WHERE lastName =:lastName", Employees.class);
        employees.setParameter("lastName", lastName);
        return employees.getResultList();
    }

    @Override
    public List<Employees> findEmployeesWithSalaryGreaterThan(int number) {
        TypedQuery<Employees> employees = entityManager.createQuery("FROM Employees WHERE salary >:number", Employees.class);
        employees.setParameter("number", number);
        return employees.getResultList();
    }

    @Override
    @Transactional
    public String updateEmployeeSalary(int id, double salary) {
        int numsOfUpdatingEmployees = entityManager.createQuery("UPDATE Employees SET salary=:salary WHERE id=:id")
                .setParameter("salary", salary)
                .setParameter("id", id)
                .executeUpdate();
        return numsOfUpdatingEmployees+" Employees Updated";
    }

    @Override
    @Transactional
    public String deleteEmployee(int id) {
        Employees employee = entityManager.find(Employees.class,id);
        if(employee == null){
            return "Employee not found";
        }
        entityManager.remove(employee);
        return "Employee Deleted.";
    }
}