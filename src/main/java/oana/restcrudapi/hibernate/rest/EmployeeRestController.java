package oana.restcrudapi.hibernate.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oana.restcrudapi.hibernate.dao.EmployeeDAO;
import oana.restcrudapi.hibernate.entity.Employee;
import oana.restcrudapi.hibernate.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	//inject employee service
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	//add mapping for GET - return a single employee
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		
		Employee employee = employeeService.findById(employeeId);
		
		if(employee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		return employee;
	}
	
	//add mapping for POST - adding a new employee
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee employee) {
		
		//in case the user pass an id in JSON - set it to 0
		//this forces a save of new item instead of an update
		employee.setId(0);
		employeeService.save(employee);
		return employee;
	}
	
	//add mapping for PUT - update existing employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		
		employeeService.save(employee);
		
		return employee;
	}
	
	//add mapping for DELETE /employees/{id} - delete employee
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee tempEmployee = employeeService.findById(employeeId);
		
		//throw exception if null
		if(tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return "Deleted the employee with id: " + employeeId;
	}
	
}
