#!/bin/sh

# untar our joda-time library and redis-server code
tar -zxvf ~/joda-time.tgz
#tar -zxvf ~/redis-stable.tgz

# run redis server on aws instance. It will run on port 6379
#nohup ./redis-stable/src/redis-server </dev/null &>/dev/null &

# cd joda-time

# nohup mvn site:run </dev/null &>/dev/null &