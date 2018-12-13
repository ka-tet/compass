package compass.schedule.monitor.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import compass.schedule.monitor.CompassJobFactory;
import compass.schedule.monitor.entity.Job;
import compass.schedule.monitor.entity.QuartzJobId;

@Component
public class JobRepository implements JpaRepository<Job, QuartzJobId> {
	
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

//	@Autowired
//	private CompassJobFactory schedulerJobFactory;

	public List<Job> findAllById(Iterable<QuartzJobId> id)  {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		List<Job> results = new ArrayList<Job>();
		List<QuartzJobId> search = new ArrayList<QuartzJobId>();
		try {
			for(JobKey jobKey: scheduler.getJobKeys(matcher)) {
				search.add(new QuartzJobId(jobKey.getGroup(), jobKey.getName()));
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(QuartzJobId i: id) {
			for(QuartzJobId jobId: search) {
				if(jobId.equals(i)) {
					Job job = new Job();
					job.setId(jobId);
					results.add(job);
				}
			}
		}
		return results;
	}

	@Override
	public Page<Job> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Job> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Optional<Job> findById(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public boolean existsById(String id) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	public void deleteById(String id) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void delete(Job entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Job> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Job> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Job> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Job> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Job> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Job> findAll() {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		List<Job> results = new ArrayList<Job>();
		try {
			for(JobKey jobKey: scheduler.getJobKeys(matcher)) {
				Job job = new Job();
				job.setId(new QuartzJobId(jobKey.getGroup(), jobKey.getName()));
				results.add(job);
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<Job> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Job> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Job> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Job> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public Job getOne(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public <S extends Job> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Job> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	
////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Optional<Job> findById(QuartzJobId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(QuartzJobId id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteById(QuartzJobId id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Job getOne(QuartzJobId id) {
		// TODO Auto-generated method stub
		return null;
	}
}
