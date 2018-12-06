package compass.schedule.monitor.api;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import compass.schedule.monitor.QuartzJobService;
import compass.schedule.monitor.QuartzSchedulerService;
import compass.schedule.monitor.repositories.QuartzJobRepository;

@RestController
@RequestMapping("/api")
public class ScheduleMonitorController {
	
	@Autowired
	QuartzSchedulerService quartzSchedulerService;
	
	@Autowired
	QuartzJobService schedulerJobService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		return null; // new ResponseEntity(schedulerJobService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/schedulers")
	public ResponseEntity<?> getSchedulers() throws SchedulerException {
		var response = new ResponseEntity(quartzSchedulerService.getSchedulers(), HttpStatus.OK);
		return response;
	}

}
