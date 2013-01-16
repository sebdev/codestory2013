package com.sdevolder;

import java.io.IOException;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

public class ResponseBotTest {

    private static final String YES = "OUI";
    private static final String DEFAULT_ANSWER = "Vous pouvez r�p�ter la question?";
    private static final String UNKWNON_QUESTION = "unkwnon question";
    private static final String FILENAME = "language.test.properties";
    private static final String KNOWN_QUESTION = "Es tu pret a recevoir une enonce au format markdown par http post(OUI/NON)";
    private ResponseBot bot;

    @Before
    public void setup() {
        bot = new ResponseBot(FILENAME);
    }

    @Test
    public void testConstructMapShouldReturnTheDefaultAnswer() throws IOException {
        Assertions.assertThat(bot.getAnswer(UNKWNON_QUESTION)).isEqualTo(DEFAULT_ANSWER);
    }

    @Test
    public void testConstructMapShouldReturnTheAnswerToAKnownAsking() throws IOException {
        Assertions.assertThat(bot.getAnswer(KNOWN_QUESTION)).isEqualTo(YES);
    }

}
