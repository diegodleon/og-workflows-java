package opengov.workflow.standalone.service;

import opengov.workflow.standalone.model.Job;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JobServiceTest {

	private static JobService jobService;
	
	private final String JOB_NAME = "MY_JOB_TEST";
	
	@BeforeAll
	public static void init() {
		jobService = new JobService();
	}
	
	@Test
	public void createJobTest() {
		Job newJob = jobService.createJob(JOB_NAME);
		
		assertThat(newJob.getName()).isEqualTo(JOB_NAME);
	}
	
}
