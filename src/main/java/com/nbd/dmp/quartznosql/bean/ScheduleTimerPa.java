package com.nbd.dmp.quartznosql.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleTimerPa {

    @JsonProperty("job_group_name")
    private String jobGroupName;

    @JsonProperty("job_key_key")
    private JobKey jobKey;

    @JsonProperty("trigger_key")
    private TriggerKey triggerKey;
}
