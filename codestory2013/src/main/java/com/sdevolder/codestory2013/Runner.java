package com.sdevolder.codestory2013;

import com.sdevolder.codestory2013.server.AbstractServer;
import com.sdevolder.codestory2013.server.grizly.GrizzlyServer;
import com.sdevolder.codestory2013.server.jetty.JettyServer;

public class Runner {

    private static final String JETTY = "JETTY";
    private static final Object GRIZZLY = "GRIZZLY";
    private AbstractServer server;

    public Runner(String serverType, int port) {
        if (JETTY.equals(serverType)) {
            server = new JettyServer(port);
        } else if (GRIZZLY.equals(serverType)) {
            server = new GrizzlyServer(port);
        }

    }

    public void launch() throws Exception {
        server.launchServer();
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new Runner(args[0], Integer.valueOf(args[1]));
        runner.launch();
    }

}
