package com.nbd.dmp.quartznosql.service;

import java.util.List;

public interface ScheduleService {

    public void schedule() throws Exception;

    public void initScheduleList(List<String> params) throws Exception;

    public void jobList() throws Exception;
}
