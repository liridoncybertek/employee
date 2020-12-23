package com.cybertek.employee.repository;

import com.cybertek.employee.entities.Employee;
import com.cybertek.employee.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // List all employee with a specific email?
    List<Employee> findByEmail(String email);

    // List all employees with first name and last name also list all employees with a certain email?
    List<Employee> findByFirstNameAndLastNameOrEmail(String firstName, String lastName, String email);

    // List all employees except the one with specific first name?
    List<Employee> findByFirstNameIsNot(String firstName);

    // List all employees with certain last name that has a starting pattern?
    List<Employee> findByLastNameStartsWith(String lastName);

    // List all employees where salary is lower than a specific salary?
    List<Employee> findBySalaryLessThan(Integer salary);

    // List all employees where salary is higher than a specific salary?
    List<Employee> findBySalaryGreaterThan(Integer salary);

    // List all employees inside a specific range of hire date?
    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);

    // List all employees in order with salary higher and equal than a specific salary?
    List<Employee> findBySalaryGreaterThanEqualOrderBySalaryDesc(Integer salary);

    // List top unique 3 employees with the lowest salary than a specific salary?
    List<Employee> findDistinctTop3BySalaryLessThan(Integer salary);

    // List all employees where email is not present?
    List<Employee> findByEmailIsNull();

    //Remove employee with a certain first name?
    @Transactional
    void deleteByFirstName(String firstName);

    //NATIVE QUERY
    @Query(value = "select * from employees where email = :email", nativeQuery = true)
    List<Employee> testNativeQuery(@Param("email") String email);

    //JPQL
    @Query(value = "SELECT e FROM Employee e")
    List<Employee> testJPQLQuery();

    @Query(value = "SELECT * FROM employees WHERE salary BETWEEN :start AND :end", nativeQuery = true)
    List<Employee> fetchAllBetweenSalaries(@Param("start") Integer start, @Param("end") Integer end);

    @Query(value = "SELECT concat(e.first_name, ' ', e.last_name), d.department, r.region " +
            " FROM employees e JOIN departments d on e.department = d.department " +
            " JOIN regions r on e.region_id = r.id", nativeQuery = true)
    List<Tuple> readNameDepartmentAndRegion();

    @Query(value = "SELECT concat(e.first_name, ' ', e.last_name), d.department, r.region " +
            " FROM employees e JOIN departments d on e.department = d.department " +
            " JOIN regions r on e.region_id = r.id WHERE r.country = :country", nativeQuery = true)
    List<Tuple> fetchByCountryRegion(@Param("country") String country);


    @Query(value = "SELECT * FROM employees WHERE email IS NULL", nativeQuery = true)
    List<Employee> fetchAllEmployeeWithoutEmail();

    @Query(value = "SELECT e from Employee  e WHERE e.gender = :gender")
    List<Employee> fetchByGender(@Param("gender") Gender gender);


    @Query(value = "SELECT cast(row_to_json(employee) AS text) AS result FROM( " +
            "SELECT json_agg((json_build_object('name', concat(e.first_name, ' ', e.last_name), 'department', d.department, 'region', r.region))) as \"employeeList\" " +
            " FROM employees e JOIN departments d on e.department = d.department " +
            " JOIN regions r on e.region_id = r.id WHERE r.country = :country) employee", nativeQuery = true)
    List<Tuple> customNativeQuery(@Param("country") String country);


    @Query("UPDATE Employee e SET e.email = 'admin@email.com' WHERE e.id = :id")
    Employee updateEmployee(@Param("id") Integer id);

}
