package bpaunescu.simplewebserver.http;

/**
 * 
 *	Enum that describes HTTP headers used in processing requests and responses
 */
public enum HttpHeader {
	
	HOST("Host"),
	USER_AGENT("User-Agent"),
	ACCEPT("Accept"),
	ACCEPT_LANGUAGE("Accept-Language"),
	ACCEPT_ENCODING("Accept-Encoding"),
	ACCEPT_CHARSET("Accept-Charset"),
	KEEP_ALIVE("Keep-Alive"),
	CONNECTION ("Connection"),
	CONTENT_LENGTH("Content-Length"),
	CONTENT_TYPE("Content-Type"),
	CONTENT_ENCODING("Content-Encoding"),
	COOKIE("Cookie"),
	DATE("Date"),
	SERVER("Server");

	private String name;

	HttpHeader(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 * A {@code String} object containing the name of the header
	 */
	public String getName() {
		return name;
	}
}
