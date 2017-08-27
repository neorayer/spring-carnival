package com.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
@Slf4j
public class ScheduleService {

    private static final int SLEEP_MS_WHEN_RECEIVE = 200;
    private static final int SLEEP_MS_WHEN_EMPTY = 1000;
    private static final int SLEEP_MS_WHEN_EXCEPTION = 5000;
    private boolean isStopped = true;

    private MessageListener messageListener;

    public ScheduleService(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public void start() {
        new Thread(this::run, "ScheduleService").start();
    }

    private void run() {
        log.info("Started ScheduleService thread");
        isStopped = false;
        int sleepMs = SLEEP_MS_WHEN_EMPTY;

        while(!isStopped) {
            sleep(sleepMs);
            try {
                String message = messageListener.listen();
                if (message != null) {
                    consumeMessage(message);
                    sleepMs = SLEEP_MS_WHEN_RECEIVE;
                }else {
                    sleepMs = SLEEP_MS_WHEN_EMPTY;
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                log.debug("", e);
                sleepMs = SLEEP_MS_WHEN_EXCEPTION;
            }
        }
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }

    public void stop() {
        isStopped = true;
        messageListener.stop();
    }

    private void consumeMessage(String message) {
        Paths.get(message).toFile().delete();
        log.info(message);
    }

}
