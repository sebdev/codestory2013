package com.sdevolder;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class CodeStoryServer {

    private static final int port = 8080;
    private final Logger log = Logger.getLogger(CodeStoryServer.class);

    public void launchServer() throws Exception {
        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(new ServletHolder(new CodeStoryServlet()), "/*");
        server.setHandler(context);
        server.start();
        log.info("Server is started");
    }

    public static void main(String[] args) throws Exception {
        CodeStoryServer runner = new CodeStoryServer();
        runner.launchServer();
    }
}
