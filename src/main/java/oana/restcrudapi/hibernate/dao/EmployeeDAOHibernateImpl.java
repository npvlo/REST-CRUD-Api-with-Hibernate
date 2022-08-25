package oana.restcrudapi.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import oana.restcrudapi.hibernate.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{
	
	//define field for entitymanager
	private EntityManager entityManager;
	
	//setup constructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	//@Transactional - removing this annotation from the DAO because Service will handle it
	public List<Employee> findAll() {
		//get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//create a query
		Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
		
		//execute query and get result list
		List<Employee> employees = query.getResultList();
		
		//return the results
		return employees;
	}

	@Override
	public Employee findById(int id) {
		
		//get hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//get the employee
		Employee employee = currentSession.get(Employee.class, id);
		
		//return the employee
		return employee;
	}

	@Override
	public void save(Employee employee) {
		
		//get hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		//save the employee
		currentSession.saveOrUpdate(employee);
	}

	@Override
	public void deleteById(int id) {
		
		//get hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
				
		//delete object with primary key
		Query query = currentSession.createQuery("delete from Employee where id=:employeeId");
		query.setParameter("employeeId", id);
				
		query.executeUpdate();
		
	}

}
