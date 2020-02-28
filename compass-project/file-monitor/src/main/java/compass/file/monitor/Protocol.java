package compass.file.monitor;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Protocol {
	
	CLASSPATH ("classpath"),
	FILESYSTEM ("file"),
	FTP ("ftp"),
	HTTP ("http"),
	HTTPS ("https"),
	SFTP ("sftp");

	private String code;
	private static final Map<String, Protocol> map = null;
	private Protocol (String code) {
		this.code = code;
	}
	static {
		Map<String, Protocol> map = new ConcurrentHashMap<String, Protocol>();
		for (Protocol p : Protocol.values()) {
			map.put(p.getCode(), p);
		}
		map = Collections.unmodifiableMap(map);
	}
	public static Protocol fromCode(String code) {
		for (Protocol p : Protocol.values()) {
			if (p.code.equalsIgnoreCase(code)) {
				return p;
			}
		}
		throw new IllegalArgumentException("Invalid protocol value.");
	}
	public String getCode() {
		return this.code;
	}
}
