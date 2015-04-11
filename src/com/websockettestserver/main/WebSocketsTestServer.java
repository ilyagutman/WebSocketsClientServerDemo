package com.websockettestserver.main;
import java.io.IOException;
 

import java.util.Random;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
 
/** 
 * @ServerEndpoint gives the relative name for the end point
 * This will be accessed via ws://localhost:8080/WebSocketsTestServer/echo
 * Where "localhost" is the address of the host,
 * "WebSocketsTestServer" is the name of the package
 * and "echo" is the address to access this class from the server
 */
@ServerEndpoint("/echo") 
public class WebSocketsTestServer {
	
	final int NUMBER_OF_ITERATIONS = 10000;
	final int BOTTOM_RANDOM_NUMBERS = 1;
	final int TOP_RANDOM_NUMBERS = 300;
	final int PAUSE_INTERVAL = 10;
		
    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session session){
        System.out.println(session.getId() + " has opened a connection"); 
        try {
            session.getBasicRemote().sendText("Connection Established");
            
            startSimulation(session);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Message from " + session.getId() + ": " + message);
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        System.out.println("Session " +session.getId()+" has ended");
    }
    
    private void startSimulation(Session session) {
    	
    	for (int ndx = 0; ndx < NUMBER_OF_ITERATIONS; ++ndx) {
    		
    		double value = generateRandomValue(BOTTOM_RANDOM_NUMBERS, TOP_RANDOM_NUMBERS);
    		
    		try {
				session.getBasicRemote().sendText(Double.toString(value));
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    		pause();
    	}
    	
    }
    
    private double generateRandomValue(int min, int max) {
    	Random rand = new Random();
		return rand.nextInt((max-min) + 1) + min;
    }
    	
    private void pause() {
    	try {
			Thread.sleep(PAUSE_INTERVAL);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    }
}