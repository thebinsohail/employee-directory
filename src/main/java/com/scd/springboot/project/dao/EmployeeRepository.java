package com.scd.springboot.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scd.springboot.project.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// add a method to sort by last name
	public List<Employee> findAllByOrderByFirstNameAsc();
	

}
