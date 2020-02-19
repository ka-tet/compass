package compass.schedule.monitor.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import compass.schedule.monitor.entity.QuartzJob;
import compass.schedule.monitor.entity.QuartzJobId;

@Component
@NoRepositoryBean
public class QuartzJobRepository implements JpaRepository<QuartzJob, QuartzJobId> {
	
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

//	@Autowired
//	private QuartzJobFactory schedulerJobFactory;

	public List<QuartzJob> findAllById(Iterable<QuartzJobId> id)  {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		List<QuartzJob> results = new ArrayList<QuartzJob>();
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
					QuartzJob job = new QuartzJob();
					job.setId(jobId);
					results.add(job);
				}
			}
		}
		return results;
	}

	@Override
	public Page<QuartzJob> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends QuartzJob> S save(S entity) {
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
	public void delete(QuartzJob entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends QuartzJob> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends QuartzJob> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends QuartzJob> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends QuartzJob> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends QuartzJob> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<QuartzJob> findAll() {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		List<QuartzJob> results = new ArrayList<QuartzJob>();
		try {
			for(JobKey jobKey: scheduler.getJobKeys(matcher)) {
//				QuartzJob job = new QuartzJob();
//				job.setId(new QuartzJobId(jobKey.getGroup(), jobKey.getName()));
				results.add(new QuartzJob(new QuartzJobId(jobKey.getGroup(), jobKey.getName())));
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<QuartzJob> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends QuartzJob> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends QuartzJob> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<QuartzJob> entities) {
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
	public <S extends QuartzJob> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends QuartzJob> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}
	
////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Optional<QuartzJob> findById(QuartzJobId id) {
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
	public QuartzJob getOne(QuartzJobId id) {
		// TODO Auto-generated method stub
		return null;
	}
}
