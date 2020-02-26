package compass.file.monitor;

import org.springframework.stereotype.Service;

@Service
public class FileManager {
	
	public static boolean exists(String filename) {
		return new HttpFile(filename).exists();
	}
	
	public void copy(File from, File to) {
		
	}
	

}
