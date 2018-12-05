package compass.schedule.monitor.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Job {
	
	@EmbeddedId
	private QuartzJobId id;
	
	public QuartzJobId getId() { return id; }
	public Job setId(QuartzJobId id) { this.id = id; return this; }

}
