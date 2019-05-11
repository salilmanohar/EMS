package com.example.ems.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ems.entities.Employee;
import com.example.ems.services.EmployeeService;
import com.example.ems.utils.Enums.Role;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@RequestMapping(value = "/admin/save/employee", method = RequestMethod.POST)
	public void saveEmployee(@RequestBody Employee employee) {
		empService.saveEmployee(employee);
	}

	@RequestMapping(value = "/get/employee/{empId}", method = RequestMethod.GET)
	public Employee getEmployeeById(@PathVariable("empId") String empId) {
		return empService.getEmployeeById(empId);
	}

	@RequestMapping(value = "/admin/get/allEmployee", method = RequestMethod.GET)
	public List<Employee> findAllEmployee() {
		return empService.findAllEmployee();
	}

	@RequestMapping(value = "/admin/delete/employee/{empId}")
	public void removeEmployee(@PathVariable("empId") String empId) {
		empService.removeEmployee(empId);
	}

	@RequestMapping(value = "/getLoggedInUser", method = RequestMethod.GET)
	public Object getLoggedInUser() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@RequestMapping(value = "/employee/resetPassword", method = RequestMethod.POST) 
	public void resetPassword(@RequestBody Employee emp) {
		empService.resetPassword(emp);
	}
	
	@RequestMapping(value = "/employee/getEmployeeCount", method = RequestMethod.GET)
	public int getEmployeeCountByRole(@RequestParam("role") Role role) {
		return empService.getEmployeeCountByRole(role);
	}
	
}
