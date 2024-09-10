package com.ranjan.testing.webflux.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.ranjan.testing.webflux.dto.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {
	
	private static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Customer> getCustomer(){
		return IntStream.rangeClosed(1, 10)
				.peek(CustomerDao::sleepExecution)
				.peek(i->System.out.println("Processing Count : "+i))
				.mapToObj(i->new Customer(i,"Customer"+i))
				.toList();
		
	}
	
	public Flux<Customer> getCustomerStream(){
		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i-> System.out.println("Processing Count : "+i))
				.map(i->new Customer(i, "customer "+i));
		
		
	}
	
	public Flux<Customer> getCustomerList(){
		return Flux.range(1, 50)
				.doOnNext(i-> System.out.println("Processing Count : "+i))
				.map(i->new Customer(i, "customer "+i));
		
		
	}
	
	
}
