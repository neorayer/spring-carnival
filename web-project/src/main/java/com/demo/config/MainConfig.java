package com.demo.config;

import com.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MainConfig {

    @Autowired
    private ScheduleService scheduleService;

    @PostConstruct
    public void start() {
        scheduleService.start();

    }
}
