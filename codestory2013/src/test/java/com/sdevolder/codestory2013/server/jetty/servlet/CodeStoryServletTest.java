package com.sdevolder.codestory2013.server.jetty.servlet;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.sdevolder.codestory2013.server.jetty.servlet.CodeStoryServlet;

@RunWith(MockitoJUnitRunner.class)
public class CodeStoryServletTest {

    private static final String VOUS_POUVEZ_REPETER_LA_QUESTION = "Vous pouvez répéter la question?";
    private static final String SEBASTIEN_DEVOLDER_GMAIL_COM = "sebastien.devolder@gmail.com";
    private static final String QUELLE_EST_TON_ADRESSE_EMAIL = "Quelle est ton adresse email";

    private CodeStoryServlet codeStoryServlet;

    @Mock
    HttpServletRequest httpServletRequest;
    @Mock
    HttpServletResponse httpServletResponse;
    @Mock
    PrintWriter writer;
    private Map<String, Object> value;

    @Before
    public void setup() throws Exception {
        codeStoryServlet = new CodeStoryServlet();
        codeStoryServlet.init();
        value = new HashMap<String, Object>(1);
        Mockito.when(httpServletRequest.getParameterMap()).thenReturn(value);
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);
    }

    @Test
    public void testGetQuestionEmail() throws Exception {

        value.put("q", QUELLE_EST_TON_ADRESSE_EMAIL);
        Mockito.when(httpServletRequest.getParameter("q")).thenReturn(QUELLE_EST_TON_ADRESSE_EMAIL);
        codeStoryServlet.doGet(httpServletRequest, httpServletResponse);
        Mockito.verify(writer).write(SEBASTIEN_DEVOLDER_GMAIL_COM);
    }

    @Test
    public void testGetUnknownQuestion() throws Exception {
        value.put("q", "test");
        Mockito.when(httpServletRequest.getParameter("q")).thenReturn("test");
        codeStoryServlet.doGet(httpServletRequest, httpServletResponse);
        Mockito.verify(writer).write(VOUS_POUVEZ_REPETER_LA_QUESTION);
    }
}
