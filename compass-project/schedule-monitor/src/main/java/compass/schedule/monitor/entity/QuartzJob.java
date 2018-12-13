package compass.schedule.monitor.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class QuartzJob {
	
	@EmbeddedId
	private QuartzJobId id;
	
	public QuartzJobId getId() { return id; }
	public QuartzJob setId(QuartzJobId id) { this.id = id; return this; }
//	public String getScheduleName() { return id.getScheduleName(); }
	public String getGroupName() { return id.getGroupName(); }
	public String getJobName() { return id.getJobName(); }

}
