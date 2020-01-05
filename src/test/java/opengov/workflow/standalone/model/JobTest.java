package opengov.workflow.standalone.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class JobTest {
	
	@Test
	public void addDependencyTest() {
		Job job = new Job("Main");
		Job dependencyJob = new Job("Dependency");
		
		job.addDependency(dependencyJob);
		
		assertThat(job.getDependencies().contains(dependencyJob));
	}
	
	@Test
	public void executeTest() {
		Job job = new Job("Main");
		
		assertThat(job.execute()).isTrue();
	}

}
