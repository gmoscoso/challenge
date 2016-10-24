package com.test;

import java.util.List;
import java.util.Optional;

import com.test.interfaces.RouteFinder;
import com.test.interfaces.RouteParser;
import com.test.model.RoutesData;

public class DirectBusRoutesService implements RouteFinder {

	private RouteParser routeParser;
	
	public DirectBusRoutesService(RouteParser routeParser){
		this.routeParser = routeParser;
	}

	@Override
	public RoutesData start(String fileName) {
		return routeParser.parseRouteNetwork(fileName);
	}

	@Override
	public Optional<Integer> findRouteBetween(int station1, int station2) {
		RoutesData routesData = routeParser.getRoutesData();
		
		List<Integer> routesOfFirstStation = routesData.getStations().get(station1);
		List<Integer> routesOfSecondStation = routesData.getStations().get(station2);
		
		return findConnectingRoute(routesOfFirstStation, routesOfSecondStation);
	}

	private Optional<Integer> findConnectingRoute(
			List<Integer> routesOfFirstStation,
			List<Integer> routesOfSecondStation) {
		
		if (routesOfFirstStation == null || routesOfSecondStation == null) {
			return Optional.empty();
		} else {
			return routesOfFirstStation.stream()
					.filter(routesOfSecondStation::contains)
					.findFirst();
		}
	}
}
