# WebSocketsTestServer Simulation Build and Run Instructions

1. Install an Application Server, like Tomcat or GlassFish. I installed GlassFish 4 (https://glassfish.java.net/download.html).

2. Using Eclipse Marketplace install GlassFish Tools for [Luna] your version of Eclipse. After that, you will be able to create Java Web projects, as well as start Application Server from inside the Eclipse.

3. Create a new web project File -> New -> Other -> Web -> Dynamic Web Project. Give it a name and specify GlassFish (Tomcat) as a Target Runtime.

4. Open tab called Server. Find GlassFish (Tomcat)  server in the list. Right-button click and Start the server.

5. Build WebSocketsTestServer code. Right-button click on the running GlassFish (Tomcat)  server, in the Server tab, and publish (deploy) your code at the application web server.

6. Open browser that supports websockets protocol. Test your running web server by typing localhost:8080

7. Open HTML client  WebSocketsTestClient file in the browser.

8. Click open button to connect to the server and kick off the simulation.
