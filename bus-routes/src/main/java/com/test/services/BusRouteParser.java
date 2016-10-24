package com.test.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.test.interfaces.RouteNetworkProvider;
import com.test.interfaces.RouteParser;
import com.test.model.RoutesData;

public class BusRouteParser implements RouteParser {
	
	private final RouteNetworkProvider routeNetworkStreamer;
	
	private final RoutesData data = new RoutesData(new HashMap<Integer, List<Integer>>(), 0);

	private final Pattern spacePattern = Pattern.compile(" ");
	
	public BusRouteParser(RouteNetworkProvider routeNetworkStreamer) {
		this.routeNetworkStreamer = routeNetworkStreamer;
	}

	@Override
	public RoutesData parseRouteNetwork(String fileName) {
		Stream<String> stream = this.routeNetworkStreamer.stream(fileName);
		return this.parseRouteNetwork(stream);
	}
	
	public RoutesData parseRouteNetwork(Stream<String> lines) {
		String nbBusRoutesAsString = lines.reduce((nbRoutes, route) -> {
			this.parseSingleRoute(toIntStream(route));
			return nbRoutes;
		}).get();
		
		this.data.setNumBusRoutes(Integer.valueOf(nbBusRoutesAsString));
		
		return this.data;
	}

	private Stream<Integer> toIntStream(String line) {
		return spacePattern.splitAsStream(line)
				.map(Integer::valueOf);
	}
	
	public void parseSingleRoute(Stream<Integer> busRouteStream) {
		busRouteStream.reduce((busRouteId, station) -> {
			this.addStationAndBusRoute(busRouteId, station);
			return busRouteId;
		});	
	}

	private void addStationAndBusRoute(Integer busRouteId, Integer station) {
		if (this.data.getStations().get(station) == null) {
			List<Integer> routeList = new ArrayList<Integer>();
			routeList.add(busRouteId);
			this.data.getStations().put(station, routeList);
		} else {
			this.data.getStations().get(station).add(busRouteId);
		}
	}

	@Override
	public RoutesData getRoutesData() {
		return data;
	}
}
