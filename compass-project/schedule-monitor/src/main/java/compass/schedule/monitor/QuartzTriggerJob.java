package compass.schedule.monitor;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import org.slf4j.Logger; import org.slf4j.LoggerFactory;

public class QuartzTriggerJob extends QuartzJobBean {
	private static Logger log = LoggerFactory.getLogger(QuartzTriggerJob.class);

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

}