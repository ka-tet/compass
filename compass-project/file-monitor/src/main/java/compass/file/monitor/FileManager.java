package compass.file.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileManager {
	private static final Logger logger = LoggerFactory.getLogger(FileManager.class);
	
	public static boolean exists(String filename) {
		Protocol protocol = Protocol.fromCode(File.getProtocol(filename));
		File file = null;
		
		switch(protocol) {
		case HTTP:
			file = new HttpFile(filename);
			break;
		case HTTPS:
			file = new HttpFile(filename);
			break;
		case FILESYSTEM:
			file = new FileSystemFile(filename);
			break;
		case FTP:
		case SFTP:
		default:
			logger.info("Protocol '{}' not implemented.");
		}
		return file.exists();
	}
	
	public void copy(File from, File to) {
		
	}
	

}
