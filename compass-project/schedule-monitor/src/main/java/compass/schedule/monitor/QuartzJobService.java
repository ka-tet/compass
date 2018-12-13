package compass.schedule.monitor;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import org.slf4j.Logger; import org.slf4j.LoggerFactory;

import compass.schedule.monitor.entity.QuartzJob;
import compass.schedule.monitor.repositories.QuartzJobRepository;

@Service
public class QuartzJobService {
	private static Logger log = LoggerFactory.getLogger(QuartzJobService.class);

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	private QuartzJobFactory schedulerJobFactory;
	
	@Autowired
	private QuartzTriggerFactory schedulerTriggerFactory;
	
	@Autowired
	private QuartzJobRepository jobRepository;
	
	private Scheduler scheduler;
	
	@PostConstruct
	public void start() throws ParseException, SchedulerException {
		
//		QuartzTriggerJob job1 = schedulerJobFactory.getTriggerJob("JOB-TEST-1a");
//		QuartzTriggerJob job2 = schedulerJobFactory.getTriggerJob("JOB-TEST-2a");
		
//		scheduleTriggerJob(job1, "0/8 * * * * ?", "TRIGGER-TEST-1a");
//		scheduleTriggerJob(job2, "0/9 * * * * ?", "TRIGGER-TEST-2a");
		
		scheduler = schedulerFactoryBean.getScheduler();
		log.info("Starting Quartz scheduler " + scheduler.getSchedulerName());
		scheduler.start();
		scheduler.pauseAll();
		listJobGroups();
		listTriggerGroups();
		listJobs();
		scheduler.resumeAll();;
	}
	
	@PreDestroy
	public void shutdown() throws SchedulerException {
		log.info("Schedular shutting down Quartz scheduler: " + schedulerFactoryBean.getScheduler().getSchedulerName());
		schedulerFactoryBean.getScheduler().shutdown();
	}
	
	public Date scheduleTriggerJob(QuartzTriggerJob job, String cronString, String triggerName) throws SchedulerException, ParseException {
		return schedulerFactoryBean.getScheduler().scheduleJob(job.getJobDetail(), schedulerTriggerFactory.getTrigger(job.getJobDetail(), cronString, triggerName).getObject());
	}
	
	public List<String> listJobs(){
		List<String> results = new ArrayList<String>();
		results.add("   Group         Job");
		results.add("____________ ____________");
		for(QuartzJob job: getJobs()) {
			results.add(String.format("%12s %12s", left(job.getGroupName(),12), left(job.getJobName(),12)));
		}
		for(String i: results) {
			log.info(i);
		}
		return results;
		
	}
	public List<QuartzJob> getJobs() {
		return jobRepository.findAll();
	}
	public List<String> listJobGroups() throws SchedulerException{
		List<String> results = new ArrayList<String>();
		results.add("Job Groups in schedule " + scheduler.getSchedulerName() + ":");
		results.add("   Group");
		results.add("____________");
		for(String group: scheduler.getJobGroupNames()) {
			results.add(String.format("%s", group));
		}
		for(String i: results) {
			log.info(i);
		}
		return results;
	}
	public List<String> listTriggerGroups() throws SchedulerException{
		List<String> results = new ArrayList<String>();
		results.add("Trigger Groups in schedule " + scheduler.getSchedulerName() + ":");
		results.add("   Group");
		results.add("____________");
		for(String group: scheduler.getTriggerGroupNames()) {
			results.add(String.format("%s", group));
		}
		for(String i: results) {
			log.info(i);
		}
		return results;
	}
	public void scheduleJob() {
		
	}
	private String left(String string, int chars) {
		if(string == null) return null;
		if(chars < 1) chars = 1;
		if(chars > string.length()) { chars = string.length(); }
		return string.substring(0, chars);
	}
}
