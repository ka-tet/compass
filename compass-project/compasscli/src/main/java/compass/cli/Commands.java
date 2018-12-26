package compass.cli;

import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.CellMatchers;
import org.springframework.shell.table.NoWrapSizeConstraints;
import org.springframework.shell.table.SizeConstraints;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;

//import calpers.eai.epm.engine.quartz.QuartzSchedulerService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.jline.utils.Log;
//import org.quartz.JobDetail;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.web.client.RestTemplate;

import compass.schedule.monitor.TriggerJob;
import compass.schedule.monitor.api.*;
@ShellComponent
public class Commands {
	
//	@Autowired
//	QuartzSchedulerService schedulerService;
//	
//	@ShellMethod(value ="List schedulers.", key= {"list schedulers", "li sc", "ls"})
//	public Table listSchedulers() {
//		List<Scheduler> schedulers = schedulerService.getSchedulers();
//		List<String> results = new ArrayList<String>();
//		if(!(schedulers.size() > 0)) {
//			results.add("No schedulers found.");
//			Object[][] data = new Object[3][];
//			data[0] = new Object[] { "XXX", "No schedulers found."};
//			TableBuilder tableBuilder = new TableBuilder(new ArrayTableModel(data));
//			return tableBuilder.build();
//
//		} else {
//			LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
//			headers.put("schedulerName", "Name");
//			headers.put("schedulerInstanceId", "Id");
//			headers.put("metaData.runningSince", "Started");
//			headers.put("metaData.numberOfJobsExecuted", "Jobs Exec.");
//			headers.put("metaData.threadPoolSize", "Threads");
////			headers.put("currentlyExecutingJobs.size.toString", "Running");
////			headers.put("jobStoreClass.name", "Job Store");
////			headers.put("jobStoreClustered", "Clustered");
//		
//			TableModel model = new BeanListTableModel<>(schedulers, headers);
//			TableBuilder tableBuilder = new TableBuilder(model);
//			tableBuilder.addFullBorder(BorderStyle.oldschool);
//			tableBuilder.on(CellMatchers.table()).addSizer(new NoWrapSizeConstraints());
//			return tableBuilder.build();
//		}
//	}
	@ShellMethod(value ="List jobs.", key= {"list jobs", "li jo", "lj"})
	public Table listJobs() {
		RestTemplate restTemplate = new RestTemplate();
//		var jobs = restTemplate.getForObject("http://localhost:8088/api/jobs", TriggerJob.class);
		ResponseEntity<List<TriggerJob>> response = restTemplate.exchange(
		  "http://localhost:8088/api/jobs/",
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<TriggerJob>>(){});
		List<TriggerJob> jobs = response.getBody();
		Log.info(response.toString());
//		return jobs;
//		List<JobDetail> jobs = schedulerService.getJobs();
		if(!(jobs.size() > 0)) {
			Object[][] data = new Object[1][];
			data[0] = new Object[] { "XXX", "No jobs found."};
			TableBuilder tableBuilder = new TableBuilder(new ArrayTableModel(data));
			return tableBuilder.build();
//
		} else {
			LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
			headers.put("groupName",  "Group Name");
			headers.put("jobName", "Job Name");
//			headers.put("description", "Description");
//			headers.put("jobDataMap", "Data Map");
//		
			TableModel model = new BeanListTableModel<>(jobs, headers);
			TableBuilder tableBuilder = new TableBuilder(model);
			tableBuilder.addFullBorder(BorderStyle.oldschool);
			tableBuilder.on(CellMatchers.table()).addSizer(new NoWrapSizeConstraints());
			return tableBuilder.build();
		}
	}
}
