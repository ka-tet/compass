package compass.file.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ApplicationStartup  implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
	
	@Autowired
	private Configuration config;

	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("================================================================================");
		logger.info("compass.file.monitor started with options: {}", (Object[]) args.getSourceArgs());
		logger.info("================================================================================");

		// Ensure that API is operational
		logger.info("Exists tests:");
		for(String url: config.getExiststesturl()) {
			logger.info(url + " " + callExists(config.getExistsurl() + url));
		}
		logger.info("Temp folder: {}", config.getTempfolder());
		logger.info("Get tests:");
		for(String url: config.getGettesturl()) {
			logger.info(url + " " + callGet(config.getGeturl() + url));
		}
		// Can also be tested using curl:
		// curl -X GET -F filename=\\\\nas1\\pers\\AIS\\ConfigAndSetup\\jssecacerts http://localhost:8080/api/exists
	}
	
	private boolean callExists(String url) {
		try {
			return WebClient.create(url).get()
                .retrieve()
                .bodyToMono(boolean.class).block();
		} catch (Exception e) {
			return false;
		}
		
	}
	private String callGet(String url) {
		try {
			return WebClient.create(url).get()
                .retrieve()
                .bodyToMono(String.class).block();
		} catch (Exception e) {
			return null;
		}
		
	}
}
