package bpaunescu.simplewebserver.http;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import bpaunescu.simplewebserver.utils.Protocol;

/**
 * 
 * @author Bogdan
 *
 */
public class HttpRequest {

	private HttpMethod method;
	private String url;
	private Protocol protocol;
	private Map<String, String> headers;

	public HttpRequest() {
		headers = new LinkedHashMap<>();
	}

	/**
	 * Sets the request method
	 * @param method
	 */
	public void setMethod(HttpMethod method) {
		this.method = method;
	}
	
	/**
	 * 
	 * @return
	 * The http method of the request
	 */
	public HttpMethod getMethod() {
		return method;
	}

	/**
	 * Sets the request URL 
	 * @param url
	 */
	public void setURL(String url) {
		this.url = url;
	}
	
	/**
	 * 
	 * @return
	 * The URL of the requested resource
	 */
	public String getURL() {
		return url;
	}
	
	/**
	 * Sets the request protocol
	 * @param protocol
	 */
	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	/**
	 * 
	 * @return
	 * The protocol of the request
	 */
	public Protocol getProtocol() {
		return protocol;
	}

	/**
	 * Sets a header given as a (name, value) pair
	 * @param name
	 * @param value
	 */
	public void setHeader(String name, String value) {
		headers.put(name, value);
	}
	
	/**
	 * 
	 * @param header
	 * @return
	 * The value of the http header given as parameter
	 */
	public String getHeader(HttpHeader header) {
		return headers.get(header.getName());
	}

	/**
	 * 
	 * @return
	 * A {@code Map} object containing all the headers in the request
	 */
	public Map<String, String> getHeaders() {
		return Collections.unmodifiableMap(headers);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(method).append(" ").append(url).append(" ").append(protocol.toString()).append("\n");
		for (String headerName : headers.keySet()) {
			sb.append(headerName).append(": ").append(headers.get(headerName)).append("\n");
		}

		return sb.toString();
	}
}
