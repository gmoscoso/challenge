package com.test.server.util;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.test.server.App;

public class ServerFactory {

    private static final URI BASE_URI = URI.create("http://localhost:8088/api/");

	public void startServerWith(ResourceConfig config) {
		try {

        	final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, config, false);
            
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));
            
            server.start();
            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
