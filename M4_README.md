## Milestone - Special Component

Team Members:

1. Chetan Pawar (chpawar@ncsu.edu)
2. Viralkumar Sanghvi (vjsangha@ncsu.edu)
3. Payal Soman (prsoman@ncsu.edu)

#### Target Project 

We are using the same target project joda-time as used in the previous milestones.

### Restart Monkey

We are implementing a restart monkey as a special component. Instead of conventional restart monkey which restarts local web servers on the instance, our restart monkey restarts the whole AWS instance and checks if all the services are up and compliant to requirements. The main motivation behind this approach was to make every instance present in load balancer as restart safe. There can be unplanned events when instances are restarted and our service needs to be up and running once the instance is back to running state. 

So our restart monkey, after server is restarted, it waits and checks if the application deployed to server is up and running by doing a simple CURL request to the public DNS of the instance. If the curl was successful then only the instance is decided to be added to the load balancer. Otherwise the output is shown to user that something was wrong after the restart and then user can decide to work on it.

###### Link to [final report](https://github.com/viralsanghavi7/joda-time/blob/master/FinalReport.MD)

### Demo [video](https://youtu.be/m2X3ZlsHZhg)

