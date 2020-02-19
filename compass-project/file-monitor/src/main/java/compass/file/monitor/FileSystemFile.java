package compass.file.monitor;

import java.nio.file.*;

public class FileSystemFile {
	
	public FileSystemFile() {
		
	}
	
	public FileSystemFile(String filename) {
		
	}
	
	public static boolean exists(String filename) {
		return Files.exists(Paths.get(filename));
	}

}
