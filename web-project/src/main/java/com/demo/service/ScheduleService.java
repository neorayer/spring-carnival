package com.demo.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScheduleService {

    private static final long DEFAULT_SLEEP_MS = 1000;
    private boolean isStopped = true;

    private MessageListener messageListener;

    public ScheduleService(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public void start() {
        isStopped = false;

        while(!isStopped) {
            try {
                String message = messageListener.syncListen();
                consumeMessage(message);
                Thread.sleep(DEFAULT_SLEEP_MS);
            } catch (Exception e) {
                isStopped = true;
                log.error("", e);
            }
        }
    }

    public void stop() {
        isStopped = true;
        messageListener.stop();
    }

    private void consumeMessage(String message) {
        System.out.println(message);
        //TODO
    }

}
