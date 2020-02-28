package compass.file.monitor;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClasspathFile extends FileSystemFile {

	private static final Logger logger = LoggerFactory.getLogger(ClasspathFile.class);

	public ClasspathFile(File file) throws MalformedURLException {
		super(file);
		// TODO Auto-generated constructor stub
	}
}
