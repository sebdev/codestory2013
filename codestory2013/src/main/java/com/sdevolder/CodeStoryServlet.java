package com.sdevolder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CodeStoryServlet extends HttpServlet {

    private static final String LANGUAGE_PROPERTIES = "language.properties";
    private static final String PARAMETER_GET = "q";
    private final Logger log = Logger.getLogger(CodeStoryServlet.class);
    private ResponseBot responseBot;

    @Override
    public void init() throws ServletException {
        responseBot = new ResponseBot(LANGUAGE_PROPERTIES);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info(req.toString());
        String response = "ERROR";
        if (req.getParameterMap().size() == 1 && req.getParameterMap().keySet().iterator().next().equals(PARAMETER_GET)) {
            String value = req.getParameter(PARAMETER_GET);
            response = responseBot.getAnswer(value);
        }

        resp.getWriter().write(response);
    }
}
