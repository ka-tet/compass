package compass.file.monitor;

import java.nio.file.*;

public class FileSystemFile {
	
	public void FileSystemFile() {
		
	}
	
	public void FileSystemFile(String filename) {
		
	}
	
	public static boolean exists(String filename) {
		return Files.exists(Paths.get("C:\\" + filename));
	}

}
