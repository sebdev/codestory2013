package com.sdevolder.codestory2013.dialog;

public class DialogBotException extends RuntimeException {

    public DialogBotException(Throwable arg1) {
        super("initialisationError", arg1);
    }

}
