package compass.file.monitor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class File implements FileInterface{

	private static final Logger logger = LoggerFactory.getLogger(File.class);
	
	
	protected String url;
	protected Protocol protocol;
	protected String scheme;
	protected int port;
	protected String host;
	protected String path;
	protected String authority;
	
	private Class subclass;
	
	private FileInterface typedFile;
	
	private URL urlObject;
	
	public File(String url) throws MalformedURLException {
		this.url = url;
		try {
			this.urlObject = getUrlFromUrlString(url);
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
			default:
				logger.info("Protocol '{}' not implemented.");
			}
		} catch (MalformedURLException e) {
			logger.info("Invalid url: {}", url);
			throw e;
		}
		
	}
	
	protected URLConnection getConnection() {
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

}
