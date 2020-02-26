package compass.file.monitor;

public abstract class File {

	protected String filename;
	
	public abstract boolean exists();
	public abstract boolean get();
	public abstract boolean put();

}
