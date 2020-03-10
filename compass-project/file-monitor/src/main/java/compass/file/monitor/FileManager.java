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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileManager {
	private static final Logger logger = LoggerFactory.getLogger(FileManager.class);

	@Autowired
	private Configuration config;
	
	public static boolean exists(URL url) {
		return true;
	}
	
	public boolean exists(String url) throws MalformedURLException {
		CompassFile file = new CompassFile(url, config.getTempfolder());
		return file.exists();
	}
	
	public void copy(CompassFile from, CompassFile to) {
		
	}
	
	public String get(String url) throws IOException, URISyntaxException {
		String tempFolder = config.getTempfolder();
		CompassFile file = new CompassFile(url, config.getTempfolder());
		File tempFile = new File(file.getTempFileUrl().toURI());
		logger.info("Temp file: {}",tempFile.toURI().toURL().toString());
		FileUtils.copyURLToFile(file.getUrlObject(), tempFile);
		return (tempFile.toURI().toURL().toString());
	}
}
