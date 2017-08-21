package com.demo.service;

public interface MessageListener {

    public String syncListen() throws Exception;

    public void stop();

}
