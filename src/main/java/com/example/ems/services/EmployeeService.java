package com.example.ems.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ems.entities.Employee;
import com.example.ems.repositories.EmployeeRepository;
import com.example.ems.utils.Email;
import com.example.ems.utils.Enums.Role;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	EmailService emailService;

	public void saveEmployee(Employee employee) {
		String pass = RandomStringUtils.randomAlphanumeric(6);
		employee.setPassword(passwordEncoder.encode(pass));
		String textMsg = "Hi " + employee.getFullName() + ",\n\nYour credentials to login to Ems are: \nUsername : "
				+ employee.getUsername() + "\nLogin Password : " + pass
				+ "\nPlease reset your password after you successfully login.\n\nEMS-Team";
		emailService.sendPlainTextMail(
				new Email(Arrays.asList(employee.getEmailId()), Arrays.asList(), "Employee Login Password", textMsg));
		empRepo.save(employee);
	}

	public Employee getEmployeeById(String empId) {
		return empRepo.getByEmpId(empId);
	}

	public List<Employee> findAllEmployee() {
		return empRepo.findAll();
	}

	@Transactional
	public void removeEmployee(String empId) {
		empRepo.deleteByEmpId(empId);
	}

	public void resetPassword(Employee emp) {
		Employee existingEmp = Optional.ofNullable(SecurityContextHolder.getContext()).map(e -> e.getAuthentication())
				.map(e -> e.getPrincipal()).filter(e -> e instanceof Employee).map(Employee.class::cast).orElse(null);
		boolean checkpw = BCrypt.checkpw(emp.getPassword(), existingEmp.getPassword());
		if (checkpw) {
			existingEmp.setPassword(passwordEncoder.encode(emp.getNewPassword()));
			empRepo.save(existingEmp);
		} else {
			throw new RuntimeException("Incorrect Password");
		}

	}
	
	public int getEmployeeCountByRole(Role role) {
		return empRepo.getEmployeeCountByEmpRole(role);
	}
}
