package compass.file.monitor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ApplicationMonitor {

	private static Logger log = LoggerFactory.getLogger(ApplicationMonitor.class);
	private int timeout;
	
	public ApplicationMonitor(int timeout) {
		this.timeout = timeout;
	}
	
	public ApplicationMonitor() {
		this.timeout = 0;
	}
	
	@PostConstruct
	public void init() throws Exception {
		log.info("Started. Timeout: {}", timeout);
	}
	
	@PreDestroy
	public void destroy() {
		log.info("Shutdown.");
	}
}
