package com.ranjan.testing.webflux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranjan.testing.webflux.dao.CustomerDao;
import com.ranjan.testing.webflux.dto.Customer;

import reactor.core.publisher.Flux;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao dao;
	
	
	public List<Customer> loadAllCustomers(){
		long start = System.currentTimeMillis();
		List<Customer> customers = dao.getCustomer();
		long end= System.currentTimeMillis();
		System.out.println("Total Execution time= "+(end-start));
		return customers;
	}
	
	public Flux<Customer> loadAllCustomersStream(){
		long start = System.currentTimeMillis();
		Flux<Customer> customers = dao.getCustomerStream();
		long end= System.currentTimeMillis();
		System.out.println("Total Execution time= "+(end-start));
		return customers;
	}
	
}
