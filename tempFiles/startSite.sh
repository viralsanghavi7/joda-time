#!/bin/sh
echo "***********************Running startSite.sh***********************"
cd joda-time

nohup mvn site:run </dev/null &>/dev/null &