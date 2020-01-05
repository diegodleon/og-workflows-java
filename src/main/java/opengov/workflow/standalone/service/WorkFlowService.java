package opengov.workflow.standalone.service;

import org.springframework.stereotype.Service;

import opengov.workflow.standalone.model.WorkFlow;

@Service
public class WorkFlowService {
	
	/**
	 * Create a Work flow with name
	 * 
	 * @param name Work flow Name
	 * @return new Work flow instance
	 */
	public WorkFlow createWorkFlow(String name) {
		return new WorkFlow(name);
	}

}
