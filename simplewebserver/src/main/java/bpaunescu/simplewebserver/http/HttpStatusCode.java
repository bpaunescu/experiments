package bpaunescu.simplewebserver.http;

/**
 * 
 * @author Bogdan
 * Describes the status codes implemented by the server
 */
public enum HttpStatusCode {
	
	OK("200 OK"), 
	NOT_FOUND("404 Not Found"), 
	FORBIDDEN("403 Forbidden"),
	BAD_REQUEST("400 Bad Request"),
	NOT_IMPLEMENTED("501 Not Implemented");
	
	private String message;
	
	HttpStatusCode(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
	@Override
	public String toString() {
		return getMessage();
	}

}
