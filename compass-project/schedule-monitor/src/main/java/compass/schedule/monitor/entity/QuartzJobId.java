package compass.schedule.monitor.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class QuartzJobId implements Serializable{
	static final long serialVersionUID = 0;
	
//	private String scheduleName;
	private String groupName;
	private String jobName;

	public QuartzJobId() { 
		
	}
	
	public QuartzJobId(String groupName, String jobName) {
//		this.scheduleName = scheduleName;
		this.groupName = groupName;
		this.jobName = jobName;
	}
	
//	public String getScheduleName() { return this.scheduleName; }
	public String getGroupName() { return this.groupName; }
	public String getJobName() {return this.jobName; }
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuartzJobId)) return false;
        QuartzJobId that = (QuartzJobId) o;
        return // Objects.equals(getScheduleName(), that.getScheduleName()) &&
        		Objects.equals(getGroupName(), that.getGroupName()) &&
        		Objects.equals(getJobName(), that.getJobName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupName(), getJobName());
    }

}
