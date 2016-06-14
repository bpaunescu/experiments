package bpaunescu.simplewebserver.http;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import bpaunescu.simplewebserver.utils.Protocol;


/**
 * 
 *	A class that describes a http response message
 */
public class HttpResponse {

	private Protocol protocol;
	private HttpStatusCode statusCode;
	private Map<String, String> headers;
	
	private byte[] body;
	
	/**
	 * Constructs a new {@code HttpResponse} with a given status code
	 * @param statusCode
	 */
	public HttpResponse(HttpStatusCode statusCode) {
		this.statusCode = statusCode;
		headers = new LinkedHashMap<>();
	}
	
	/**
	 * Set the protocol of the response
	 * @param protocol
	 */
	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}
	
	/**
	 * Set the date of the response
	 * @param date
	 */
	public void setDate(Date date) {
		headers.put(HttpHeader.DATE.getName(), date.toString());
	}
	
	/**
	 * Set the server information header value
	 * @param serverInfo
	 */
	public void setServer(String serverInfo) {
		headers.put(HttpHeader.SERVER.getName(), serverInfo);
	}
	
	/**
	 * Set the connection information header value
	 * @param connectionInfo
	 */
	public void setConnection(String connectionInfo) {
		headers.put(HttpHeader.CONNECTION.getName(), connectionInfo);
	}
	
	/**
	 * Set the content length header value
	 * @param length
	 */
	public void setContentLength(long length) {
		headers.put(HttpHeader.CONTENT_LENGTH.getName(), Long.toString(length));
	}
	
	/**
	 * Set the MIME type of the response
	 * @param type
	 */
	public void setContentType(ContentType type) {
		headers.put(HttpHeader.CONTENT_TYPE.getName(), type.getValue());
	}
	
	/**
	 * Set the content encoding header value
	 * @param encoding
	 */
	public void setContentEncoding(String encoding) {
		headers.put(HttpHeader.CONTENT_ENCODING.getName(), encoding);
	}
	
	/**
	 * Set the response message body
	 * @param content
	 */
	public void setBody(byte[] content) {
		body = content;
	}
	
	/**
	 * 
	 * @return
	 * An array of bytes representing the response message body
	 */
	public byte[] getBody() {
		return body;
	}
	
	/**
	 * 
	 * @return
	 * First part of the response(without the body)
	 */
	public String getMessage() {
		final StringBuilder sb = new StringBuilder();
		sb.append(protocol).append(" ").append(statusCode).append("\n");
		
		//append headers
		for (String headerName : headers.keySet()) {
			sb.append(headerName).append(": ").append(headers.get(headerName)).append("\n");
		}
		
		sb.append("\r\n");
		
		return sb.toString();
	}
}
