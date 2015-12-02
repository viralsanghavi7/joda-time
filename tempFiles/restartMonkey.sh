#!/bin/sh

# this playbook will restart the instances.
ansible-playbook -i ec2.py restart.yml --private-key ~/Downloads/devops3.pem

#echo "************************** waiting for servers ssh port to open up **************************"
sleep 5m

./ec2.py --refresh-cache

echo "************************** running site:9000 again **************************"
# this command will start maven site on instances
ansible -i ec2.py --private-key ~/Downloads/devops3.pem -u ubuntu us-east-1 -a /home/ubuntu/startSite.sh -m shell

echo "************************** waiting for mvn site to get up **************************"
sleep 2m

echo "************************** curl to instance dns to see if the site is up **************************"
node ~/DevOps/getIP/curl1.js