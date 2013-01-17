package com.sdevolder;

public class Runner {

    private static final String JETTY = "JETTY";
    private AbstractCodeStoryServer server;

    public Runner(String serverType, int port) {
        if (JETTY.equals(serverType)) {
            server = new CodeStoryJettyServer(port);
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
