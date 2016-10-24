package com.test.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.test.RouteFinderServiceFactory;
import com.test.interfaces.RouteFinder;
import com.test.model.RoutesData;

public class RouteFinderShouldParseTheFileAnd {

	private Integer numRoutes;
	private Map<Integer,List<Integer>> stations;

	@Before
	public void before() {
		RouteFinder app = new RouteFinderServiceFactory().create();
		
		RoutesData routesData = app.start("./src/test/resources/busRoutes.txt");
		numRoutes = routesData.getNumBusRoutes();
		stations = routesData.getStations();
	}
	
	@Test
	public void returnNumberOfStations(){
		assertThat(numRoutes).isEqualTo(3);
	}

	@Test
	public void busRoutesOfStation0(){
		assertThat(stations.get(0)).contains(0, 2);
	}

	@Test
	public void busRoutesOfStation1(){
		assertThat(stations.get(1)).contains(0, 1);
	}
	
	@Test
	public void busRoutesOfStation2(){
		assertThat(stations.get(2)).contains(0);
	}
	
	@Test
	public void busRoutesOfStation3(){
		assertThat(stations.get(3)).contains(0, 1);
	}
	
	@Test
	public void busRoutesOfStation4(){
		assertThat(stations.get(4)).contains(0, 2);
	}
	
	@Test
	public void busRoutesOfStation5(){
		assertThat(stations.get(5)).contains(1);
	}
	
	@Test
	public void busRoutesOfStation6(){
		assertThat(stations.get(6)).contains(1);
	}
	
}
