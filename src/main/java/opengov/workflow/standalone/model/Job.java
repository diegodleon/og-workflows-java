package opengov.workflow.standalone.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import opengov.workflow.standalone.contract.Executable;

public class Job implements Executable{
	
	private String name;
	
	private Set<Job> dependecySet = new HashSet<>();
	
	/**
	 * Class represents a Job.
	 * 
	 * @param name Job Name
	 */
	public Job(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * Add a Job dependency to this job.
	 * 
	 * @param jobs 1 .. n Job references to register as dependencies
	 * @return this very same Job object for chaining method calls
	 */
	public Job addDependency(Job... jobs) {
		Arrays.stream(jobs).forEach(job -> dependecySet.add(job));
		return this;
	}
	
	public Set<Job> getDependencies() {
		return dependecySet;
	}

	/**
	 * Execute the Job
	 * 
	 * @return True
	 */
	@Override
	public Boolean execute() {
		
		System.out.printf("Executing Job %s\n", getName());
		
		return Boolean.TRUE;
	}

}
