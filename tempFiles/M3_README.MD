## Milestone Deploy

#### Team Members:

1. Chetan Pawar
2. Viralkumar Sanghvi
3. Payal Soman

### Capabilities:

Our target project is [joda-time] https://github.com/JodaOrg/joda-time

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

#### Metrics and alerts


#### Canary releasing
