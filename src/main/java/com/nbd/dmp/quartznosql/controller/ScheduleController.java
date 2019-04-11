package com.nbd.dmp.quartznosql.controller;

import clojure.lang.Obj;
import com.nbd.dmp.quartznosql.service.impl.ScheduleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @GetMapping(value = "/list/all")
    public Map allScheduleJobList(){

        Map<String, Object> model = new HashMap<>();
        Boolean ok = true;
        try {
            model.put("data", scheduleService.jobList());
        } catch (Exception e) {
            ok = false;
            log.error("列出全部schedule列表失败", e);
        }
        model.put("ok", ok);
        return model;
    }
}
