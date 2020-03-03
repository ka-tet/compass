package compass.file.monitor;

import org.springframework.beans.factory.FactoryBean;

public class CompassFileFactory implements FactoryBean<FileInterface> {

	@Override
	public CompassFile getObject() throws Exception {
		FileInterface file;
		return new CompassFile();
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

}
