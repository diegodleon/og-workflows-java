package opengov.workflow.standalone.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class WorkFlowTest {
	
	@Test
	public void executeTest() {
		
		Job job1 = new Job("JOB1");
		Job job2 = new Job("JOB2");
		
		WorkFlow wf = new WorkFlow("WORK_FLOW");
		
		wf.registerJob(job1).registerJob(job2);
		
		assertThat(wf.execute()).isTrue();
		
	}
}
