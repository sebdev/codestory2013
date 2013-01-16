package com.sdevolder;

import java.io.IOException;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class ResponseBotTest {

    @Test
    public void testConstructMapShouldReturnTheDefaultAnswer() throws IOException {

        ResponseBot bot = new ResponseBot("language.test.properties");
        Assertions.assertThat(bot.getAnswer("unkwnon question")).isEqualTo("Vous pouvez répéter la question?");
    }

    @Test
    public void testConstructMapShouldReturnTheAnswerToAKnownAsking() throws IOException {

        ResponseBot bot = new ResponseBot("language.test.properties");
        Assertions.assertThat(
                bot.getAnswer("Es tu pret a recevoir une enonce au format markdown par http post(OUI/NON)")).isEqualTo(
                "OUI");
    }

}
