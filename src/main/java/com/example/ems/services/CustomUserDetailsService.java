package com.example.ems.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ems.repositories.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return empRepo.findByUserName(username);
	}
}
