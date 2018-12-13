package compass.schedule.monitor;

import javax.annotation.PreDestroy;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import compass.schedule.monitor.api.ScheduleMonitorController;

import org.slf4j.Logger; import org.slf4j.LoggerFactory;

@SpringBootApplication // (exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ScheduleMonitorApplication {
	
	private static Logger log = LoggerFactory.getLogger(ScheduleMonitorApplication.class);
	
	@Autowired
	ApplicationMonitor applicationMonitor;
	
	@Autowired
	ScheduleMonitorController scheduleMonitorController;
	
	public static void main(String[] args) { // After CommandLineRunner started.
		SpringApplication.run(ScheduleMonitorApplication.class, args);
		log.info("Schedular application started.");
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx){ // After ApplicationMonitor started.
		return args -> {
			log.info("CommandLineRunner " + new java.util.Date());
//			log.info(scheduleMonitorController.getSchedulers().toString());
		};
	}
}
