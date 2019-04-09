package com.nbd.dmp.quartznosql.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.spi.JobFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Slf4j
@Configuration
public class QuartzConfig {

    private static final String CONTEXT_KEY = "applicationContext";
    private static final String PROPERTIES_QUARTZ_KEY = "quartz.properties";

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setOverwriteExistingJobs(true);
        factoryBean.setStartupDelay(2);
        factoryBean.setAutoStartup(true);
        factoryBean.setApplicationContextSchedulerContextKey(CONTEXT_KEY);
        factoryBean.setConfigLocation(new ClassPathResource(PROPERTIES_QUARTZ_KEY));
        return factoryBean;
    }

}
