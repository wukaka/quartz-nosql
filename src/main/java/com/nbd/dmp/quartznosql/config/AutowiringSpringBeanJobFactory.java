package com.nbd.dmp.quartznosql.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Slf4j
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    private transient AutowireCapableBeanFactory beanFactory;


    @Override
    public void setApplicationContext(final ApplicationContext context) throws BeansException {
        beanFactory = context.getAutowireCapableBeanFactory();

    }

    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        log.info("create job instance");
        beanFactory.autowireBean(job);
        return job;

    }
}
