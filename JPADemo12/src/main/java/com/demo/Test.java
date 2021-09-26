package com.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("JPADemo12");
		EntityManager manager=factory.createEntityManager();
	Employee emp=new Employee();
	emp.setId(1);
	emp.setName("vishal");
	emp.setCity("Mumbai");
	emp.setSalary(1000.00);
	
	manager.getTransaction().begin();
	manager.persist(emp);
	manager.getTransaction().commit();
	
	}

}
