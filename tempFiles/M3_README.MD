## Milestone Deploy

#### Team Members:

1. Chetan Pawar
2. Viralkumar Sanghvi
3. Payal Soman

### Capabilities:

Our target project is [joda-time] (https://github.com/JodaOrg/joda-time)

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
	ansible-playbook -i ec2.py task1.yml --private-key ~/Downloads/devops3.pem
	./M3.sh
	ansible -i ec2.py --private-key ~/Downloads/devops3.pem -u ubuntu us-east-1 -m shell ./aws_tasks.sh
else
	git reset --soft HEAD~1
	echo "Build was not successful. Commit was rolled back"
fi

```
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

If the feauture flag is true, the date is printed otherwise not. 

#### Metrics and alerts

AWS provides CloudWatch Alarms which enables to monitor the instances based on certain parameters like CPU Utilization, Disk Read/Write Operations, System Check Failed, etc.
 
For our capability, we chose the metrics CPU Utilization and Disk Write Operations.

The alarm was set for CPU utilization with a threshold of    . If the threshold is surpassed, an alert in the form of an email is sent.

Similarly for Disk Write Operation, the alarm was set with a threshold of    .

#### Canary releasing

We created a load balancer from the AWS Management Console. The ec2 instances were then added to this load balancer.

The load balancer balances the load between the two servers.

If one of the servers shuts down, the load balancer routes the traffic to only the live one.
