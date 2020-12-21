package com.cybertek.employee.repository;

import com.cybertek.employee.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByEmail(String email); //1

    List<Employee> findByFirstNameAndLastNameOrEmail(String firstName, String lastName, String email); //2

    List<Employee> findByFirstNameIsNot(String firstName);//3

    List<Employee> findByLastNameStartsWith(String lastName); //4

    List<Employee> findBySalaryLessThan(Integer salary); //5

    List<Employee> findBySalaryGreaterThan(Integer salary); //5

    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate); //5

    List<Employee> findBySalaryGreaterThanEqualOrderBySalaryDesc(Integer salary); //6

    List<Employee> findDistinctTop3BySalaryLessThan(Integer salary); //7

    List<Employee> findByEmailIsNull(); //6

    @Transactional
    void deleteByFirstName(String firstName); //8

    //NATIVE QUERY
    @Query(value = "select * from employees where email = :email", nativeQuery = true)
    List<Employee> testNativeQuery(@Param("email") String email);

    //JPQL
    @Query(value = "SELECT e FROM Employee e where e.email = :email")
    List<Employee> testJPQLQuery(@Param("email") String email);
}
