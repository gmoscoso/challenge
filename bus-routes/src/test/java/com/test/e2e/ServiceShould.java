package com.test.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

import com.test.RouteFinderServiceFactory;
import com.test.interfaces.RouteFinder;

public class ServiceShould {


	@Test
	public void parseAFileAndFindARoute(){
		String GOEURO_BUS_FILE = "./src/test/resources/busRoutes.txt";
		
		RouteFinder app = new RouteFinderServiceFactory().create();
		
		app.start(GOEURO_BUS_FILE);
		
		Optional<Integer> route = app.findRouteBetween(3, 6);
		assertThat(route.isPresent()).isTrue();
		assertThat(route.get()).isEqualTo(1);
	}
}
