package bpaunescu.simplewebserver.utils;

public final class Constants {
	
	/* Size of the thread queue*/
	public static final int MIN_THREAD_COUNT = 8;
	
	/* Error messages */
	public static final String ERR_MSG_INVALID_ARGS = "Application must be started with either 4 arguments.\n"
			+ "First argument is the number of threads to be used to handle HTTP requests and responses.\n"
			+ "Second argument is the absolute path to the web application's root directory.\n" 
			+ "Third argument is the home page of the website.\n" 
			+ "Fourth arguments is the port on which the server will listen.";
	
	public static final String ERR_MSG_INVALID_THREAD_NO = "Number of threads must be a positive integer.";
	
	public static final String ERR_MSG_INVALID_PORT_NO = "Port number must be a positive integer.";
	
	/* HTTP header values */
	public static final String CONNECTION_CLOSE = "close";
	
	public static final String SERVER_INFO = "BPaunescu's lame Java webserver";
	
	public static final Protocol PROTOCOL = Protocol.HTTP_1_0;
	
	private Constants(){}
	
}
