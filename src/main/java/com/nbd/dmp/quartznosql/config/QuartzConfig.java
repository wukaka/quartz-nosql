package com.nbd.dmp.quartznosql.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Configuration
public class QuartzConfig {

    private static final String CONTEXT_KEY = "applicationContext";
    private static final String PROPERTIES_QUARTZ_KEY = "quartz.properties";


    @Value("${nbd.quartz.jobStore.mongoUri}")
    private String mongoUri;

    @Value("${nbd.quartz.jobStore.class}")
    private String jobStoreClass;

    @Value("${nbd.quartz.jobStore.dbName}")
    private String jobStoreDbName;

    @Value("${nbd.quartz.jobStore.collectionPrefix}")
    private String collectionPrefix;

    @Value("${nbd.quartz.jobStore.isClustered}")
    private boolean isClustered;

    @Value("${nbd.quartz.threadPool.threadCount}")
    private Integer threadCount;

    @Value("${nbd.quartz.scheduler.skipUpdateCheck}")
    private boolean skipUpdateCheck;

    @Value("${nbd.quartz.scheduler.instanceId}")
    private String instanceId;

    @Value("${nbd.quartz.scheduler.instanceName}")
    private String instanceName;

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
        Properties quartzProperties = new Properties();
        quartzProperties.setProperty("org.quartz.jobStore.class", jobStoreClass);
        quartzProperties.setProperty("org.quartz.jobStore.mongoUri", mongoUri);
        quartzProperties.setProperty("org.quartz.jobStore.dbName", jobStoreDbName);
        quartzProperties.setProperty("org.quartz.jobStore.collectionPrefix", collectionPrefix);
        quartzProperties.setProperty("org.quartz.threadPool.threadCount", String.valueOf(threadCount));
        quartzProperties.setProperty("org.quartz.scheduler.skipUpdateCheck", String.valueOf(skipUpdateCheck));
        quartzProperties.setProperty("org.quartz.jobStore.isClustered", String.valueOf(isClustered));
        quartzProperties.setProperty("org.quartz.scheduler.instanceId", String.valueOf(instanceId));
        quartzProperties.setProperty("org.quartz.scheduler.instanceName", instanceName);
        factoryBean.setQuartzProperties(quartzProperties);
//        factoryBean.setConfigLocation(new ClassPathResource(PROPERTIES_QUARTZ_KEY));
        return factoryBean;
    }

}
