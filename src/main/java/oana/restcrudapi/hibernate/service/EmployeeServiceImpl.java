package oana.restcrudapi.hibernate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import oana.restcrudapi.hibernate.dao.EmployeeDAO;
import oana.restcrudapi.hibernate.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO employeeDAO;
	
	public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int id) {
		
		return employeeDAO.findById(id);
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		
		employeeDAO.save(employee);

	}

	@Override
	@Transactional
	public void deleteById(int id) {
		
		employeeDAO.deleteById(id);

	}

}
