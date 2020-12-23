package com.cybertek.employee.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedNativeQuery(name = "Department.countAllDepartments", query = "SELECT count(*) FROM departments")
public class Department {

    @Id
    private String department;

    private String division;
}
