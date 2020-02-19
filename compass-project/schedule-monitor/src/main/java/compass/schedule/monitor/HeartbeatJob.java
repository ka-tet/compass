package compass.schedule.monitor;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;

public class HeartbeatJob extends QuartzTriggerJob {
	private static Logger log = LoggerFactory.getLogger(HeartbeatJob.class);

//	@Override
	protected void executeInternal(JobExecutionContext jobContext) throws JobExecutionException {
		setJobDetail(jobContext.getJobDetail());
		fireInstanceId = jobContext.getFireInstanceId();
		StringBuilder sb = new StringBuilder("♥♥♥♥♥ ");
		sb.append(fireInstanceId);
		JobDetail jobDetail = jobContext.getJobDetail();
		JobDataMap jobDataMap = jobContext.getMergedJobDataMap();
		String filename = jobDataMap.getString("filename");
		if(jobDataMap.getBooleanFromString("checkFileMonitor").booleanValue()) {
			WebClient client = WebClient.create(jobDataMap.getString("fileMonitorUrl"));
			WebClient.RequestBodySpec uri = client.method(HttpMethod.GET);
			
		}
		log.info(String.format("♥♥♥♥♥ %s", fireInstanceId));
	}

}