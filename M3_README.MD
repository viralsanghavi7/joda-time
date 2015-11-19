## Milestone Deploy

#### Team Members:

1. Chetan Pawar (chpawar@ncsu.edu)
2. Viralkumar Sanghvi (vjsangha@ncsu.edu)
3. Payal Soman (prsoman@ncsu.edu)

### Capabilities:

Our target project is [joda-time](https://github.com/JodaOrg/joda-time)

### Demo video: [video](https://youtu.be/XnEpyCdvgbA)

#### Automatic configuration of production environment

The ansible playbook task1.yml creates an ec2 instance and all required dependencies.

The ansible playbook is run from the post-commit hook.

Contents of post-commit hook for the first and second capabilities:

```
s="SUCCESS"
if [ $lastBuildStatus = $s ]
then
	echo "Commit pushed successfully with build also successful"
	cd $GIT_DIR/../tempFiles/
	#ansible-playbook -i ec2.py task1.yml --private-key ~/Downloads/devops3.pem
	ansible-playbook -i ec2.py task5.yml --private-key ~/Downloads/devops3.pem	
	./M3.sh
	ansible -i ec2.py --private-key ~/Downloads/devops3.pem -u ubuntu us-east-1 -a /home/ubuntu/aws_tasks.sh -m shell
else
	git reset --soft HEAD~1
	echo "Build was not successful. Commit was rolled back"
fi

```

In above code, once the build is successful, we are executing ansible playbook task1.yml which will create an instance in aws.

#### Triggered, remote deployment

The script M3.sh does the following tasks:

- Get the IP of recently created instance.
- Zip the current joda-time folder and put it in ~/DevOps/viral/joda-time.tgz
- copy joda-time.tgz to aws instance
- copy redis-stable.tgz to aws instace for running redis server on aws instance
- Copy aws_tasks.sh to aws instance

The script aws_tasks.sh does the following tasks:

- The script M3.sh does the follwing tasks:
- Untar redis-stable.tgz to redis-stable folder
- Untar redis-stable.tgz to redis-stable folder

Both the scripts M3.sh and aws_tasks.sh are run from the post-commit hook whose contents are shown in the first section.

#### Feature Flags

We created a new feauture FeatureFlag.java which prints the date.

The file ConnectToRedis.java does the connection to redis.

If the feauture flag is true, the date is printed otherwise null is printed. 

#### Metrics and alerts

AWS provides CloudWatch Alarms which enables to monitor the instances based on certain parameters like CPU Utilization, Disk Read/Write Operations, System Check Failed, etc.
 
For our capability, we chose the metrics CPU Utilization and Disk Write Operations.

The alarm was set for CPU utilization with some threshold. If the threshold is surpassed, an alert in the form of an email is sent.

Similarly for Disk Write Operation, the alarm was set with some threshold.

#### Canary releasing

We created a load balancer using the AWS Elastic Load Balancer. The ec2 instances were then added to this load balancer.

The load balancer routes the traffic to the two instances.

If one of the servers shuts down, the load balancer routes the traffic to only the live one. For put an alert in load balancer that if the number of healthy instances in load balancer is less than 2 then give email notification. So if canary instance with new feature, if it goes down, all traffic will be routed to the production instance only.
