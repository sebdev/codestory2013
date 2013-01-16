package com.sdevolder;

public class ResponseBotException extends RuntimeException {

    public ResponseBotException(Throwable arg1) {
        super("initialisationError", arg1);
    }

}
