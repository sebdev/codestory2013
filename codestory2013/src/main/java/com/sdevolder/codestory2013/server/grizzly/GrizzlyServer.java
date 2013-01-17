package com.sdevolder.codestory2013.server.grizzly;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sdevolder.codestory2013.server.AbstractServer;
import com.sun.jersey.api.container.filter.LoggingFilter;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class GrizzlyServer extends AbstractServer {

    private final int port;
    private HttpServer httpServer;

    public GrizzlyServer(int port) {
        this.port = port;
    }

    @Override
    public void launchServer() throws Exception {

        System.out.println("Starting grizzly...");

        ResourceConfig rc = new PackagesResourceConfig("com.sdevolder.codestory2013.server.grizzly.servlet");
        Map<String, Object> entries = new HashMap<String, Object>();
        entries.put(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS, LoggingFilter.class.getName());
        entries.put(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, LoggingFilter.class.getName());
        rc.setPropertiesAndFeatures(entries);
        httpServer = GrizzlyServerFactory.createHttpServer(getBaseURI(), rc);
        System.in.read();
    }

    private URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(this.port).build();
    }

    @Override
    public void stopServer() throws Exception {
        httpServer.stop();
    }

}
