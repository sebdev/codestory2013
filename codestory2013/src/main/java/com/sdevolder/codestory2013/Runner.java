package com.sdevolder.codestory2013;

import com.sdevolder.codestory2013.server.AbstractServer;
import com.sdevolder.codestory2013.server.jetty.JettyServer;

public class Runner {

    private static final String JETTY = "JETTY";
    private AbstractServer server;

    public Runner(String serverType, int port) {
        if (JETTY.equals(serverType)) {
            server = new JettyServer(port);
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
