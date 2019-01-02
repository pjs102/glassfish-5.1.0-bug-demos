# JSONP Example
This module is an example JSONP service which is based on https://www.journaldev.com/2315/java-json-example.  The test is triggered when the bundle is started and it will print parsed information into the server log file. 

When deployed in a glassfish 5.1.0-RC1 the call to Json.createReader() throws an ClassNotFoundException. 

To repeat the test build the project using maven and deploy the jar file into the
glassfish/domains/domain1/autodeploy/bundles folder. The output will appear in the server.log file.