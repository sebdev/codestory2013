package com.sdevolder;

import java.util.Properties;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class PropertiesLearningTest {

    private static final String LANGUAGE_TEST_PROPERTIES = "language.test.properties";

    @Test
    public void shouldAcceptSpaceInkeys() {
        Properties properties = new Properties();
        properties.put("test space", "value");
        Assertions.assertThat(properties.get("test space")).isEqualTo("value");
    }

    @Test
    public void shouldNotAcceptSpaceInkeysWhenReadFromFile() throws Exception {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream(LANGUAGE_TEST_PROPERTIES));
        Assertions.assertThat(properties.get("test space")).isNull();
    }

    @Test
    public void shouldReturnNullIfValueDoesnotExists() throws Exception {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream(LANGUAGE_TEST_PROPERTIES));
        properties.put("test space", "value");
        Assertions.assertThat(properties.get("nothing")).isNull();
    }
}
