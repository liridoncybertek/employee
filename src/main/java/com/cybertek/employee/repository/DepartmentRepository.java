package com.cybertek.employee.repository;

import com.cybertek.employee.entities.Department;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    // List all departments with a specific department name?
    List<Department> findByDepartment(String department);

    // List all departments with a specific division?
    List<Department> findByDivisionIs(String division);

    // List all departments with a specific division?
    List<Department> findByDivisionEquals(String division);

    // List all departments with a specific ending department pattern?
    List<Department> findByDepartmentEndingWith(String department);

    // List all departments in order with department?
    List<Department> findByOrderByDepartment();

    // List top 3 unique departments with a specific division that contains a certain pattern?
    List<Department> findDistinctTop3ByDivisionContains(String division);


    //NATIVE QUERIES...
    @Query(value = "SELECT * FROM departments",nativeQuery = true)
    List<Department> fetchAll();

    @Query(value = "SELECT d FROM Department d")
    List<Department> fetchAllJPQL();

    @Query(value = "SELECT * FROM departments WHERE division ILIKE concat('%', :division, '%')", nativeQuery = true)
    List<Department> fetchAllByDivision(@Param("division") String division);

    @Query(value = "SELECT d FROM Department d")
    List<Department> orderByDivisionJPQL(Sort sort);

    @Query(value = "SELECT * FROM departments ORDER BY department DESC", nativeQuery = true)
    List<Department> orderByDepartment();

}
