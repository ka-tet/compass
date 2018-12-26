package compass.schedule.monitor.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class QuartzJob {
	
	@EmbeddedId
	private QuartzJobId id;
	
	public QuartzJob(QuartzJobId quartzJobId) {
		// TODO Auto-generated constructor stub
		setId(quartzJobId);
	}
	public QuartzJob() {
		// TODO Auto-generated constructor stub
	}
	public QuartzJobId getId() { return id; }
	public QuartzJob setId(QuartzJobId id) { this.id = id; return this; }
//	public String getScheduleName() { return id.getScheduleName(); }
	public String getGroupName() { return id.getGroupName(); }
	public String getJobName() { return id.getJobName(); }

}
