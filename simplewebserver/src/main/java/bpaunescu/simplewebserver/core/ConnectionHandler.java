package bpaunescu.simplewebserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import bpaunescu.simplewebserver.utils.Constants;

/**
 * Class that handles incoming connections.
 * A thread pool is used to assign a thread to handle each connection
 * 
 */
public class ConnectionHandler {

	private static Logger LOGGER = Logger.getLogger(ConnectionHandler.class.getName());
	
	private HttpServer server;
	private ServerSocket socketListener;
	private ThreadPoolExecutor threadPool;

	public ConnectionHandler(HttpServer server, ServerSocket socket) {
		socketListener = socket;
		this.server = server;

		final ThreadFactory threadFactory = Executors.defaultThreadFactory();
		final BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(Constants.MIN_THREAD_COUNT);
		threadPool = new ThreadPoolExecutor(Constants.MIN_THREAD_COUNT, server.getMaxNumberOfConnections(), 10, TimeUnit.SECONDS, taskQueue,
				threadFactory);
	}
	
	/**
	 *
	 */
	public void acceptConnection() {
		try {
			final Connection incoming = new Connection(socketListener.accept(), server);
			threadPool.execute(new Thread(incoming));
		} catch (IOException e) {
			LOGGER.debug(e.getMessage());
			LOGGER.error("Incoming connection could not be established.");
		}
	}

	/**
	 * Close socket that accepts connections and shuts down the thread pool
	 */
	public void cleanUp() {
		try {
			socketListener.close();
		} catch (IOException e) {
			LOGGER.debug(e.getMessage());
			LOGGER.error("Error during socket closing.");
		}
		
		threadPool.shutdown();
		try {
			if (!threadPool.awaitTermination(10, TimeUnit.SECONDS))
				threadPool.shutdownNow();
		} catch (InterruptedException e) {
			LOGGER.debug(e.getMessage());
			LOGGER.error("Error during thread pool shutdown");
		}
	}

}
