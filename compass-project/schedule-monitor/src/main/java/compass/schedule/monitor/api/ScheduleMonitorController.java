package compass.schedule.monitor.api;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import compass.schedule.monitor.JobService;
import compass.schedule.monitor.QuartzSchedulerService;

@RestController
@RequestMapping("/api")
public class ScheduleMonitorController {
	
	@Autowired
	QuartzSchedulerService quartzSchedulerService;
	
	@Autowired
	JobService jobService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		return null; // new ResponseEntity(schedulerJobService.findAll(), HttpStatus.OK);
	}
	
//	@GetMapping("/schedulers")
//	public ResponseEntity<?> getSchedulers() throws SchedulerException {
//		var response = new ResponseEntity(quartzSchedulerService.getSchedulers(), HttpStatus.OK);
//		return response;
//	}

	@GetMapping("/jobs")
	public ResponseEntity<?> getJobs() throws SchedulerException {
		ResponseEntity<?> response = new ResponseEntity<List<?>>(jobService.getJobs(), HttpStatus.OK);
		return response;
	}

}
