package opengov.workflow.standalone.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Test;

public class DependencyResolverTest {
	
	@Test
	public void getExecutionQTestWithEmptyJobListReturnsEmptyQ() {
		
		DependencyResolver dr = new DependencyResolver(new ArrayList<Job>());
		
		assertThat(dr.getExecutionQ().isEmpty()).isTrue();
		
	}
	
	@Test
	public void getExecutionQTestWithOneElementJobListReturnsQWithElement() {
		Job job1 = new Job("Job1");
		List<Job> jobList = new ArrayList<>();
		jobList.add(job1);
		
		DependencyResolver dr = new DependencyResolver(jobList);
		Queue<Job> resultQ = dr.getExecutionQ();
		
		assertThat(resultQ.size() == 1).isTrue();
		assertThat(resultQ.contains(job1)).isTrue();
		
	}
	
	@Test
	public void getExecutionQTestWithNoDependenciesReturnsQWithAllElements() {
		Job job1 = new Job("Job1");
		Job job2 = new Job("Job2");
		
		List<Job> jobList = new ArrayList<>();
		jobList.add(job1);
		jobList.add(job2);
		
		DependencyResolver dr = new DependencyResolver(jobList);
		Queue<Job> resultQ = dr.getExecutionQ();
		
		assertThat(resultQ.contains(job1)).isTrue();
		assertThat(resultQ.contains(job2)).isTrue();
		
	}
	
	@Test
	public void getExecutionQTestWithMultipleElementsJobListReturnsQInCorrectDependencyOrder() {
		
		Job job1 = new Job("Job1");
		Job job2 = new Job("Job2");
		Job job3 = new Job("Job2");
		
		job1.addDependency(job2);
		job2.addDependency(job3);
		
		Queue<Job> expectedResult = new LinkedList<>();
		expectedResult.add(job3);
		expectedResult.add(job2);
		expectedResult.add(job1);
		
		List<Job> jobList = new ArrayList<>();
		jobList.add(job1);
		jobList.add(job2);
		jobList.add(job3);
		
		DependencyResolver dr = new DependencyResolver(jobList);
		Queue<Job> resultQ = dr.getExecutionQ();
		
		assertThat(resultQ.equals(expectedResult)).isTrue();
		
	}
	
	@Test
	public void getExecutionQTestWithMultipleDependenciesReturnsQInCorrectDependencyOrder() {
		
		Job job1 = new Job("Job1");
		Job job2 = new Job("Job2");
		Job job3 = new Job("Job2");
		
		job1.addDependency(job2, job3);
		job2.addDependency(job3);
		
		Queue<Job> expectedResult = new LinkedList<>();
		expectedResult.add(job3);
		expectedResult.add(job2);
		expectedResult.add(job1);
		
		List<Job> jobList = new ArrayList<>();
		jobList.add(job1);
		jobList.add(job2);
		jobList.add(job3);
		
		DependencyResolver dr = new DependencyResolver(jobList);
		Queue<Job> resultQ = dr.getExecutionQ();
		
		assertThat(resultQ.equals(expectedResult)).isTrue();
		
	}
	
}
