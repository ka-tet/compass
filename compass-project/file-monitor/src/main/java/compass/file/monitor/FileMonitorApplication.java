package compass.file.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileMonitorApplication {

	private static Logger log = LoggerFactory.getLogger(FileMonitorApplication.class);

	@Autowired
	ApplicationMonitor applicationMonitor;
	
	public static void main(String[] args) {
		SpringApplication.run(FileMonitorApplication.class, args);
		log.info("Compass File Monitor started.");
	}
	
}
