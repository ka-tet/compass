package compass.schedule.monitor;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger; import org.slf4j.LoggerFactory;

import compass.schedule.monitor.entity.QuartzJob;

@Service
public class JobService {
	private static Logger log = LoggerFactory.getLogger(JobService.class);
	
	@Autowired
	private QuartzJobService quartzJobService;

//	@Autowired
//	private SchedulerFactoryBean schedulerFactoryBean;
	
//	@Autowired
//	private JobFactory schedulerJobFactory;
	
//	@Autowired
//	private SchedulerTriggerFactory schedulerTriggerFactory;
	
//	@Autowired
//	private JobRepository jobRepository;
	
	@PostConstruct
	public void setupJobs() throws ParseException, SchedulerException {
		log.info("=== JobRepository.setupJobs()");
	}
	
	public List<TriggerJob> getJobs() {
		List<TriggerJob> jobs = new ArrayList<TriggerJob>();
		for(QuartzJob quartzJob : quartzJobService.getJobs()) {
			TriggerJob job = new TriggerJob();
			//job.setJobDetail(quartzJob.getJobDetail());
			job.setGroupName(quartzJob.getGroupName());
			job.setJobName(quartzJob.getJobName());
			jobs.add(job);
		}
		return jobs;
	}
	
//	public Date scheduleTriggerJob(TriggerJob job, String cronString, String triggerName) throws SchedulerException, ParseException {
//		return schedulerFactoryBean.getScheduler().scheduleJob(job.getJobDetail(), schedulerTriggerFactory.getTrigger(job.getJobDetail(), cronString, triggerName).getObject());
//	}
	
}
