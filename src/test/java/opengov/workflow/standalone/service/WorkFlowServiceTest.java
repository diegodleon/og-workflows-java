package opengov.workflow.standalone.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import opengov.workflow.standalone.model.WorkFlow;

public class WorkFlowServiceTest {
	
	private static WorkFlowService workFlowService;
	
	private static final String WORK_FLOW_NAME = "MY_WORKFLOW";
	
	@BeforeAll
	public static void init() {
		workFlowService = new WorkFlowService();
	}
	
	@Test
	public void createWorkFlowTest() {
		WorkFlow workFlow = workFlowService.createWorkFlow(WORK_FLOW_NAME);
		
		assertThat(workFlow.getName()).isEqualTo(WORK_FLOW_NAME);
	}

}
