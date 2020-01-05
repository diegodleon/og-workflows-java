**How to Run

Clone repository locally then edit OpengovWorkflowStandAloneApplication.java and...

There are two paths to execute this:

-> Spring Tool Suite
   - Import project into STS.
   - Run as SpringBoot application.
   
-> Maven and a Terminal
   - cd to your local path
   - Make sure MAVEN is installed in your system.
   - mvn spring-boot:run to run the application
   - mvn test to run Unit Tests


**Workflow Simulator

This application intends to emulate a Job running framework.

The framework provides the following entities:

* Job: which represents a Job.
* WorkFlow: which represents a logical grouping of Jobs together.
* DependencyResolver: which is in charge of resolving dependencies for correct job execution order.

A Job then will output its name when executed. So will a Work Flow. 

Multiple jobs can be registered to a work flow. And, multiple jobs can be registered as dependencies to a job. Meaning the 
registered upon job will not run until all of its dependent jobs have run.

Framework also contains two services:

* Job Service: Creates jobs.
* Workflow Service: Creates workflows.

**Assumptions and Considerations

- Framework is single threaded at this point. 
- If we wished to make this multi threaded we could look into implementing Runnable and using a thread pool.
- NO CIRCULAR DEPENDENCIES IN JOBS ARE CONSIDERED (dead locks)
- The dependency resolving algorithm is a custom implementation of my own, it was just something to get going. If we 
wanted to improve depedency solving elapsed time and circular dependency detection, we may wanna consider a Topological Sorting 
algorithm, there are plenty online available.

This project is using Springboot in Stand Alone mode. 

-> If we wanted to build this into a Service, I would suggest we:
   - Separate away the job/workflow framework into a separate standalone library.
   - Change into SpringBoot web app mode instead of stand alone app and create CRUD APIs for JOBs and WORKFLOWs. We could define
     easy API contracts using REST for both.
   - Create APIs for Execution management for Jobs/Workflows. e.g. Execute Workflow, Execute Job etc.
   - Dockerize everything.
   - Move into a multi-threaded model.

-> Current Scaling issues:
   - Framework is single thread... yiuk! Move into multi-threaded to leverage multiple hardware threads.
   - Dockerize in order to be able to leverage Auto Scaling Groups and Swarm/K8s. As well as ease deployment.
   - Jobs without dependency could run in parallel in order to speed execution.
   - Performance/Load/System testing would be nice in order to tune and capacity plan.

-> Potential applications of this workflow manager:
   - This can become a Task Automation framework for Foreground/Background jobs.
   - It can allow asynchrounous decoupling by using a Queue like SQS or RabbitMQ.
 

