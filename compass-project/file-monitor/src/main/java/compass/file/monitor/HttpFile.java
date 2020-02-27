package compass.file.monitor;

import java.io.IOException;
import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

public class HttpFile extends File {

	public HttpFile(String filename) throws MalformedURLException {
		super(filename);
	}

	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
	
	@Override
	public boolean exists() {
//		boolean success = WebClient.create(url).head().exchange()
//                .map(response -> response.statusCode()).block().is2xxSuccessful();
		try {
			getConnection().connect();
			logger.info("Length: {}", getConnection().getHeaderFields());
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

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
