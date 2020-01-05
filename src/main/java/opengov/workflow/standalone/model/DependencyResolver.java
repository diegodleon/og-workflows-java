package opengov.workflow.standalone.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class DependencyResolver {

	private List<Job> jobList;

	private Queue<Job> executionQ = new LinkedList<>();
	
	private Set<Job> traversedJobs = new HashSet<>();

	/**
	 * Constructor takes a List<Job> which represents the job list to be sorted out
	 * for correct execution order based on Job's dependencies.
	 * 
	 * @param jobs List<Job> list of jobs to sort out proper execution order
	 */
	public DependencyResolver(List<Job> jobs) {
		this.jobList = jobs;
	}

	/**
	 * This method figures out the execution order based on dependencies for the source Job list in jobList.
	 * 
	 * @return A Queue<Job> which has proper job execution order based on job dependencies.
	 */
	public Queue<Job> getExecutionQ() {

		Iterator<Job> it = null;
		
		while ( !jobList.isEmpty() ) {
			if (it == null || !it.hasNext()) {
				it = jobList.iterator();
			}
			
			Job job = it.next();
			
			if (isDependencyReady(job)) {
				markTraversed(job);
				executionQ.add(job);
				jobList.remove(job);
				it = jobList.iterator();
			}
		}

		return executionQ;
	}
	
	private void markTraversed(Job job) {
		traversedJobs.add(job);
	}
	
	private Boolean isDependencyReady(Job job) {
		return job.getDependencies().stream().allMatch(j -> traversedJobs.contains(j));
	}

}
