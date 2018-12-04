package compass.schedule.monitor;

import org.quartz.JobDataMap;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class SchedulerJobFactory
{

    public JobDetailFactoryBean job(
                               Class<? extends QuartzJobBean> jobClass,
                               JobDataMap jobMap,
                               String jobName) {
        JobDetailFactoryBean jBean = createJobDetail(jobClass);
        jBean.setJobDataMap(jobMap);
        jBean.setBeanName(jobName);
        jBean.afterPropertiesSet();
        return jBean;
    }

    public JobDetailFactoryBean job(
            Class<? extends QuartzJobBean> jobClass,
            String jobName) {
    	JobDetailFactoryBean jBean = createJobDetail(jobClass);
    	jBean.setBeanName(jobName);
    	jBean.afterPropertiesSet();
    	return jBean;
    }

    public TriggerJob getTriggerJob(String jobName) {
    	TriggerJob triggerJob = new TriggerJob();
    	JobDetailFactoryBean jobDetail = createJobDetail(TriggerJob.class);
    	jobDetail.setBeanName(jobName);
    	jobDetail.afterPropertiesSet();
    	triggerJob.setJobDetail(jobDetail.getObject());
    	return triggerJob;
    }


    private JobDetailFactoryBean createJobDetail(
                                  Class<? extends QuartzJobBean> jobClass) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        return factoryBean;
    }
}