package com.demo.service;

import java.util.function.Consumer;

public interface MessageListener {

    public String listen() throws Exception;

    public void stop();

}
