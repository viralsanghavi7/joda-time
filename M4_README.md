## Milestone - Special Component

Team Members:

1. Chetan Pawar (chpawar)
2. Viralkumar Sanghvi (vjsangha)
3. Payal Soman (prsoman)

#### Target Project 

We are using the same target project joda-time as used in the previous milestones.

### Restart Monkey

We are implementing a restart monkey as a special component.

This restart monkey restarts the servers present in the load balancer.

It then waits and then checks if the servers are up and running properly.

If not working properly, it sends an email to the user saying that the server did not restart as desired.

It then again attempts to restart it.

### Flamegraph

We generated a CPU Usage flamegraph for our application.

Flame graphs are a visualization of profiled software, allowing the most frequent code-paths to be identified quickly and accurately.

We used the flamegraphs repo of [Brendan Gregg](http://www.brendangregg.com/FlameGraphs/cpuflamegraphs.html)

Install maven and Java 8.

Run the following command on the joda-time.jar file:

```
java -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:FlightRecorderOptions=defaultrecording=true,settings=profile,disk=true,repository=./tmp,dumponexit=true,dumponexitpath=./ -jar ../classes/artifacts/joda-time.jar --exit-timeout 60
```

Clone the repo (https://github.com/chrishantha/jfr-flame-graph)

Run the script run.sh on the .jfr file to produce output.txt

Use the flamegraph.pl script on the output.txt to produce an svg file.

This svg file is the flamegraph which can be viewed in the browser.

```
jfr-flame-graph$ ./run.sh -i -f ../sample-java-programs/hotspot-pid-30716-id-0-2015_09_14_19_01_38.jfr -o /tmp/output.txt
$ cat /tmp/output.txt | ~/performance/brendangregg-git/FlameGraph/flamegraph.pl --width 1680 > /tmp/highcpu-jfr.svg
```

#### Generated flamegraph:

