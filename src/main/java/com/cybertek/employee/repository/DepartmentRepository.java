package com.cybertek.employee.repository;

import com.cybertek.employee.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    List<Department> findByDepartment(String department); //1

    List<Department> findByDivisionIs(String division); //3

    List<Department> findByDivisionEquals(String division); //3

    List<Department> findByDepartmentEndingWith(String department); //4

    List<Department> findByOrderByDepartment(); //6

    List<Department> findDistinctTop3ByDivisionContains(String division); //7


}
