package com.sdevolder.codestory2013.dialog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Dictionnaire de question r�ponse charg� � partir d'un fichier de propri�t�s
 */
public class DialogBot {
    private static final String ASK_DEFAULT_KEY = "ask.";
    private static final String ANSWER_DEFAULT_KEY = "answer.default";
    private static final String REG_EXP_FOR_DOT_SEPARATOR = "[.]";
    private static final String ANSWER_PREFIX_KEY = "answer.";
    private String defaultAnswer;
    private final Map<String, String> dictionnary = new HashMap<String, String>();

    /**
     * Prend en param�tre le nom du fichier de questions/r�ponses � charger.
     */
    public DialogBot(String fileName) {
        try {
            constructMapping(fileName);
        } catch (IOException e) {
            throw new DialogBotException(e);
        }
    }

    /**
     * Retourne la r�ponse � une question donn�e.
     */
    public String getAnswer(String asking) {
        String answer = dictionnary.get(asking);
        if (answer == null) {
            return defaultAnswer;
        } else {
            return answer;
        }
    }

    private void constructMapping(String fileName) throws IOException {
        Properties properties = loadProperties(fileName);
        fillMap(properties);
    }

    private void fillMap(Properties properties) {
        for (Object key : properties.keySet()) {
            String keyString = (String) key;
            if (keyString.equals(ANSWER_DEFAULT_KEY)) {
                defaultAnswer = (String) properties.get(keyString);
            } else {
                String[] splitedKey = keyString.split(REG_EXP_FOR_DOT_SEPARATOR);
                if (isValidAskKey(splitedKey)) {
                    String answer = (String) properties.get(ANSWER_PREFIX_KEY + splitedKey[1]);
                    String ask = (String) properties.get(keyString);
                    dictionnary.put(ask, answer);
                }
            }
        }
    }

    private Properties loadProperties(String fileName) throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream(fileName));
        return properties;
    }

    private boolean isValidAskKey(String[] tokens) {
        return tokens.length == 2 && (tokens[0].equals(ASK_DEFAULT_KEY));
    }
}
