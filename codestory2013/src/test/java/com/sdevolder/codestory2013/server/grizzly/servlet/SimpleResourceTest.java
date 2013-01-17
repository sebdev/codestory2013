package com.sdevolder.codestory2013.server.grizzly.servlet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;

public class SimpleResourceTest extends JerseyTest {

    public SimpleResourceTest() throws Exception {
        super("com.sdevolder.codestory2013.server.grizzly.servlet");
    }

    @Test
    public void testAnswerMessage() {
        WebResource webResource = resource();
        webResource.path("");
        webResource = webResource.queryParam("q", "Quelle est ton adresse email");
        String responseMsg = webResource.get(String.class);
        assertEquals("sebastien.devolder@gmail.com", responseMsg);
    }
}
