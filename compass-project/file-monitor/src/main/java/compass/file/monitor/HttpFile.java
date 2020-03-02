package compass.file.monitor;

import java.io.IOException;
import java.net.MalformedURLException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.apache.commons.io.FileUtils;

public class HttpFile implements FileInterface {
	
	CompassFile file;

	public HttpFile(CompassFile file) {
		this.file = file;
	}
	private static final Logger logger = LoggerFactory.getLogger(HttpFile.class);
	
	public boolean exists() {
//		boolean success = WebClient.create(url).head().exchange()
//                .map(response -> response.statusCode()).block().is2xxSuccessful();
		try {
			this.file.getConnection().connect();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("Not found: {}", this.file.url);
		}
	

		return false;
	}

	public boolean get() {
		try {
			FileUtils.copyURLToFile(this.file.getUrlObject(), new java.io.File(this.file.getTempfolder()), 1, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		};
		return true;
	}

	public boolean put() {
		// TODO Auto-generated method stub
		return this.file.put();
	}

}
