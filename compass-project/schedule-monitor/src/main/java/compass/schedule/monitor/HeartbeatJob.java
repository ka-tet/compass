package compass.schedule.monitor;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import org.slf4j.Logger; import org.slf4j.LoggerFactory;

public class HeartbeatJob extends QuartzTriggerJob {
	private static Logger log = LoggerFactory.getLogger(HeartbeatJob.class);

	
//	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws JobExecutionException {
		setJobDetail(jobContext.getJobDetail());
		fireInstanceId = jobContext.getFireInstanceId();
		
		JobDetail jobDetail = jobContext.getJobDetail();
		JobDataMap jobDataMap = jobContext.getMergedJobDataMap();
		for(var key: jobDataMap.getKeys()) {
			log.info(String.format("%s: %s", key, jobDataMap.getString(key)));
		}
		log.info(String.format("Heartbeat %s %s fired at %s will fire again at %tc", fireInstanceId, jobDetail.getKey(), jobContext.getFireTime(), jobContext.getNextFireTime()));
	}

}