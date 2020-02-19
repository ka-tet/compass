package compass.file.monitor.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import compass.file.monitor.FileSystemFile;

@RestController
@RequestMapping("/api")
public class FileMonitorController {
	private static final Logger logger = LoggerFactory.getLogger(FileMonitorController.class);
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		return null;
	}
	
	@GetMapping(value="/exists")
	public boolean exists(@RequestParam String filename) {
		logger.info("/exists?filename=" + filename);
		return FileSystemFile.exists(filename);
		
	}

}
