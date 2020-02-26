package compass.file.monitor;

public enum Protocol {
	
	FILESYSTEM ("file", "filesystem", FileSystemFile.class),
	FTP ("ftp", "ftp", FileSystemFile.class),
	SFTP ("sftp", "sftp", FileSystemFile.class),
	HTTP ("http", "http", HttpFile.class);

	private String description;
	private String code;
	private Class subclass;
	private Protocol (String code, String description, Class subclass) {
		this.description = description;
		this.code = code;
		this.subclass = subclass;
	}
//	public File getSubclassByCode(String code) {
//		Class subclass;
//		
//	}
}
