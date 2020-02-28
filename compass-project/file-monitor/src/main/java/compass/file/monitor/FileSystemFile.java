package compass.file.monitor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSystemFile implements FileInterface {
	private static final Logger logger = LoggerFactory.getLogger(FileSystemFile.class);
	
	protected File file;
	
	public FileSystemFile(File file) throws MalformedURLException {
		this.file = file;
		
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
			logger.info("Not found copy: {}", from);
		}
		
		return;
	}

//	@Override
	public boolean exists() {
		try {
			this.file.getConnection().connect();
			return true;
		} catch (Exception e) {
			logger.info("Not found fsf: {}", file.url);
		}
		return false;
	}

	@Override
	public boolean put() {
		// TODO Auto-generated method stub
		return this.file.put();
	}
}
