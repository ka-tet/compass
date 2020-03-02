package compass.file.monitor;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpFile implements FileInterface {

	private static final Logger logger = LoggerFactory.getLogger(FtpFile.class);

	private CompassFile file;
	
	public FtpFile(CompassFile file) {
		this.file = file;
	}
	
	public boolean exists() {
		try {
			this.file.getConnection().connect();
			return true;
		} catch (IOException e) {
			logger.info("Not found: {}", this.file.url);
		}
		return false;
	}

	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean put() {
		// TODO Auto-generated method stub
		return false;
	}

}
