package com.test.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.util.runner.ConcurrentRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.test.RouteFinderServiceFactory;
import com.test.interfaces.RouteFinder;
import com.test.server.service.DirectBusRouteResponseBean;
import com.test.server.util.InjectionConfigurationFactory;

@RunWith(ConcurrentRunner.class)
public class EndToEndTest extends JerseyTest {

    @Override
    protected ResourceConfig configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        RouteFinder finder = new RouteFinderServiceFactory().create();
		finder.start("src/test/resources/busRoutes.txt");
		
		return new InjectionConfigurationFactory().createWith(finder);
    }
    
    @Test
    public void findDirectRouteBetweenStations6And4() throws Exception {
    	Builder request = requestWithDepartureAndArrival("6", "4").request();
		DirectBusRouteResponseBean response = request.get(DirectBusRouteResponseBean.class);
    	
		assertThat(response.isDirect_bus_route()).isTrue();
    }
    
    @Test
    public void nofFindAnythingBetweenStations0And5() throws Exception {
    	Builder request = requestWithDepartureAndArrival("0", "5").request();
		DirectBusRouteResponseBean response = request.get(DirectBusRouteResponseBean.class);
    	
		assertThat(response.isDirect_bus_route()).isFalse();
    }

	private WebTarget requestWithDepartureAndArrival(String departure, String arrival) {
		return target().path("/direct").queryParam("dep_sid", departure).queryParam("arr_sid", arrival);
	}
    
}

