package com.example.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ems.entities.Employee;
import com.example.ems.utils.Enums.Role;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public Employee getByEmpId(String empId);

	public void deleteByEmpId(String empId);
	
	public Employee findByUserName(String username);
	
	@Query("select count(e) from Employee e INNER JOIN e.empRoles r where r.role=:role")
	public int getEmployeeCountByEmpRole(@Param("role") Role role);
}
