package compass.file.monitor;

public enum FileType {
	
	FILESYSTEM ("filesystem", "file", FileSystemFile.class),
	FTP ("sftp", "sftp", FileSystemFile.class),
	SFTP ("sftp", "sftp", FileSystemFile.class),
	HTTP ("http", "http", FileSystemFile.class);

	private final String fileTypeDescription;
	private final String fileTypeCode;
	private final Class fileTypeClass;
	private FileType (String fileTypeDescription, String fileTypeCode, Class fileTypeClass) {
		this.fileTypeDescription = fileTypeDescription;
		this.fileTypeCode = fileTypeCode;
		this.fileTypeClass = fileTypeClass;
	}
}
