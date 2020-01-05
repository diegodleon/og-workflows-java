package opengov.workflow.standalone.service;

import org.springframework.stereotype.Service;
import opengov.workflow.standalone.model.Job;

@Service
public class JobService {

	/**
	 * Create a Job with name
	 * 
	 * @param name Job Name
	 * @return new Job instance
	 */
	public Job createJob(String name) {
		return new Job(name);
	}
	
	
}
