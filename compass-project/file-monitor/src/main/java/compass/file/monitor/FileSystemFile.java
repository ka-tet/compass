package compass.file.monitor;

import java.io.IOException;
import java.nio.file.*;

public class FileSystemFile {
	
	public FileSystemFile() {
		
	}
	
	public FileSystemFile(String filename) {
		
	}
	
	public static boolean exists(String filename) {
		return Files.exists(Paths.get(filename));
	}
	
	public static void copy(String from, String to) {
		Path fromPath = Paths.get(from);
		Path toPath = Paths.get(to);
		
		try {
			Files.copy(fromPath, toPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}

}
