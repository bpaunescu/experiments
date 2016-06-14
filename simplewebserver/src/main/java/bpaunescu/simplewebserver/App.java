package bpaunescu.simplewebserver;

import bpaunescu.simplewebserver.core.HttpServer;
import bpaunescu.simplewebserver.utils.Constants;

public class App {	
	/**
	 * 
	 * @param args
	 *            A string array that contains the number of threads, path to
	 *            the web resources, home page name and listening port
	 */
	public static void main(String[] args) {

		/* validate arguments */
		if (args.length != 4) {
			System.out.println(Constants.ERR_MSG_INVALID_ARGS);
			return;
		}

		int threadCount;

		try {
			threadCount = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			System.out.println(Constants.ERR_MSG_INVALID_THREAD_NO);
			return;
		}

		final String webAppRootPath = args[1];
		final String homePage = args[2];
		
		int port;
		
		try {
			port = Integer.parseInt(args[3]);
		} catch (NumberFormatException e) {
			System.out.println(Constants.ERR_MSG_INVALID_PORT_NO);
			return;
		}
		
		/* Start server thread */
		final Thread serverThread = new Thread(new HttpServer(threadCount, port, webAppRootPath, homePage));
		serverThread.start();
	}
}
