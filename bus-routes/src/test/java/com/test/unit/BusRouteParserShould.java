package com.test.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import com.test.model.RoutesData;
import com.test.services.BusRouteParser;

public class BusRouteParserShould {

	private BusRouteParser parser;
	
	@Before
	public void init() {
		parser = new BusRouteParser(null);
	}
	
	@Test
	public void parseAnEmptyBusRouteFile() {
		parser.parseRouteNetwork(Stream.of("0"));
		
		assertThat(parser.getRoutesData().getStations()).hasSize(0);
	}
		
	@Test
	public void parseNumberOfBusRoutes() {
		parser.parseRouteNetwork(Stream.of("1"));
		
		RoutesData routesData = parser.getRoutesData();
		
		assertThat(routesData.getStations()).hasSize(0);
		assertThat(routesData.getNumBusRoutes()).isEqualTo(1);
	}

	@Test
	public void parseOneBusRoute() {
		parser.parseSingleRoute(Stream.of(0, 0, 1));
		
		assertThat(parser.getRoutesData().getStations()).hasSize(2);
	}
	
	@Test
	public void parseFileWithOneBusRoute() {
		String routeWithTwoStations = "0 0 1";
		Stream<String> stream = Stream.of("1", routeWithTwoStations);
		
		parser.parseRouteNetwork(stream);
		
		RoutesData routesData = parser.getRoutesData();
		
		assertThat(routesData.getStations()).hasSize(2);
		assertThat(routesData.getNumBusRoutes()).isEqualTo(1);
	}
	
	@Test
	public void parseFileWithTwoBusRoutes() {
		String routeWithTwoStations = "0 0 1";
		String routeWithThreeStations = "1 1 2 3";
		Stream<String> stream = Stream.of("2", routeWithTwoStations, routeWithThreeStations);
		
		parser.parseRouteNetwork(stream);
		
		RoutesData routesData = parser.getRoutesData();
		
		assertThat(routesData.getStations()).hasSize(4);
		assertThat(routesData.getNumBusRoutes()).isEqualTo(2);
	}
	
	@Test
	public void parseRoutesWithDoubleDigitStations() {
		String routeWithDoubleDigitStations = "0 11 15";
		Stream<String> stream = Stream.of("1", routeWithDoubleDigitStations);
		
		parser.parseRouteNetwork(stream);
		
		RoutesData routesData = parser.getRoutesData();
		
		assertThat(routesData.getStations()).hasSize(2);
		assertThat(routesData.getNumBusRoutes()).isEqualTo(1);
	}
}
