package com.test.server;

import org.glassfish.jersey.server.ResourceConfig;

import com.test.RouteFinderServiceFactory;
import com.test.interfaces.RouteFinder;
import com.test.server.util.InjectionConfigurationFactory;
import com.test.server.util.ServerFactory;

public class App {


    public static void main(String[] args) {
    	if (args.length != 1) {
    		throw new IllegalStateException("Please pass the bus route data file location as the first argument of the program");
    	}
    	String fileName = args[0];
    	App.startServer(fileName);
    }
    
	private static void startServer(String fileName) {
		RouteFinder finder = new RouteFinderServiceFactory().create();
		finder.start(fileName);
		
		ResourceConfig config = new InjectionConfigurationFactory().createWith(finder);
		
		new ServerFactory().startServerWith(config);
	}
}


