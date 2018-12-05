package compass.schedule.monitor;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class QuartzTriggerFactory {
	public CronTriggerFactoryBean getTrigger(JobDetail jobDetail, String cronString, String triggerName)
			throws ParseException {
		CronTriggerFactoryBean triggerFactory = createCronTrigger(jobDetail, cronString);
		triggerFactory.setBeanName(triggerName);
		triggerFactory.afterPropertiesSet();
		return triggerFactory;
	}
	
	private CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail, String cronString) {
		CronTriggerFactoryBean triggerFactory = new CronTriggerFactoryBean();
		triggerFactory.setJobDetail(jobDetail);
		triggerFactory.setCronExpression(cronString);
		triggerFactory.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW);
		return triggerFactory;
	}
}
