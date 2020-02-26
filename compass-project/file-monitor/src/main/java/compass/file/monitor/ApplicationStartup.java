package compass.file.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ApplicationStartup  implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
	
	@Autowired
	FileManager fileManager;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("================================================================================");
		logger.info("compass.file.monitor started with options: {}", (Object[]) args.getSourceArgs());
		logger.info("================================================================================");

		String url = "http://localhost:8080/api/exists?filename=https://www.calpers.ca.gov/docs/formxs-publications/1959-survivor-valuation-2015.pdf";
		boolean status = WebClient.create(url).get()
                .retrieve()
                .bodyToMono(boolean.class).block();
		logger.info(status ? "Startup test successful" : "Startup test failed");
		url = "http://localhost:8080/api/exists?filename=classpath:heartbeat.txt";
		 status = WebClient.create(url).get()
                .retrieve()
                .bodyToMono(boolean.class).block();
		logger.info(status ? "Startup test successful" : "Startup test failed");
		// Can also be tested using curl:
		// curl -X GET -F filename=\\\\nas1\\pers\\AIS\\ConfigAndSetup\\jssecacerts http://localhost:8080/api/exists
	}
}
