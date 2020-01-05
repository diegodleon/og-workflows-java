package opengov.workflow.standalone.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import opengov.workflow.standalone.contract.Executable;

public class WorkFlow implements Executable{
	
	private String name;
	
	private List<Job> jobList = new ArrayList<>();
	
	private Queue<Job> executionQ;
	
	/**
	 * Class represents a Work Flow which contains several Jobs
	 * @param name Work flow name
	 */
	public WorkFlow(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * Register a Job to this work flow
	 * 
	 * @param job Job to register
	 * @return This very work flow for chaining calls
	 */
	public WorkFlow registerJob(Job job) {
		jobList.add(job);
		return this;
	}
	
	private void calculateExecutionQ() {
		this.executionQ = new DependencyResolver(jobList).getExecutionQ();
	}

	/**
	 * Execute the Work flow
	 * 
	 * @return True
	 */
	@Override
	public Boolean execute() {
		calculateExecutionQ();
		
		System.out.printf("\nExecuting WorkFlow %s:\n", getName());

		executionQ.stream().forEach(Executable::execute);
		
		System.out.println("WorkFlow Complete!!");
		
		return Boolean.TRUE;
	}

}
