package com.sdevolder.codestory2013.server.jetty;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.sdevolder.codestory2013.server.AbstractServer;
import com.sdevolder.codestory2013.server.jetty.servlet.CodeStoryServlet;

public class JettyServer extends AbstractServer {

    private final int port;
    private final Logger log = Logger.getLogger(JettyServer.class);

    public JettyServer(int port) {
        this.port = port;
    }

    @Override
    public void launchServer() throws Exception {
        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(new ServletHolder(new CodeStoryServlet()), "/*");
        server.setHandler(context);

        HandlerCollection handlers = new HandlerCollection();
        RequestLogHandler requestLogHandler = new RequestLogHandler();
        handlers.setHandlers(new Handler[] { context, new DefaultHandler(), requestLogHandler });
        server.setHandler(handlers);

        NCSARequestLog requestLog = new NCSARequestLog("./jetty-yyyy_mm_dd.request.log");
        requestLog.setRetainDays(90);
        requestLog.setAppend(true);
        requestLog.setExtended(false);
        requestLog.setLogTimeZone("GMT");
        requestLogHandler.setRequestLog(requestLog);

        server.start();
        log.info("Server is started");
    }

    @Override
    public void stopServer() throws Exception {
        // TODO Auto-generated method stub

    }

}
