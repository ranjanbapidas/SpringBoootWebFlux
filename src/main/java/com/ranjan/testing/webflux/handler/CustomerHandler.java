package com.ranjan.testing.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.ranjan.testing.webflux.dao.CustomerDao;
import com.ranjan.testing.webflux.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

	@Autowired
	private CustomerDao dao ;
	
	public Mono<ServerResponse> loadCustomers(ServerRequest serverRequest){
		Flux<Customer> customerList = dao.getCustomerList();
		return ServerResponse.ok().body(customerList,Customer.class);
	}
	
	public Mono<ServerResponse> findCustomer(ServerRequest serverRequest){
		int custId = Integer.valueOf(serverRequest.pathVariable("input"));
		
		// dao.getCustomerList().filter(c->c.getId()==custId).take(1).single();
		Mono<Customer> customers = dao.getCustomerList().filter(c->c.getId()==custId).next();
		return ServerResponse.ok().body(customers, Customer.class);
	}
	
	public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest){
		Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);
		Mono<String> saveResponse = customerMono.map(dto->dto.getId()+":"+dto.getName());
		return ServerResponse.ok().body(saveResponse, String.class);
	}
	
}
