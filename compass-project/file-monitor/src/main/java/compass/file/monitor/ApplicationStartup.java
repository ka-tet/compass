package compass.file.monitor;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ApplicationStartup  implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("================================================================================");
		logger.info("compass.file.monitor started with options: {}", (Object[]) args.getSourceArgs());
		logger.info("================================================================================");
		
		WebClient client = WebClient.create("http://localhost:8080/api/exists?filename=classpath:heartbeat.txt");
		WebClient.RequestBodySpec uri = client
				.method(HttpMethod.GET);
		logger.info(uri.retrieve().bodyToMono(String.class).block());
		// Can also be tested using curl:
		// curl -X GET -F filename=\\\\nas1\\pers\\AIS\\ConfigAndSetup\\jssecacerts http://localhost:8080/api/exists
	}
}
