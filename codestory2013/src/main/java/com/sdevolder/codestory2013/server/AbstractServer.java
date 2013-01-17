package com.sdevolder.codestory2013.server;

public abstract class AbstractServer {

    /**
     * D�marrer le serveur
     */
    public abstract void launchServer() throws Exception;

    /**
     * Arr�ter le serveur
     */
    public abstract void stopServer() throws Exception;
}
