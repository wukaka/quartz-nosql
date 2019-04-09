package com.nbd.dmp.quartznosql.service.impl;

import com.nbd.dmp.quartznosql.service.ScheduleService;
import com.nbd.dmp.quartznosql.timer.SortedTimer;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@Service("ScheduleServiceImpl")
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private Scheduler scheduler;

    @Override
    public void schedule() throws Exception {
        log.info("schedule service");

        String name = UUID.randomUUID().toString();

        String group = SortedTimer.class.getName();

        ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");

        JobDetail jobDetail = JobBuilder.newJob(SortedTimer.class).withIdentity(name, group).build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }
}
