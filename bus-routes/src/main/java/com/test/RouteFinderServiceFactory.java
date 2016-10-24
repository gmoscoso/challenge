package com.test;

import com.test.interfaces.RouteFinder;
import com.test.interfaces.RouteNetworkProvider;
import com.test.interfaces.RouteParser;
import com.test.services.BusRouteParser;
import com.test.services.FileStreamer;

public class RouteFinderServiceFactory {

	public RouteFinder create() {
		RouteNetworkProvider routeNetworkStreamer = new FileStreamer();
		RouteParser parser = new BusRouteParser(routeNetworkStreamer);
		RouteFinder directBusRoutesService = new DirectBusRoutesService(parser);
		return directBusRoutesService;
	}

}
