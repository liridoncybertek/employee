package com.cybertek.employee;

import com.cybertek.employee.entities.Employee;
import com.cybertek.employee.repository.DepartmentRepository;
import com.cybertek.employee.repository.EmployeeRepository;
import com.cybertek.employee.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class EmployeeApplication {

	@Autowired
	private  EmployeeRepository employeeRepository;
	@Autowired
	private  DepartmentRepository departmentRepository;
	@Autowired
	private  RegionRepository regionRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

	@PostConstruct
	public void testEmployees() {
		System.out.println("----------Employee start -------");
		System.out.println("findByEmail: " + employeeRepository.findByEmail("npointona@vistaprint.com"));
		System.out.println("findByFirstNameAndLastNameOrEmail: " + employeeRepository.findByFirstNameAndLastNameOrEmail("Berrie", "Manueau", "ssymonds2@hhs.gov"));
		System.out.println("findByFirstNameIsNot: " + employeeRepository.findByFirstNameIsNot("Dayle"));
		System.out.println("findByLastNameStartsWith: " + employeeRepository.findByLastNameStartsWith("Dav"));
		System.out.println("findBySalaryLessThan: " + employeeRepository.findBySalaryLessThan(55307));
		System.out.println("findByHireDateBetween: " + employeeRepository.findByHireDateBetween(LocalDate.of(2010, 6, 1), LocalDate.of(2013, 12, 30)));
		System.out.println("findBySalaryGreaterThan: " + employeeRepository.findBySalaryGreaterThan(119674));
		System.out.println("findBySalaryGreaterThanEqualOrderBySalaryDesc: " + employeeRepository.findBySalaryGreaterThanEqualOrderBySalaryDesc(119674));
		System.out.println("findDistinctTop3BySalaryLessThan: " + employeeRepository.findDistinctTop3BySalaryLessThan(119674));
		System.out.println("findByEmailIsNull: " + employeeRepository.findByEmailIsNull());
		System.out.println("deleteByFirstName: ");
		employeeRepository.deleteByFirstName("Niles");
		System.out.println("----------Employee end -------");
	}

	@PostConstruct
	public void testDepartments() {
		System.out.println("----------Department start -------");
		System.out.println("findByDepartment: "+ departmentRepository.findByDepartment("Furniture"));
		System.out.println("findByDivisionIs: "+ departmentRepository.findByDivisionIs("Kids"));
		System.out.println("findByDivisionEquals: "+ departmentRepository.findByDivisionEquals("Hardware"));
		System.out.println("findByDepartmentEndingWith: "+ departmentRepository.findByDepartmentEndingWith("vies"));
		System.out.println("findDistinctTop3ByDivisionContains: "+ departmentRepository.findDistinctTop3ByDivisionContains("Hea"));
		System.out.println("findByOrderByDepartment: "+ departmentRepository.findByOrderByDepartment());
		System.out.println("----------Department end -------");

	}

	@PostConstruct
	public void testRegions() {
		System.out.println("----------Regions start -------");
		System.out.println("findDistinctByCountry: " + regionRepository.findDistinctByCountry("Asia"));
		System.out.println("findByCountryOrRegion: " + regionRepository.findByCountryOrRegion("Asia", "Southwest"));
		System.out.println("findByCountryContaining: " + regionRepository.findByCountryContaining("United"));
		System.out.println("findByCountryContainingOrderByCountry: " + regionRepository.findByCountryContainingOrderByCountry("United"));
		System.out.println("findTop3ByCountry: " + regionRepository.findTop3ByCountry("Canada"));
		System.out.println("----------Regions end -------");
	}

}
