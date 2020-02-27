package compass.file.monitor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class File {

	private static final Logger logger = LoggerFactory.getLogger(File.class);
	
	
	protected String url;
	protected String protocol;
	protected int port;
	protected String host;
	protected String path;
	protected String authority;
	
	private Class subclass;
	
	private File typedFile;
	
	private URL urlObject;
	
	public File(String url) throws MalformedURLException {
		this.url = url;
		try {
			this.urlObject = getUrlFromUrlString(url);
			this.protocol = this.urlObject.getProtocol();
			this.port = this.urlObject.getPort();
			this.host = this.urlObject.getHost();
			this.path = this.urlObject.getPath();
			this.authority = this.urlObject.getAuthority();
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
			e.printStackTrace();
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
	public abstract boolean exists();
	public abstract boolean get();
	public abstract boolean put();

}
