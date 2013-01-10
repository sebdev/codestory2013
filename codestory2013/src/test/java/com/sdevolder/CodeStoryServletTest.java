package com.sdevolder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

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
    public void testGet() throws IOException, ServletException {
        Mockito.when(httpServletRequest.getParameterMap()).thenReturn(new HashMap<String, String>());
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);

        runner.doGet(httpServletRequest, httpServletResponse);
        Mockito.verify(writer).write("sebastien.devolder@gmail.com");
    }
}
