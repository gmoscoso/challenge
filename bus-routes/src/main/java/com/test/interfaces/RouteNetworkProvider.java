package com.test.interfaces;

import java.util.stream.Stream;

public interface RouteNetworkProvider {
	public Stream<String> stream(String resourceName);
}
