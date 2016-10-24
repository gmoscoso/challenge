package com.test.server.service;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.test.interfaces.RouteFinder;

@Singleton
@Path("direct")
public class DirectBusRoutesResource {
	
	@Inject
	private 
	RouteFinder routeFinder;

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public DirectBusRouteResponseBean getDirectBusRoutes(
    		@QueryParam("dep_sid") final int dep_sid, 
    		@QueryParam("arr_sid") final int arr_sid) {
        
    	DirectBusRouteResponseBean response = new DirectBusRouteResponseBean();
		response.setArr_sid(arr_sid);
		response.setDep_sid(dep_sid);
    	
		
		Optional<Integer> findRouteBetween = this.routeFinder.findRouteBetween(dep_sid, arr_sid);
		
		response.setDirect_bus_route(findRouteBetween.isPresent());
		
    	return response;
    }
    
	public void setRouteFinder(RouteFinder routeFinder) {
		this.routeFinder = routeFinder;
	}
}
