package com.nbd.dmp.quartznosql.service;

import com.nbd.dmp.quartznosql.bean.ScheduleTimerPa;

import java.util.List;

public interface ScheduleService {

    public void schedule() throws Exception;

    public void initScheduleList(List<String> params) throws Exception;

    public List<ScheduleTimerPa> jobList() throws Exception;
}
