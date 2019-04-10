package com.nbd.dmp.quartznosql.config;

import com.nbd.dmp.quartznosql.service.impl.ScheduleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Configuration
public class InitConfig implements ApplicationListener<ApplicationReadyEvent>{

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            initTimerList();
        } catch (Exception e) {
            log.error("init config error", e);
        }
    }


    private void initTimerList() {
        try {
            List<String> params = new LinkedList<>();
            for (int i= 0; i < 10; i++){
                params.add("timer param " + i);
            }
            scheduleService.initScheduleList(params);
        }catch (Exception e){
            log.error("init timer error", e);
        }
    }
}
