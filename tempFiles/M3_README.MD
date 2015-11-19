## Milestone Deploy

#### Team Members:

1. Chetan Pawar
2. Viralkumar Sanghvi
3. Payal Soman

### Capabilities:

Our target project is [joda-time] https://github.com/JodaOrg/joda-time

#### Automatic configuration of production environment

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

#### Feature Flags

#### Metrics and alerts


#### Canary releasing
