package opengov.workflow.standalone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import opengov.workflow.standalone.model.Job;
import opengov.workflow.standalone.service.JobService;
import opengov.workflow.standalone.service.WorkFlowService;

@SpringBootApplication
public class OpengovWorkflowStandAloneApplication implements CommandLineRunner {

	@Autowired
	private JobService jobService;

	@Autowired
	private WorkFlowService workFlowService;

	public static void main(String[] args) {
		SpringApplication.run(OpengovWorkflowStandAloneApplication.class, args);
	}

	/**
	 * 
	 * Re-write contents in this method to execute whatever you please.
	 * 
	 * Service layers are contained in jobService and workFlowService which are self
	 * explanatory.
	 * 
	 * Go ahead and create different jobs and work flows.
	 * 
	 * @see CommandLineRunner and SpringBoot standalone apps
	 * 
	 */

	@Override
	public void run(String... args) throws Exception {

		Job job1 = jobService.createJob("A");

		Job job2 = jobService.createJob("B");
		job2.addDependency(job1);

		Job job3 = jobService.createJob("C");
		job3.addDependency(job1, job2);

		Job job4 = jobService.createJob("D");
		job4.addDependency(job2);

		Job job5 = jobService.createJob("E");
		job5.addDependency(job1, job4, job2);

		workFlowService.createWorkFlow("X").registerJob(job3).registerJob(job2).registerJob(job1).registerJob(job5)
				.registerJob(job4).execute();

	}

}
