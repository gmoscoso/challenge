package com.test.unit;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;

import com.test.interfaces.RouteFinder;
import com.test.server.service.DirectBusRoutesResource;

public class DirectBusRoutesResourceShould {

	@Test
	public void callTheUnderlyingRouteFinderService() throws Exception {
		DirectBusRoutesResource resource = new DirectBusRoutesResource();
		RouteFinder routeFinderMock = mock(RouteFinder.class);
		
		when(routeFinderMock.findRouteBetween(anyInt(), anyInt())).thenReturn(Optional.empty());
		resource.setRouteFinder(routeFinderMock);
		
		resource.getDirectBusRoutes(0, 1);
		
		verify(routeFinderMock, times(1)).findRouteBetween(anyInt(), anyInt());
	}
}
