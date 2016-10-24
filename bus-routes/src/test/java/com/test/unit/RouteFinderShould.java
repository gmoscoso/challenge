package com.test.unit;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;

import com.test.DirectBusRoutesService;
import com.test.interfaces.RouteFinder;
import com.test.interfaces.RouteParser;
import com.test.unit.util.RouteParserMock;

public class RouteFinderShould {

	@Test
	public void givenARouteFindADirectRouteBetweenItsStations() {
		RouteParser mockRouteParser = new RouteParserMock()
				.withStationAndRoutes(0, asList(1))
				.withStationAndRoutes(1, asList(1))
				.build();

		RouteFinder routeFinder = new DirectBusRoutesService(mockRouteParser);
		routeFinder.start(null);
		
		Optional<Integer> route = routeFinder.findRouteBetween(0, 1);
		
		assertThat(route.isPresent()).isTrue();
		assertThat(route.get()).isEqualTo(1);
	}

	@Test
	public void returnEmptyWhenNoRouteBetweenStations() {
		
		RouteParser mockRouteParser = new RouteParserMock()
				.withStationAndRoutes(0, asList(1))
				.withStationAndRoutes(1, asList(2))
				.withStationAndRoutes(2, asList(1,2))
				.build();
		
		RouteFinder routeFinder = new DirectBusRoutesService(mockRouteParser);
		
		Optional<Integer> route = routeFinder.findRouteBetween(0, 1);
		
		assertThat(route.isPresent()).isFalse();
	}
	
	@Test
	public void retrunEmptyWhenAStationDoesNotExist() {
		RouteParser mockRouteParser = new RouteParserMock()
				.withStationAndRoutes(0, asList(1, 2))
				.withStationAndRoutes(1, asList(1))
				.build();
		
		RouteFinder routeFinder = new DirectBusRoutesService(mockRouteParser);
		
		Optional<Integer> route = routeFinder.findRouteBetween(5, 6);
		
		assertThat(route.isPresent()).isFalse();
	}
}
