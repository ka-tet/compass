package compass.schedule.monitor;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.slf4j.Logger; import org.slf4j.LoggerFactory;

public class TriggerJob {
	private static Logger log = LoggerFactory.getLogger(TriggerJob.class);

	private String jobName = "";
	private String groupName = "";
	public String getGroupName() {
		return groupName;
	}

	private JobDetail jobDetail;
	
	
	public String getJobName() {
		return this.jobName;
	}
	
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public JobDetail getJobDetail() {
		return this.jobDetail;
	}
	
	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}
	
//	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws JobExecutionException {
//		log.info("--------------------------------------------------------------------");
		JobDetail jobDetail = jobContext.getJobDetail();
		log.info("TriggerJob " + jobDetail.getKey() + " fired at: " + jobContext.getFireTime());
//		log.info("Example name is: " + jobDetail.getJobDataMap().getString("example"));		
//		log.info("TriggerJob end: " + jobContext.getJobRunTime() + ", key: " + jobDetail.getKey());
//		log.info("TriggerJob next scheduled time: " + jobContext.getNextFireTime());
//		log.info("--------------------------------------------------------------------");
		
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
		
	}

}