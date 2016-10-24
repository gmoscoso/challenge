package com.test.interfaces;

import java.util.Optional;

import com.test.model.RoutesData;

public interface RouteFinder {

	public Optional<Integer> findRouteBetween(int station1, int station2);
	public RoutesData start(String fileName);
}
