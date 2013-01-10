package com.sdevolder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CodeStoryServletTest {

    private static final String TEXT = "Parameters list\nParam 1: [param][value]\n";
    private final CodeStoryServlet runner = new CodeStoryServlet();

    @Mock
    HttpServletRequest httpServletRequest;
    @Mock
    HttpServletResponse httpServletResponse;
    @Mock
    PrintWriter writer;

    @Before
    @Test
    public void testBuildParamList() {
        Map<String, String> parameterMap = new HashMap<String, String>(1);
        parameterMap.put("param", "value");
        Assertions.assertThat(runner.buildParamList(parameterMap)).isEqualTo(TEXT);
    }

    @Test
    public void testGet() throws IOException, ServletException {
        Mockito.when(httpServletRequest.getParameterMap()).thenReturn(new HashMap<String, String>());
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);

        runner.doGet(httpServletRequest, httpServletResponse);
        Mockito.verify(writer).write("sebastien.devolder@gmail.com");
    }
}
