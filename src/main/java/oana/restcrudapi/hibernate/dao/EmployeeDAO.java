package oana.restcrudapi.hibernate.dao;

import java.util.List;

import oana.restcrudapi.hibernate.entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> findAll();
	
	public Employee findById(int id);
	
	public void save(Employee employee);
	
	public void deleteById(int id);
}
