package compass.file.monitor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

public class FileSystemFile extends File {
	
	public FileSystemFile(String filename) throws MalformedURLException {
		super(filename);
		
	}
	
	public boolean get() {
		return true;
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

//	@Override
	public boolean exists() {
		return Files.exists(Paths.get(url));
	}

	@Override
	public boolean put() {
		// TODO Auto-generated method stub
		return false;
	}

}
