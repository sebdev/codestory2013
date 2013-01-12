package com.sdevolder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CodeStoryServletTest {

    private final CodeStoryServlet runner = new CodeStoryServlet();

    @Mock
    HttpServletRequest httpServletRequest;
    @Mock
    HttpServletResponse httpServletResponse;
    @Mock
    PrintWriter writer;

    @Test
    public void testGetQuestionEmail() throws IOException, ServletException {

        Map<String, Object> value = new HashMap<String, Object>(1);
        value.put("q", AskSentences.QUESTION_EMAIL);
        Mockito.when(httpServletRequest.getParameterMap()).thenReturn(value);
        Mockito.when(httpServletRequest.getParameter("q")).thenReturn(AskSentences.QUESTION_EMAIL);
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);

        runner.doGet(httpServletRequest, httpServletResponse);
        Mockito.verify(writer).write(AnswerSentences.SEBASTIEN_DEVOLDER_GMAIL_COM);
    }

    @Test
    public void testGetQuestionMailingList() throws IOException, ServletException {

        Map<String, Object> value = new HashMap<String, Object>(1);
        value.put("q", AskSentences.QUESTION_MAILINGLIST);
        Mockito.when(httpServletRequest.getParameterMap()).thenReturn(value);
        Mockito.when(httpServletRequest.getParameter("q")).thenReturn(AskSentences.QUESTION_MAILINGLIST);
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);

        runner.doGet(httpServletRequest, httpServletResponse);
        Mockito.verify(writer).write(AnswerSentences.OUI);
    }

    @Test
    public void testGetQuestionHeureux() throws IOException, ServletException {

        Map<String, Object> value = new HashMap<String, Object>(1);
        value.put("q", AskSentences.QUESTION_HEUREUX);
        Mockito.when(httpServletRequest.getParameterMap()).thenReturn(value);
        Mockito.when(httpServletRequest.getParameter("q")).thenReturn(AskSentences.QUESTION_HEUREUX);
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);

        runner.doGet(httpServletRequest, httpServletResponse);
        Mockito.verify(writer).write(AnswerSentences.OUI);
    }

    @Test
    public void testGetUnknownQuestion() throws IOException, ServletException {
        Map<String, Object> value = new HashMap<String, Object>(1);
        value.put("q", "test");
        Mockito.when(httpServletRequest.getParameterMap()).thenReturn(value);
        Mockito.when(httpServletRequest.getParameter("q")).thenReturn("test");
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);

        runner.doGet(httpServletRequest, httpServletResponse);
        Mockito.verify(writer).write(AnswerSentences.VOUS_POUVEZ_REPETER_LA_QUESTION);
    }
}
