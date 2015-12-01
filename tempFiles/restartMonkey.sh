#!/bin/sh

# this playbook will restart the instances.
ansible-playbook -i ec2.py restart.yml --private-key ~/Downloads/devops3.pem

# this command will start maven site on instances
ansible -i ec2.py --private-key ~/Downloads/devops3.pem -u ubuntu us-east-1 -a /home/ubuntu/startSite.sh -m shell