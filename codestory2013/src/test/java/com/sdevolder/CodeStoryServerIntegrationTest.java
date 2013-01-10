package com.sdevolder;

import java.io.IOException;

import org.fest.assertions.Assertions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.testing.HttpTester;
import org.mortbay.jetty.testing.ServletTester;

public class CodeStoryServerIntegrationTest {

    private static ServletTester tester;

    /**
     * This kicks off an instance of the Jetty servlet container so that we can hit it. We register an echo service that
     * simply returns the parameters passed to it.
     */
    @BeforeClass
    public static void initServletContainer() throws Exception {
        tester = new ServletTester();
        tester.setContextPath("/");
        tester.addServlet(CodeStoryServlet.class, "/");
        tester.start();
    }

    /**
     * Stops the Jetty container.
     */
    @AfterClass
    public static void cleanupServletContainer() throws Exception {
        tester.stop();
    }

    @Test
    public void test() throws IOException, Exception {
        HttpTester request = new HttpTester();
        HttpTester response = new HttpTester();
        request.setMethod("GET");
        request.setHeader("Host", "tester");
        request.setURI("/?q=Quelle+est+ton+adresse+email");
        request.setVersion("HTTP/1.0");

        response.parse(tester.getResponses(request.generate()));

        Assertions.assertThat(response.getMethod()).isNull();
        Assertions.assertThat(response.getStatus()).isEqualTo(200);
        Assertions.assertThat(response.getContent()).isEqualTo("sebastien.devolder@gmail.com");
    }

}
