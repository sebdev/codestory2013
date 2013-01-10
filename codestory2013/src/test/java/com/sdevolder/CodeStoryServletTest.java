package com.sdevolder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CodeStoryServletTest {
    private static final String TEXT1 = "Parameters list\nParam 1: [param][value]\n";
    private static final String TEXT2 = "Parameters list\nParam 1: [q][L[ton, adresse]]\n";

    private final CodeStoryServlet runner = new CodeStoryServlet();

    @Mock
    HttpServletRequest httpServletRequest;
    @Mock
    HttpServletResponse httpServletResponse;
    @Mock
    PrintWriter writer;

    @Test
    public void testBuildParamListWithString() {
        Map<String, Object> parameterMap = new HashMap<String, Object>(1);
        parameterMap.put("param", "value");
        Assertions.assertThat(runner.buildParamList(parameterMap)).isEqualTo(TEXT1);
    }

    @Test
    public void testBuildParamListWithStringList() {
        Map<String, Object> parameterMap = new HashMap<String, Object>(1);
        String[] paramValues = new String[2];
        paramValues[0] = "ton";
        paramValues[1] = "adresse";
        parameterMap.put("q", paramValues);
        Assertions.assertThat(runner.buildParamList(parameterMap)).isEqualTo(TEXT2);
    }

    @Test
    public void testGet() throws IOException, ServletException {
        Mockito.when(httpServletRequest.getParameterMap()).thenReturn(new HashMap<String, String>());
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);

        runner.doGet(httpServletRequest, httpServletResponse);
        Mockito.verify(writer).write("sebastien.devolder@gmail.com");
    }
}
