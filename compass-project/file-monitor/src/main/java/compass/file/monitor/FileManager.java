package compass.file.monitor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileManager {
	private static final Logger logger = LoggerFactory.getLogger(FileManager.class);
	
	public static boolean exists(URL url) {
		return true;
	}
	
	public static boolean exists(String url) throws MalformedURLException {
		CompassFile file = new CompassFile(url);
		return file.exists();
	}
	
	public void copy(CompassFile from, CompassFile to) {
		
	}
	
	public static String get(String url) throws IOException, URISyntaxException {
		CompassFile file = new CompassFile(url);
		File tempFile = new File(file.getTempFileUrl().toURI());
		FileUtils.copyURLToFile(file.getUrlObject(), tempFile);
		logger.info(tempFile.toURI().toURL().toString());
		return (tempFile.toURI().toURL().toString());
	}
}
