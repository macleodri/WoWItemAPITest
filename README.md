# WoWItemAPITest
A series of example Junit tests for the WoW item API. As such it has a few library 
dependencies in order to parse the JSON responses and run the tests. In order to make it as easy as
possible to read, build, and run these tests they are kept in the same directory. This also does not print
test results to an external file as that would require the use of extra libraries (such as ant) and fall outside the scope of
this assignment. Upon successfull completion of these tests JUnit will give a simple command line response as to if anything
went wrong during the test execution.

# Dependencies
1. This uses the [JUnit](http://junit.org/) library in order to run the unit tests
2. The [org.json](http://mvnrepository.com/artifact/org.json/json) library is used in order to parse the responses from the API
3. This project was written with Java 8

# Running the tests
To run the test cases, after you have built the files, you can run either WoWAPITestRunner (which is to show how this set up could run multiple test classes), or just call WoWAPITestClass directly. Neither class needs any arguements in order to run.
