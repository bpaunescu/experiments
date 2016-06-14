package bpaunescu.simplewebserver.core;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.log4j.Logger;

/**
 * 
 *	Server thread. Opens a socket and listens for incoming connections
 */
public class HttpServer implements Runnable {

	private static Logger LOGGER = Logger.getLogger(HttpServer.class.getName());
	
	/* number of connections allowed at a time */
	private int threadCount;
	
	/* port used to listen for incoming connections */
	private int port;

	/* object that accepts and closes connections */
	private ConnectionHandler connectionHandler;
	
	/* object that handles resource requests */
	private ResourceManager resourceManager;
	
	/* socket for incoming connections */
	private ServerSocket socketListener;

	public HttpServer(int threadCount, int port, String resourcesPath, String homePage) {
		this.threadCount = threadCount;
		this.port = port;
		resourceManager = new ResourceManager(resourcesPath, homePage);
	}

	@Override
	public void run() {
		LOGGER.info("Server has started. Listening on port " + port);
		
		try {
			socketListener = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		connectionHandler = new ConnectionHandler(this, socketListener);

		while (!Thread.interrupted()) {
			connectionHandler.acceptConnection();
		}
		
		LOGGER.info("Server is shutting down.");
		connectionHandler.cleanUp();
	}
	
	public ResourceManager getResourceManager() {
		return resourceManager;
	}
	
	public int getMaxNumberOfConnections() {
		return threadCount;
	}
}
