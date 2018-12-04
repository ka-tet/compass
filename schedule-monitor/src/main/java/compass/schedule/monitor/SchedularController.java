package compass.schedule.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import compass.schedule.monitor.repositories.QuartzJobRepository;

@RestController
@RequestMapping("/api")
public class SchedularController {
	
	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	QuartzJobService schedulerJobService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		return null; // new ResponseEntity(schedulerJobService.findAll(), HttpStatus.OK);
	}

}
