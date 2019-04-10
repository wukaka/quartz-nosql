package com.nbd.dmp.quartznosql.service.impl;

import com.nbd.dmp.quartznosql.service.ScheduleService;
import com.nbd.dmp.quartznosql.timer.SortedTimer;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
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

        jobDetail.getJobDataMap().put("key", "just params");

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public void initScheduleList(List<String> params) throws Exception {
        for (String param : params) {
            try {
                String name = UUID.randomUUID().toString();
                String group = SortedTimer.class.getName();
                ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
                JobDetail jobDetail = JobBuilder.newJob(SortedTimer.class).withIdentity(name, group).build();
                jobDetail.getJobDataMap().put("key", param);
                Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } catch (Exception e) {
                log.error("schedule error", e);
            }
        }


    }

    @Override
    public void jobList() throws Exception {
        for (String groupName : scheduler.getJobGroupNames()) {
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                String jobName = jobKey.getName();
                String jobGroup = jobKey.getGroup();
                List<Trigger> triggerList = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                Date nextFireTime = triggerList.get(0).getNextFireTime();
                log.info("Job : " + jobName + ", Group : "
                        + jobGroup + ", Next execution time : "
                        + nextFireTime);
            }
        }
    }

}
