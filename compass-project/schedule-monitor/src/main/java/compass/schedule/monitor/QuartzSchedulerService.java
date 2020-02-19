package compass.schedule.monitor;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class QuartzSchedulerService {
	private static Logger log = LogManager.getLogger(QuartzSchedulerService.class);
	
//	@Autowired
//	@Qualifier("localSystemScheduler")
//	SchedulerFactoryBean localSystemScheduler;
//
//	@Autowired
//	@Qualifier("nodeScheduler")
//	SchedulerFactoryBean nodeScheduler;

	@Autowired
	@Qualifier("mainScheduler")
	SchedulerFactoryBean mainScheduler;

	@PostConstruct
	public void startSchedulers() throws ParseException, SchedulerException, InterruptedException {
//		log.info("Starting local system schedular: %s...", localSystemScheduler.getScheduler().getSchedulerName());
//		localSystemScheduler.start();
//		Thread.sleep(500);
//		log.info("Starting node schedular: %s...", localSystemScheduler.getScheduler().getSchedulerName());
//		nodeScheduler.start();
//		Thread.sleep(500);
		log.info(String.format("Starting main schedular: %s...", mainScheduler.getScheduler().getSchedulerName()));
		mainScheduler.start();
		
	}
	
	public List<String> getSchedulerNames() throws SchedulerException {
		List<String> schedulers = new ArrayList<String>();
		
//		schedulers.add(localSystemScheduler.getScheduler().getSchedulerName());
//		schedulers.add(nodeScheduler.getScheduler().getSchedulerName());
		schedulers.add(mainScheduler.getScheduler().getSchedulerName());
		
		return schedulers;
	}
	
	public List<Scheduler> getSchedulers() throws SchedulerException {
		List<Scheduler> schedulers = new ArrayList<Scheduler>();
		
//		schedulers.add(localSystemScheduler.getScheduler());
//		schedulers.add(nodeScheduler.getScheduler());
		schedulers.add(mainScheduler.getScheduler());
	
		return schedulers;
	}
	
/*	public List<JobDetail> getJobs() throws SchedulerException {
		List<JobDetail> jobs = new ArrayList<JobDetail>();
//		jobs.addAll(getJobs(localSystemScheduler.getScheduler()));
//		jobs.addAll(getJobs(nodeScheduler.getScheduler()));
		jobs.addAll(getJobs(mainScheduler.getScheduler()));
	
		return jobs;
	}
	
	public List<JobDetail> getJobs(Scheduler scheduler) throws SchedulerException {
		List<JobDetail> jobs = new ArrayList<JobDetail>();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		for(JobKey jobKey: scheduler.getJobKeys(matcher)) {
			jobs.add(scheduler.getJobDetail(jobKey));
		}
		return jobs;
	}
*/
}
