package compass.schedule.monitor;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import org.slf4j.Logger; import org.slf4j.LoggerFactory;

public class QuartzSystemJob extends QuartzJobBean {
	private static Logger log = LoggerFactory.getLogger(QuartzSystemJob.class);
	
	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;

	private String jobName = "";
	private JobDetail jobDetail;
	private String fireInstanceId;
	
	public String getJobName() { return this.jobName; }
	public void setJobName(String jobName) { this.jobName = jobName; }
	public JobDetail getJobDetail() { return this.jobDetail; }
	public void setJobDetail(JobDetail jobDetail) {	this.jobDetail = jobDetail;	}
	
	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws JobExecutionException {
		setJobDetail(jobContext.getJobDetail());
		fireInstanceId = jobContext.getFireInstanceId();
		
		JobDetail jobDetail = jobContext.getJobDetail();
		JobDataMap jobDataMap = jobContext.getMergedJobDataMap();
		log.info(String.format("%s %s fired for %s at %s will fire again at %s", fireInstanceId, jobDetail.getKey(), jobDataMap.getString("SchedularJobName"), jobContext.getFireTime(), jobContext.getNextFireTime()));
	}
//	private void run(JobExecutionContext jobContext) {
//		log.info("Quartz maintenance:");
//		//Scheduler scheduler = jobContext.getScheduler();
//		//log.info("%s", scheduler.getContext().);
//		
//	}

}