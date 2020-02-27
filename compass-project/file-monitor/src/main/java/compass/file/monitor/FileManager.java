package compass.file.monitor;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileManager {
	private static final Logger logger = LoggerFactory.getLogger(FileManager.class);
	
	public static boolean exists(URL url) {
		return true;
	}
	
	public static boolean exists(String url) throws MalformedURLException {
		Protocol protocol = Protocol.fromCode(File.getProtocol(url));
		File file = null;
		
		switch(protocol) {
		case HTTP:
			file = new HttpFile(url);
			break;
		case HTTPS:
			file = new HttpFile(url);
			break;
		case FILESYSTEM:
			file = new FileSystemFile(url);
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
