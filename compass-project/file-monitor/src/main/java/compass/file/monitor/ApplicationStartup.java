package compass.file.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ApplicationStartup  implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
	
	@Autowired
	FileManager fileManager;

	@Value("${smoketesturl:}")
	List<String> urls;
	@Value("${existsurl}")
	String existsurl;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("================================================================================");
		logger.info("compass.file.monitor started with options: {}", (Object[]) args.getSourceArgs());
		logger.info("================================================================================");

		for(String url: urls) {
			boolean status;
			try {
				status = WebClient.create(existsurl + url).get()
	                .retrieve()
	                .bodyToMono(boolean.class).block();
			} catch (Exception e) {
				status = false;
			}
			logger.info(status ? url + " successful" : url + " failed");
		}
		
		// Can also be tested using curl:
		// curl -X GET -F filename=\\\\nas1\\pers\\AIS\\ConfigAndSetup\\jssecacerts http://localhost:8080/api/exists
	}
}
