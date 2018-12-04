package compass.schedule.monitor;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import org.slf4j.Logger; import org.slf4j.LoggerFactory;

import compass.schedule.monitor.repositories.JobRepository;

@Service
public class SchedulerJobService {
	private static Logger log = LoggerFactory.getLogger(SchedulerJobService.class);

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	private SchedulerJobFactory schedulerJobFactory;
	
	@Autowired
	private SchedulerTriggerFactory schedulerTriggerFactory;
	
	@Autowired
	private JobRepository jobRepository;
	
	@PostConstruct
	public void setupJobs() throws ParseException, SchedulerException {
		
		TriggerJob job1 = schedulerJobFactory.getTriggerJob("JOB-TEST-1");
		TriggerJob job2 = schedulerJobFactory.getTriggerJob("JOB-TEST-2");
		
		scheduleTriggerJob(job1, "0/8 * * * * ?", "TRIGGER-TEST-1");
		scheduleTriggerJob(job2, "0/9 * * * * ?", "TRIGGER-TEST-2");
		
		schedulerFactoryBean.getScheduler().start();
		
		log.info(jobRepository.findAll().toString());
		
	}
	
	public void shutdown() throws SchedulerException {
		schedulerFactoryBean.getScheduler().shutdown();
	}
	
	public Date scheduleTriggerJob(TriggerJob job, String cronString, String triggerName) throws SchedulerException, ParseException {
		return schedulerFactoryBean.getScheduler().scheduleJob(job.getJobDetail(), schedulerTriggerFactory.getTrigger(job.getJobDetail(), cronString, triggerName).getObject());
	}
	
}
