package compass.file.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

public class HttpFile extends File {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
	
	@Override
	public boolean exists() {
		WebClient client = WebClient.create(filename);
		WebClient.RequestBodySpec uri = client.method(HttpMethod.HEAD);
		logger.info(uri.retrieve().bodyToMono(String.class).block());
		return false;
	}

	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean put() {
		// TODO Auto-generated method stub
		return false;
	}

}
