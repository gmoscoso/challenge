package com.test.server.util;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.test.interfaces.RouteFinder;
import com.test.server.service.DirectBusRoutesResource;

public class InjectionConfigurationFactory {

	public ResourceConfig createWith(RouteFinder finder) {
		ConfigWithBusRoutes serverWithBusRoutes = new ConfigWithBusRoutes();
		serverWithBusRoutes.register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(finder).to(RouteFinder.class);
			}
		});
		return serverWithBusRoutes;
	}

}

class ConfigWithBusRoutes extends ResourceConfig {
	public ConfigWithBusRoutes() {
		super(
				MyObjectMapperProvider.class,
				JacksonFeature.class,
				DirectBusRoutesResource.class
				) ;
	}
}