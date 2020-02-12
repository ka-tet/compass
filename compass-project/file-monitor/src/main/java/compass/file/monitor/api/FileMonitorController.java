package compass.file.monitor.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import compass.file.monitor.FileSystemFile;

@RestController
@RequestMapping("/api")
public class FileMonitorController {
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		return null;
	}
	
	@GetMapping(value="/exists/{filename}")
	public boolean exists(@PathVariable String filename) {
		return FileSystemFile.exists(filename);
		
	}

}
