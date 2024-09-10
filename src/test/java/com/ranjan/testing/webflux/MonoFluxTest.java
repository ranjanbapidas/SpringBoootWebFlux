package com.ranjan.testing.webflux;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

	//@Test
	public void testMono() {
		Mono<?> monoString = Mono.just("javatechie")
				.then(Mono.error(new RuntimeException("Exception Occured..."))).log();
		monoString.subscribe(System.out::println,t ->System.out.println(t.getMessage()) );
	}
	
	@Test
	public void testFlux() {
		Flux<String> fluxString =Flux.just("Spring","Spring Boot","Hibernate","Rest API")
				.concatWithValues("Microservices")
				.concatWith(Flux.error(new RuntimeException("There is a Exception Occured..."))).log();
		fluxString.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
		}
}
