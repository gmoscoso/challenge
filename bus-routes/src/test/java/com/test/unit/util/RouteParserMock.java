package com.test.unit.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.test.interfaces.RouteParser;
import com.test.model.RoutesData;

public class RouteParserMock {
	
	private HashMap<Integer, List<Integer>> stations = new HashMap<>();
	private int numBusRoutes;
	
	public RouteParserMock withStationAndRoutes(int station, List<Integer> listOfRoutes) {
		if (stations.get(station) != null) {
			stations.get(station).addAll(listOfRoutes);
		} else {
			stations.put(station, listOfRoutes);
			numBusRoutes++;
		}
		return this;
	}
	
	public RouteParserMock withStationAndRoute(int station, Integer route) {
		if (stations.get(station) != null) {
			stations.get(station).add(route);
		} else {
			ArrayList<Integer> list = new ArrayList<>();
			list.add(route);
			stations.put(station, list);
			numBusRoutes++;
		}
		return this;
	}

	public RouteParser build() {
		RouteParser mockRouteParser = mock(RouteParser.class);
		RoutesData routes = new RoutesData(stations, numBusRoutes);
		when(mockRouteParser.getRoutesData()).thenReturn(routes);
		return mockRouteParser;
	}

}
