package bpaunescu.simplewebserver.core;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * Class that handles I/O operations for a HTTP connection
 */
public class Connection implements Runnable {

	private static Logger LOGGER = Logger.getLogger(Connection.class.getName());

	private InputStream inFromClient;
	private OutputStream outToClient;

	private Socket client;
	private HttpServer server;

	/**
	 * Creates a new {@code Connection} object
	 * @param client
	 * - the socket on which the connection was established
	 * @param server
	 * - the server accepting the connection
	 */
	public Connection(Socket client, HttpServer server) {
		this.client = client;
		this.server = server;
	}

	@Override
	public void run() {
		try {
			inFromClient = client.getInputStream();
			outToClient = client.getOutputStream();

			// process request
			final RequestHandler requestHandler = new RequestHandler(server.getResourceManager());
			requestHandler.readRequest(inFromClient);

			// send response
			final DataOutputStream output = new DataOutputStream(outToClient);
			output.writeBytes(requestHandler.getResponse().getMessage());
			output.write(requestHandler.getResponse().getBody());
			output.flush();
			
			inFromClient.close();
			outToClient.close();

		} catch (IOException e) {
			LOGGER.debug(e.getMessage());
			LOGGER.error("Error during client I/O.");
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				LOGGER.debug(e.getMessage());
				LOGGER.error("Error closing client socket.");
			}
		}

	}

}
