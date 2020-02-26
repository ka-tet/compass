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
	
	private URL urlObject;
	
	public File(String url) {
		this.url = url;
		try {
			this.urlObject = new URL(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			URL parse = new URL(url);
			this.protocol = parse.getProtocol();
			this.port = parse.getPort();
			this.host = parse.getHost();
			this.path = parse.getPath();
			this.authority = parse.getAuthority();
			logger.info("Protocol: {}", this.protocol);
			logger.info("Port: {}", this.port);
			logger.info("Host: {}", this.host);
			logger.info("Path: {}", this.path);
			logger.info("Authority: {}", this.authority);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public abstract boolean exists();
	public abstract boolean get();
	public abstract boolean put();

}
