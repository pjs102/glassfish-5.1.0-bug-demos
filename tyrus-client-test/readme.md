## Tyrus Websocket Client Example
This is an example usage of the websocket client.  The test makes a call to the wss://echo.websocket.org service. And sends the message “Hello World”. 

When deployed in a glassfish 5.1.0-RC1 the call to ClientManager.createClient(); throws an DeploymentException stating that the GrizzlyClientContainer is not found. 

To repeat the test build the project using maven and deploy the jar file into the
glassfish/domains/domain1/autodeploy/bundles folder. The output will appear in the server.log file.

