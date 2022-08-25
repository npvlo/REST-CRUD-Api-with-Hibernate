package oana.restcrudapi.hibernate.service;

import java.util.List;

import oana.restcrudapi.hibernate.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void deleteById(int id);
}
