package compass.file.monitor;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix="file.monitor")
public class Configuration {
	
	private String tempfolder;
	private List<String> existstesturl;
	private List<String> gettesturl;
	private String existsurl;
	private String geturl;
	public String getTempfolder() {
		return tempfolder;
	}
	public void setTempfolder(String tempfolder) {
		this.tempfolder = tempfolder;
	}
	public List<String> getExiststesturl() {
		return existstesturl;
	}
	public void setExiststesturl(List<String> existstesturl) {
		this.existstesturl = existstesturl;
	}
	public List<String> getGettesturl() {
		return gettesturl;
	}
	public void setGettesturl(List<String> gettesturl) {
		this.gettesturl = gettesturl;
	}
	public String getExistsurl() {
		return existsurl;
	}
	public void setExistsurl(String existsurl) {
		this.existsurl = existsurl;
	}
	public String getGeturl() {
		return geturl;
	}
	public void setGeturl(String geturl) {
		this.geturl = geturl;
	}

}
