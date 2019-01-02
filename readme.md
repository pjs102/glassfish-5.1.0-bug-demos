# Glassfish 5.1.0-RC1 OSGI service examples. 
The following modules are simple usage examples that demonstrate OSGI bugs in the Glassfish 5.1.0-RC1 release.  Fundamentally the problems have been caused by missing or incorrect metadata in the associated modules.  This in turn cause class loader isolation issues, usually between the API and the implementation of each service.  

## Deployment
Build each of the bundles using maven and deploy them into the glassfish/domains/domain1/autodeploy/bundles folder.
Each of the test modules logs to stdout which will appear in the server.log file. 
