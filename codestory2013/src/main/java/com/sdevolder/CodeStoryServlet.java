package com.sdevolder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CodeStoryServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(CodeStoryServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info(req.toString());
        String answer = AnswerSentences.VOUS_POUVEZ_REPETER_LA_QUESTION;
        if (req.getParameterMap().size() == 1 && req.getParameterMap().keySet().iterator().next().equals("q")) {
            String value = req.getParameter("q");
            if (value.equals(AskSentences.QUESTION_EMAIL)) {
                answer = AnswerSentences.SEBASTIEN_DEVOLDER_GMAIL_COM;
            } else if (value.equals(AskSentences.QUESTION_MAILINGLIST)) {
                answer = AnswerSentences.OUI;
            }
        }

        resp.getWriter().write(answer);
    }
}
