package com.test.interfaces;

import com.test.model.RoutesData;

public interface RouteParser {

	public RoutesData parseRouteNetwork(String fileName);
	public RoutesData getRoutesData();

}
