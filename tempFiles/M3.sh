#!/bin/sh
var=$(node ~/DevOps/getIP/main.js)

# compressing the code to copy to the aws machine
tar -zcvf ../../joda-time.tgz ../../joda-time/

# copy joda-time library code
scp -i ~/Downloads/devops3.pem ~/DevOps/viral/joda-time.tgz ubuntu@$var:~

# copy redis-stable version code
#scp -i ~/Downloads/devops3.pem ~/DevOps/viral/joda-time/tempFiles/redis-stable.tgz ubuntu@$var:~

# copy aws_tasks.sh which contains commands to run the server on aws instance
scp -i ~/Downloads/devops3.pem ~/DevOps/viral/joda-time/tempFiles/aws_tasks.sh ubuntu@$var:~

scp -i ~/Downloads/devops3.pem ~/DevOps/viral/joda-time/tempFiles/startSite.sh ubuntu@$var:~

