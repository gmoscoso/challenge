package com.test.model;

import java.util.List;
import java.util.Map;

public class RoutesData {
	private Map<Integer, List<Integer>> stations;
	private int numBusRoutes;

	public RoutesData(Map<Integer, List<Integer>> stations, int numBusRoutes) {
		this.setStations(stations);
		this.setNumBusRoutes(numBusRoutes);
	}

	public Map<Integer, List<Integer>> getStations() {
		return stations;
	}

	public void setStations(Map<Integer, List<Integer>> stations) {
		this.stations = stations;
	}

	public int getNumBusRoutes() {
		return numBusRoutes;
	}

	public void setNumBusRoutes(int numBusRoutes) {
		this.numBusRoutes = numBusRoutes;
	}
}