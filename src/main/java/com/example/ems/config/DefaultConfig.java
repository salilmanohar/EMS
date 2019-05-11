package com.example.ems.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ems.entities.Employee;
import com.example.ems.entities.EmployeeRole;
import com.example.ems.repositories.EmployeeRepository;
import com.example.ems.utils.Enums.BloodGroup;
import com.example.ems.utils.Enums.Gender;
import com.example.ems.utils.Enums.Role;

@Configuration
public class DefaultConfig {

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public void addIfUserAbsent() {
		if (empRepo.findAll().size() == 0) {
			Employee emp = new Employee("Admin", "007", "Administrator", "abc@123.com", "9999999999",
					BloodGroup.O_POSITIVE, Gender.MALE, 35, "admin", passwordEncoder.encode("pass"));
			List<EmployeeRole> empRoles = new ArrayList<>();
			empRoles.addAll(Arrays.asList(new EmployeeRole(Role.ADMIN, emp), new EmployeeRole(Role.EMPLOYEE, emp)));
			emp.setEmpRoles(empRoles);
			empRepo.save(emp);
		}
	}

}
