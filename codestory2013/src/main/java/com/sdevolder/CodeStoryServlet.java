package com.sdevolder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CodeStoryServlet extends HttpServlet {

    private static final String SEBASTIEN_DEVOLDER_GMAIL_COM = "sebastien.devolder@gmail.com";
    private final Logger log = Logger.getLogger(CodeStoryServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info(req.toString());
        resp.getWriter().write(SEBASTIEN_DEVOLDER_GMAIL_COM);
    }
}
