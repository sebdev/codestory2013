package com.sdevolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ResponseBot {

    private Map<String, String> map;

    private String defaultAnswer;

    public ResponseBot(String fileName) {
        try {
            constructMapping(fileName);
        } catch (IOException e) {
            throw new ResponseBotException(e);
        }
    }

    private void constructMapping(String fileName) throws IOException {
        map = new HashMap<String, String>();

        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream(fileName));

        for (Object key : properties.keySet()) {
            String keyString = (String) key;
            String[] tokens = keyString.split("[.]");
            if (isValidKey(tokens)) {
                String answer = (String) properties.get("answer." + tokens[1]);
                if (keyString.equals("answer.default")) {
                    defaultAnswer = (String) properties.get(keyString);
                } else if (tokens[0].equals("ask")) {
                    String ask = (String) properties.get(keyString);
                    map.put(ask, answer);
                }
            }
        }
    }

    private boolean isValidKey(String[] tokens) {
        return tokens.length == 2 && (tokens[0].equals("ask") || tokens[0].equals("answer"));
    }

    public String getAnswer(String asking) {
        String answer = map.get(asking);
        if (answer == null) {
            return defaultAnswer;
        } else {
            return answer;
        }
    }
}
