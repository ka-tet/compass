package compass.file.monitor.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import compass.file.monitor.CompassFile;
import compass.file.monitor.FileManager;
import compass.file.monitor.FileSystemFile;

@RestController
@RequestMapping("/api")
public class FileMonitorController {
	private static final Logger logger = LoggerFactory.getLogger(FileMonitorController.class);
	
	@Autowired
	FileManager fileManager;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		return null;
	}
	
	@GetMapping(value="/exists")
	public boolean exists(@RequestParam String filename) throws MalformedURLException {
//		logger.info("/exists?filename=" + filename);
		return fileManager.exists(filename);
		
	}
	
	@GetMapping(value="/get")
	public String get(@RequestParam String filename) throws IOException, URISyntaxException {
		return fileManager.get(filename);
	}

}
