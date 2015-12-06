## Final Project Report

#### Team Members:

1. Chetan Pawar
2. Virakkumar Sanghvi
3. Payal Soman

### Target project : [Joda-time](https://github.com/JodaOrg/joda-time)

Our target project is Joda-time which provides a replacement for Java date and time classes. Joda-time provides additional features like Local Date, Local Time, Interval, Duration and Period, etc.

### Milestone 1 - BUILD

- For the build milestone, we set up a build server and used Jenkins as the CI tool for configuration of our build environment.
- We triggered build in response to git commits via the post-commit git hook.
- We used the build manager Maven which takes care of dependency management and runs clean build every time.
- On an unsuccessful and unstable build, we have set up an email trigger using the Email notification option in Jenkins.
- On a successful build, we executed a script that writes the Build Number, Build Time and the Build Url into a temporary build log file. We used the Post-Build Script Plugin in Jenkins for this.
- In the post-commit hook, we wrote a script to get the name of the branch committed and then trigger the build for that particular branch.
- The build history is displayed on the Jenkins UI.

Details for Milestone 1 can be found here : https://github.com/viralsanghavi7/DevOpsProject.git

### Milestone 2 - TEST / ANALYSIS

- The unit tests were run using SureFire which is available inbuilt in our target project.
- JaCoCo plugin for measuring and reporting the test coverage results
- To improve testing coverage,  we used EvoSuite, which allows automatic test generation for java.
- FindBugs plugin for static analysis of the project source and reporting warnings.
- wrote a java program to find the ratio of the number of comments to the number of lines of code. This program is executed in pre-commit script and it writes output as filename followed by percentComments to a file.
- For the the ability to reject a commit if it fails a minimum testing criteria and analysis criteria we used maven-surefire-plugin. It makes sure that build is failed if any of the test cases failed.  
- We wrote a script which is called in the pre-commit hook to detect for the presence of any security tokens / config keys.

Details for Milestone 2 can be found here : https://github.com/viralsanghavi7/joda-time/blob/master/M2_README.md

### Milestone 3 - DEPLOY

- The ansible playbook task1.yml creates an ec2 instance and all required dependencies. The ansible playbook is run from the post-commit hook.
- Remote deployment is done using the post-commit scripts. The script deploys the rpoject to the newly created instance and also enables to redis server on the aws instance.
- We created a new feauture FeatureFlag.java which prints the date. If the feature flag is true, the date is printed otherwise null is printed.
- We used the AWS CloudWatch Alarms to monitor the instances based on parameters like CPU Utilization and Disk Write Operation and setup alarms if the set threshold is surpassed.
- We created a load balancer using the AWS Elastic Load Balancer. The ec2 instances were then added to this load balancer. If canary instance with new feature goes down, all traffic will be routed to the production instance only.

Details for Milestone 3 can be found here : https://github.com/viralsanghavi7/joda-time/blob/master/M3_README.MD

### Milestone 4 - SPECIAL

- We implemented a restart monkey.
- Instead of conventional restart monkey which restarts local web servers on the instance, our restart monkey restarts the whole AWS instance and checks if all the services are up and compliant to requirements. 
- after server is restarted, it waits and checks if the application deployed to server is up and running by doing a simple CURL request to the public DNS of the instance. 
- If the curl was successful then only the instance is decided to be added to the load balancer. Otherwise the output is shown to user that something was wrong after the restart and then user can decide to work on it.

Details for Milestone 4 can be found here : https://github.com/viralsanghavi7/joda-time/blob/master/M4_README.md

### Final Video:

Link to the video for all the above milestones : https://www.youtube.com/watch?v=m2X3ZlsHZhg&feature=youtu.be
