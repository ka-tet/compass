package compass.schedule.monitor;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import compass.schedule.monitor.repositories.QuartzJobRepository;

@Configuration
public class QuartzConfig {
	private static Logger log = LogManager.getLogger(QuartzConfig.class);

/*
 * process-monitor will watch jobs, workflow, steps and can generate alerts and measure compliance with SLAs	
 */
	
    @Bean
    public JobFactory jobFactory() {
        return new SpringBeanJobFactory();
    }

/*    @Bean
    public SchedulerFactoryBean schedulerFactoryBean( JobFactory jobFactory) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true);
        factory.setJobFactory(jobFactory());
        factory.setBeanName("DEFAULT");
        factory.setQuartzProperties(quartzProperties());
        log.info(quartzProperties());
        return factory;
    } */
    @Bean
    public SchedulerFactoryBean mainScheduler( JobFactory jobFactory) throws Exception {
        log.info("=== Creating main scheduler...");
    	
        SchedulerFactoryBean scheduler = buildScheduler(mainQuartzProperties());
        log.info(String.format("=== Created main scheduler %s.", scheduler.getScheduler().getSchedulerName()));
        return scheduler;
    }
    @Bean
    public Properties mainQuartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/main.quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
    private SchedulerFactoryBean buildScheduler(Properties quartzProperties) throws Exception {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        
        scheduler.setOverwriteExistingJobs(true);
        scheduler.setAutoStartup(false);
        scheduler.setJobFactory(jobFactory());
//        quartzProperties.setProperty("org.quartz.plugin.jobInitializer.overWriteExistingJobs", "true");
        try {
        	scheduler.setQuartzProperties(quartzProperties);
        }
        catch(Exception e) {
        	log.error("Error creating scheduler {}.", quartzProperties.getProperty("org.quartz.scheduler.instanceName", "UNKNOWN"));
        }
        scheduler.afterPropertiesSet();
//        scheduler.setBeanName(quartzProperties.getProperty("org.quartz.scheduler.instanceName", "UNDEFINED"));
        log.info("=== Attempting to create scheduler: {}", scheduler.getScheduler().getSchedulerInstanceId());
        log.info("=== Creating scheduler {}", quartzProperties.getProperty("org.quartz.scheduler.instanceName", "UNDEFINED"));
        
        return scheduler;
    	
    }

    
//    @Bean
//    public QuartzJobRepository quartzJobRepository() {
//		return new QuartzJobRepository();
//    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
}