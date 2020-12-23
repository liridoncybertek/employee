package com.cybertek.employee.entities;

import com.cybertek.employee.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({@NamedQuery(name = "Employee.findAllNamedQuery", query = "SELECT e FROM Employee e"),
               @NamedQuery(name = "Employee.findAllWithEmails", query = "SELECT e FROM Employee e WHERE e.email IS NOT NULL"),
               @NamedQuery(name = "Employee.findWithSalaryGreaterThan", query = "SELECT e from Employee e where e.salary > :salary")})
public class Employee extends BaseEntity<Integer>{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer salary;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "department")
    private Department department;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "region_id")
    private Region region;

    public Employee(String email) {
        this.email = email;
    }
}
