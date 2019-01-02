#Jersey (JAX-RS) Client Example
This is an example usage of the JAX-RS client.  The test makes a get request to https://jsonplaceholder.typicode.com/todos/1 to retrieve a basic JSON document.  The http server response code is then logged.

When deployed in a glassfish 5.1.0-RC1 the call to the webTarget.request().get() throws an IllegalStateException stating that the InjectionManagerFactory is not found. 

To repeat the test build the project using maven and deploy the jar file into the
glassfish/domains/domain1/autodeploy/bundles folder. The output will appear in the server.log file.
