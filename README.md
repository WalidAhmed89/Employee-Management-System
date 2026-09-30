# Employee Management System

## Overview

This project is a Java Spring Boot application built from scratch as a learning project.

The main purpose of the project is not to represent a production-ready employee management system. It was created to practice and reinforce the concepts I learned while studying Spring Boot, Spring IoC, Dependency Injection, JPA, Hibernate, PostgreSQL, JPQL, `EntityManager`, DAO design, transactions, and CRUD operations.

I built the project step by step to understand how these technologies work together in a real application rather than simply copying a ready-made implementation.

The current version is a console-based application. It uses `CommandLineRunner` to execute and test the different database operations when the Spring Boot application starts.

## Project Goals

The project was created to practice:

- Building a Spring Boot application from scratch
- Understanding Spring IoC and Dependency Injection
- Creating and managing Spring Beans
- Using constructor injection
- Mapping Java classes to database tables with JPA
- Working with Hibernate as the JPA implementation
- Using `EntityManager` for persistence operations
- Writing JPQL queries
- Implementing the DAO pattern
- Performing CRUD operations
- Working with PostgreSQL
- Using transactions for database write operations
- Searching and filtering database records
- Handling cases where an employee does not exist
- Structuring a project into separate layers
- Using Lombok to reduce boilerplate code

## Technologies Used

- Java
- Spring Boot
- Spring IoC
- Dependency Injection
- JPA
- Hibernate
- PostgreSQL
- JPQL
- EntityManager
- Lombok
- Maven
- IntelliJ IDEA
- DataGrip

## Application Architecture

The current project follows a simple DAO-based architecture:

```text
CommandLineRunner
       |
       v
   EmployeeDAO
       |
       v
 EmployeesDAOImpl
       |
       v
   EntityManager
       |
       v
      JPA
       |
       v
    Hibernate
       |
       v
 PostgreSQL
```

### How the flow works

`CommandLineRunner` starts the application logic and calls methods from the DAO interface.

`EmployeeDAO` defines the database operations that the application can perform.

`EmployeesDAOImpl` contains the actual data-access implementation. It receives an `EntityManager` through constructor injection and uses it to communicate with the persistence layer.

JPA provides the persistence API, while Hibernate is responsible for implementing that API and translating persistence operations into SQL.

PostgreSQL stores the actual employee data.

## Project Structure

```text
src
└── main
    └── java
        └── com.Frosted.Employee_Management_System
            ├── DataAccessObject
            │   ├── EmployeeDAO.java
            │   └── EmployeesDAOImpl.java
            │
            ├── entity
            │   └── Employees.java
            │
            └── EmployeeManagementSystemApplication.java
```

## Employee Entity

The `Employees` class represents an employee record in the database.

It is mapped as a JPA entity using `@Entity` and is mapped to the `employees` table using `@Table`.

The entity contains:

| Field | Type | Description |
|---|---|---|
| `id` | `int` | Unique employee identifier |
| `firstName` | `String` | Employee first name |
| `lastName` | `String` | Employee last name |
| `email` | `String` | Employee email |
| `jobTitle` | `String` | Employee job title |
| `salary` | `double` | Employee salary |
| `department` | `String` | Employee department |

The database column names are explicitly mapped using `@Column`, for example:

```java
@Column(name = "first_name")
private String firstName;
```

The primary key uses:

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
```

so the database can generate the employee ID.

Lombok is also used for the no-argument constructor, getters, setters, and `toString()`.

## DAO Layer

The project uses the Data Access Object pattern to separate database operations from the rest of the application.

The `EmployeeDAO` interface defines the operations:

- Create an employee
- Find an employee by ID
- Find all employees
- Find employees by department
- Find employees by job title
- Find employees by last name
- Find employees whose salary is greater than a specific value
- Update an employee salary
- Delete an employee

The interface acts as a contract for the data-access layer.

## DAO Implementation

`EmployeesDAOImpl` implements `EmployeeDAO`.

It is registered as a Spring Bean using:

```java
@Repository
```

The class receives `EntityManager` through constructor injection:

```java
public EmployeesDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
}
```

This allowed me to practice Dependency Injection instead of creating the `EntityManager` manually.

## CRUD Operations

### Create

Employees are created using:

```java
entityManager.persist(employee);
```

The create operation is executed inside a transaction using `@Transactional`.

### Read

The project supports several read operations.

Find one employee by ID:

```java
entityManager.find(Employees.class, id);
```

Find all employees:

```java
FROM Employees
```

Find by department:

```java
FROM Employees WHERE department = :department
```

Find by job title:

```java
FROM Employees WHERE jobTitle = :jobTitle
```

Find by last name:

```java
FROM Employees WHERE lastName = :lastName
```

Find employees with a salary greater than a specific value:

```java
FROM Employees WHERE salary > :number
```

These queries are JPQL queries, so they work with the entity class and its Java fields rather than directly using database table and column names.

## Update

The project includes an operation for updating an employee salary.

It uses a JPQL update query:

```java
UPDATE Employees
SET salary = :salary
WHERE id = :id
```

The number of affected employees is returned so the application can report the result.

The update method is marked with `@Transactional` because it modifies database data.

## Delete

The delete operation first searches for the employee by ID.

If the employee does not exist, the application returns:

```text
Employee not found
```

If the employee exists, it is removed using:

```java
entityManager.remove(employee);
```

The delete operation is also transactional.

## JPQL

One of the main concepts practiced in this project is JPQL.

JPQL is different from SQL because it operates on JPA entities and their Java attributes.

For example:

```java
FROM Employees
```

refers to the `Employees` entity.

It does not directly refer to the PostgreSQL table name.

Similarly:

```java
WHERE lastName = :lastName
```

uses the Java entity field `lastName`.

This was an important distinction while learning JPA and Hibernate.

## Transactions

Database write operations are marked with `@Transactional`.

The project currently uses transactions for:

- Creating employees
- Updating salaries
- Deleting employees

This ensures that these operations are executed inside a transaction managed by the application.

## CommandLineRunner

The application currently uses `CommandLineRunner` as a simple way to test the DAO operations when Spring Boot starts.

The runner receives `EmployeeDAO` through Dependency Injection:

```java
@Bean
public CommandLineRunner commandLineRunner(EmployeeDAO employeeDAO)
```

Different test methods are available in the application class.

Examples include:

- Creating one employee
- Creating multiple employees
- Finding an employee by ID
- Finding all employees
- Searching by department
- Searching by last name
- Searching by job title
- Searching by salary
- Updating a salary
- Deleting an employee

The methods are currently commented out inside the runner so that individual operations can be enabled and tested when needed.

## Sample Data

The project contains sample employee records covering several departments and job titles.

Examples include:

- Backend Developer
- Software Engineer
- Frontend Developer
- Database Administrator
- HR Specialist
- Project Manager
- UI/UX Designer
- QA Engineer
- Marketing Specialist
- DevOps Engineer
- Financial Analyst

The sample data is used to test different search and filtering operations.

## Error Handling and Validation

The current project includes basic application-level handling for missing employees.

For example, when searching by ID, the result is checked for `null` before printing the employee.

Search operations returning lists also check whether the result is empty before displaying the result.

The project intentionally keeps this handling simple because the primary goal of this version is learning JPA, Hibernate, DAO, and database operations.

More advanced validation and exception handling can be added in a future version.

## What I Learned From This Project

This project was built to turn the concepts I studied into actual practice.

The main concepts I practiced are:

### Spring Boot

- Creating a Spring Boot application
- Using `@SpringBootApplication`
- Running application logic with `CommandLineRunner`

### Spring IoC and Dependency Injection

- Understanding Spring-managed objects
- Creating Beans
- Injecting dependencies
- Constructor injection
- Using `@Repository`

### JPA

- Creating entities
- Mapping fields to database columns
- Primary keys
- Generated IDs
- EntityManager
- Persisting entities
- Finding entities
- Removing entities
- JPQL queries

### Hibernate

- Understanding Hibernate as a JPA implementation
- Allowing Hibernate to handle ORM and SQL generation
- Working with entity-based queries

### DAO Pattern

- Separating data-access operations from application logic
- Defining a DAO interface
- Implementing the interface
- Keeping persistence logic inside the DAO implementation

### Transactions

- Understanding when database modifications require transactions
- Using `@Transactional` for create, update, and delete operations

### PostgreSQL

- Connecting the Spring Boot application to PostgreSQL
- Working with a relational database
- Storing and querying employee records

### Lombok

- Reducing boilerplate code
- Generating getters, setters, constructors, and `toString()`

## Why This Project Was Built

This project is primarily a learning project.

I created it from scratch after studying the related Spring Boot and JPA concepts. The objective was to understand the concepts by implementing them myself and dealing with the problems that appeared during development.

Instead of starting directly with a large REST API, I kept this version focused on the persistence layer and DAO pattern.

This allowed me to concentrate on understanding:

```text
Spring
  -> Dependency Injection
  -> Beans
  -> DAO
  -> JPA
  -> EntityManager
  -> Hibernate
  -> JPQL
  -> Transactions
  -> PostgreSQL
```

The project therefore represents a learning milestone in my transition toward Spring Boot backend development.

## Current Scope

The current version is intentionally limited.

It does not currently include:

- REST Controllers
- Service Layer
- DTOs
- Spring Data JPA repositories
- Spring Security
- JWT authentication
- Validation framework
- Global exception handling
- Pagination
- API documentation
- Docker
- Frontend

These are possible future additions rather than missing requirements for the current learning version.

## Future Direction

The next natural step for this project is to evolve it into a REST API.

A future version can introduce:

```text
Client
   |
   v
REST Controller
   |
   v
Service Layer
   |
   v
DAO / Repository
   |
   v
JPA / Hibernate
   |
   v
PostgreSQL
```

This would allow the project to move from a console-based learning application toward a backend REST API that can be tested with tools such as Postman.

## Project Status

This project is considered a completed learning project for the concepts it was designed to practice.

The code was written from scratch for learning purposes, and the structure reflects the stage of the Spring Boot learning path at which it was created.

The focus was understanding the technologies and their relationships rather than maximizing the number of features.

## Author

**Walid Ahmed**

GitHub: `WalidAhmed89`
