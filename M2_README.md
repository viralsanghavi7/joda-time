# MILESTONE: TEST + ANALYSIS
#### Project Team Members:
1. Viralkumar Sanghvi (vjsangha)
2. Chetan Pawar (chpawar)
3. Payal Soman (prsoman)

### Target project : [Joda-time](https://github.com/JodaOrg/joda-time)

Our target project is Joda-time which provides a replacement for Java date and time classes. Joda-time provides additional features like Local Date, Local Time, Interval, Duration and Period, etc. 

#### Initial Setup:
* Install Jenkins and Maven.
* Run jenkins and go to http://localhost:8080 to see the Jenkins GUI.
* Make a new Maven project and add the path to pom.xml file of target project.
* Build the project.

##### Link for demo video: [demo](www.youtube.com)
## Test Section:

#### Task 1: The ability to run unit tests, measure coverage, and report the results.

* The unit tests were run using SureFire which is inbuilt available in our target project.
* To measure coverage, we used Jacoco tool. Jacoco has a plugin for Jenkins. We installed this plugin and added the following lines in the pom.xml file of our Maven project:
```
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.7.4.201502262128</version>
    <executions>
        <execution>
            <id>default-prepare-agent</id>
            <phase>process-resources</phase>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>default-report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
              
    </executions>
</plugin>
```

#### Task 2: The ability to improve testing coverage using one of the techniques covered in class: constraint-based test generation, fuzzing, etc.

* We are implementing constrainst-based test generation for this ability. We used EvoSuite, which allows automatic test generation for java.
* We are manually generating EvoSuite test cases but running it using evosuite run time which we have added in pom.xml file. EvoSuite generates additional test cases and thus improves the coverage.
* Added the follwing lines in pom.xml file:
```
<dependencies>
    <dependency>
        <groupId>org.evosuite</groupId>
        <artifactId>evosuite-standalone-runtime</artifactId>
        <version>${evosuiteVersion}</version>
        <scope>test</scope>
    </dependency>
</dependencies>

<repositories>
    <repository>
        <id>EvoSuite</id>
        <name>EvoSuite Repository</name>
        <url>http://www.evosuite.org/m2</url>
    </repository>
</repositories>
```
* After running EvoSuite, the coverage for this file ```DelegatedDurationField.java``` increased to 97% percent from 0%.

## Analysis Section:

#### Task 3: The ability to run an existing static analysis tool on the source code process its results, and report its findings.

* For this capability, we are using the static analysis tool called FindBugs. We have a plugin in Jenkins for FindBugs. 
* We installed this plugin and added the follwing lines in the pom.xml file of our Maven project.
```
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>findbugs-maven-plugin</artifactId>
    <version>3.0.1</version>
    <configuration>
        <effort>Max</effort>
        <threshold>Low</threshold>
        <xmlOutput>true</xmlOutput>
        <failOnError>false</failOnError>
    </configuration>
    <executions>
        <execution>
            <id>analyze-compile</id>
            <phase>compile</phase>
            <goals>
                <goal>check</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

#### Task 4: The ability to extend an existing analysis tool with a custom analysis, or implement a new analysis from scratch. 
* We wrote a java program to find the ratio of the number of comments to the number of lines of code.
* This program is executed in pre-commit script and it writes output as filename followed by percentComments to a file tempFiles/commentFiles.txt as follows

```
.git/../src/main/java/org/joda/time/DateTime.java 68%
.git/../src/main/java/org/joda/time/Days.java 61%
.git/../src/main/java/org/joda/time/Hours.java 60%
...

```

#### Task 5: Using hooks or post-build scripts, have the ability to reject a commit if it fails a minimum testing criteria and analysis criteria.

* Since we are using maven-surefire-plugin, it makes sure that build is failed if any of the test cases failed.
![surefire](http://s15.postimg.org/699iq9s4b/cutout.png)
* For our custom rule we are not failing a build since joda-time code base contains lost of files having more than 50% comment raio. Thus we are just writing such file names with their comment ration in tempFiles/commentFiles.txt as specified in Task 4.
 

#### Task 6: 
#### The ability to parse code files and json files in order to detect the presence of AWS/digital ocean security tokens and The ability to check commited files that are private ssh keys. 

* We wrote a script to iteratively check all files to find the ones with extensions like .pem, .key, etc. and a python script to check the presence of AWS/digital ocean security tokens in the code using Regular Expressions.
* It returns true if it finds such vulnerable information and false otherwise.


#### Using hooks, reject the commit if any violation occurs.
* This scripts are called in pre-commit. So if any of such files are found then we are failing a build in pre-commit.