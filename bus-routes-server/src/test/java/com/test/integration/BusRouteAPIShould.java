package com.test.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.util.runner.ConcurrentRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.interfaces.RouteFinder;
import com.test.server.service.DirectBusRouteResponseBean;
import com.test.server.util.InjectionConfigurationFactory;

@RunWith(ConcurrentRunner.class)
public class BusRouteAPIShould extends JerseyTest {

    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        RouteFinder routeFinderMock = mock(RouteFinder.class);
        when(routeFinderMock.findRouteBetween(anyInt(), anyInt())).thenReturn(Optional.empty());
        
        return new InjectionConfigurationFactory().createWith(routeFinderMock);
    }

    @Test
    public void passParametersIntoTheResponse() {
    	DirectBusRouteResponseBean response = requestWithDepartureAndArrival("3", "2").request().get(DirectBusRouteResponseBean.class);
    	
    	assertThat(response.getDep_sid()).isEqualTo(3);
    	assertThat(response.getArr_sid()).isEqualTo(2);
    }
    
    @Test
    public void returnAValidJSON() throws Exception {

    	String responseMsg = requestWithDepartureAndArrival("1", "0").request().get(String.class);
    	
    	new ObjectMapper().readTree(responseMsg);
    }

	private WebTarget requestWithDepartureAndArrival(String departure, String arrival) {
		return target().path("/direct").queryParam("dep_sid", departure).queryParam("arr_sid", arrival);
	}
    
}


