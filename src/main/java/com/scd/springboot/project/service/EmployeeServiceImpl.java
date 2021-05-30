package com.scd.springboot.project.service;

import java.util.List;
import java.util.Optional;
import com.scd.springboot.project.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scd.springboot.project.entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private int entries;
	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
		synchronized (this) {
			entries = (int) employeeRepository.count();
	
		}

	}

	public synchronized int getEntries() {
		return this.entries;
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	@Override
	public Employee findById(int theId) throws RuntimeException {
		Optional<Employee> result = employeeRepository.findById(theId);

		Employee theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			// we didn't find the employee
			//
			throw new RuntimeException("Did not find employee id - " + theId);
		}

		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);

	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}
