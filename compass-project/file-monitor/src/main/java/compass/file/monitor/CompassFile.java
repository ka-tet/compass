package compass.file.monitor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class CompassFile implements FileInterface{

	private static final Logger logger = LoggerFactory.getLogger(CompassFile.class);
	
	@Autowired
	protected Configuration config;
	
	public String url;
	protected Protocol protocol;
	protected String scheme;
	protected int port;
	protected String host;
	protected String path;
	protected String authority;
	protected URL tempFileUrl;
	
	private Class subclass;
	
	private FileInterface typedFile;
	
	private URL urlObject;
	
	public CompassFile(String url) throws MalformedURLException {
		this.url = url;
		init(getUrlFromUrlString(url));
	}
	
	public CompassFile(URL url) throws MalformedURLException {
		init(url);
	}

	public CompassFile() {
		
	}
	public URL getTempFileUrl() {
		if(tempFileUrl == null) {
			try {
				tempFileUrl = new URL(config.getTempfolder() + UUID.randomUUID().toString() + ".dat");
			} catch (MalformedURLException e) {
				logger.info("Malformed temp folder URL: {}", config.getTempfolder());
			}
		}
		return tempFileUrl;
	}
	
	public URL getUrlObject() {
		return urlObject;
	}

	public void setUrlObject(URL urlObject) {
		this.urlObject = urlObject;
	}

	public URLConnection getConnection() {
		URLConnection connection = null;
		try {
			connection = urlObject.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("Not found: connection {}", url);
		}
		return connection;
	}
	
	public static String getProtocol(String filename) {
		String protocol = null;
		try {
			URL url = new URL(filename);
			protocol = url.getProtocol();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return protocol;
	}
	public static URL getUrlFromUrlString(String url) throws MalformedURLException {
		try {
			return new URL (url);
		} catch (Exception e) {
			throw new MalformedURLException("Invalid url");
		}
	}
	public boolean exists() {
		return this.typedFile.exists();
	}
	public boolean get() {
		return this.typedFile.get();
	}
	public boolean put() {
		return this.typedFile.put();
	}
	
	private void init(URL url) throws MalformedURLException {
		try {
			this.urlObject = url;
			this.scheme = this.urlObject.getProtocol();
			this.protocol = Protocol.fromCode(this.scheme);
			this.port = this.urlObject.getPort();
			this.host = this.urlObject.getHost();
			this.path = this.urlObject.getPath();
			this.authority = this.urlObject.getAuthority();
			switch(protocol) {
			case HTTP:
				this.typedFile = new HttpFile(this);
				break;
			case HTTPS:
				this.typedFile = new HttpFile(this);
				break;
			case FILESYSTEM:
				this.typedFile = new FileSystemFile(this);
				break;
			case CLASSPATH:
				this.typedFile = new ClasspathFile(this);
				break;
			case FTP:
			case SFTP:
				this.typedFile = new FtpFile(this);
				break;
			default:
				logger.info("Protocol '{}' not implemented.");
			}
		} catch (MalformedURLException e) {
			logger.info("Invalid url: {}", url);
			throw e;
		}
		logger.info("config.getTempfolder: {}", config.getTempfolder());
		
		
	}

}
