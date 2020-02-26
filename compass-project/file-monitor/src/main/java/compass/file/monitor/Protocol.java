package compass.file.monitor;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Protocol {
	
	FILESYSTEM ("file"),
	FTP ("ftp"),
	SFTP ("sftp"),
	HTTP ("http"),
	HTTPS ("https");

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
		return null;
	}
	public String getCode() {
		return this.code;
	}
}
