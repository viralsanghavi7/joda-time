#!/bin/sh
var=$(node main.js)
#scp -i /home/viral/Downloads/milestone3.pem ./joda-time.zip ubuntu@$var:~
scp -i /home/viral/Downloads/milestone3.pem ./redis-stable.zip ubuntu@$var:~
ssh -i /home/viral/Downloads/milestone3.pem ubuntu@$var
mvn -v

