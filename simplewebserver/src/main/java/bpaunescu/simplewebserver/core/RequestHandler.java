package bpaunescu.simplewebserver.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.log4j.Logger;

import bpaunescu.simplewebserver.http.ContentType;
import bpaunescu.simplewebserver.http.HttpMethod;
import bpaunescu.simplewebserver.http.HttpRequest;
import bpaunescu.simplewebserver.http.HttpResponse;
import bpaunescu.simplewebserver.http.HttpStatusCode;
import bpaunescu.simplewebserver.utils.Constants;
import bpaunescu.simplewebserver.utils.Protocol;

/**
 *	This class handles incoming requests and creates suitable responses 
 *
 */
public class RequestHandler {

	private static Logger LOGGER = Logger.getLogger(RequestHandler.class.getName());

	private HttpRequest request;
	private ResourceManager resourceManager;
	
	private boolean requestIsOk;

	public RequestHandler(ResourceManager resourceManager) {
		request = new HttpRequest();
		requestIsOk = false;
		this.resourceManager = resourceManager;
	}

	public void readRequest(InputStream in) {
		final BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		try {
			String line = br.readLine();
			if (line != null) {
				String[] tokenizedLine = line.split(" ");
				if (tokenizedLine.length == 3) {

					request.setMethod(HttpMethod.fromString(tokenizedLine[0]));
					request.setURL(tokenizedLine[1]);
					request.setProtocol(Protocol.fromString(tokenizedLine[2]));

					// parse the headers
					line = br.readLine();
					// read until message body(don't need that for the purpose
					// of this task)
					while (line != null && !line.isEmpty()) {
						tokenizedLine = line.split(": ");
						if (tokenizedLine.length == 2) {
							request.setHeader(tokenizedLine[0], tokenizedLine[1]);
						}
						line = br.readLine();
					}
				}
				requestIsOk = true;
			}
		} catch (IOException e) {
			LOGGER.debug(e.getMessage());
			LOGGER.error("Error reading request.");
		}
	}
	
	/**
	 * 
	 * @return
	 * a HTTP response for a given request
	 */
	public HttpResponse getResponse() {
		HttpResponse response = null;

		if (!requestIsOk) {
			LOGGER.info("Bad request received.");
			response = errorResponse(HttpStatusCode.BAD_REQUEST);
		} else {
			LOGGER.info("Processing request:\n" + request.toString());
			// check method type
			if (request.getMethod() != HttpMethod.GET) {
				response = errorResponse(HttpStatusCode.NOT_IMPLEMENTED);
			} else {
				response = respondWithResource(request.getURL());
			}
		}

		return response;
	}

	/**
	 * Creates a http response for the requested resource
	 * @param url
	 * @return
	 * a {@code HttpResponse} object
	 */
	private HttpResponse respondWithResource(String url) {
		if (!resourceManager.isAllowed(url)) {
			return errorResponse(HttpStatusCode.FORBIDDEN);
		}

		if (!resourceManager.resourceExists(url)) {
			return errorResponse(HttpStatusCode.NOT_FOUND);
		}

		HttpResponse response = genericResponse(HttpStatusCode.OK);
		// read resource
		try {
			byte[] content = resourceManager.readResource(url);
			response.setContentLength(content.length);
			response.setContentType(ContentType.fromString(resourceManager.getExtension(url)));
//			response.setContentEncoding(request.getHeader(HttpHeader.ACCEPT_ENCODING));
			response.setBody(content);
		} catch (IOException e) {
			response = errorResponse(HttpStatusCode.NOT_FOUND);
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * Creates a generic response based on the status code. 
	 * The response doesn't have a content body.
	 * @param statusCode
	 * @return
	 * a simple {@code HttpResponse} object containing only protocol, date, server information and connection information
	 */
	private HttpResponse genericResponse(HttpStatusCode statusCode) {
		final HttpResponse response = new HttpResponse(statusCode);
		response.setProtocol(Constants.PROTOCOL);
		response.setDate(new Date());
		response.setServer(Constants.SERVER_INFO);
		response.setConnection(Constants.CONNECTION_CLOSE);
		return response;
	}

	/**
	 * Creates a response with and error message(html) in the body
	 * @param statusCode
	 * @return
	 * a error response based on the given status code
	 */
	private HttpResponse errorResponse(HttpStatusCode statusCode) {
		final HttpResponse response = genericResponse(statusCode);
		/* Create a simple html page that clearly displays the error message */
		final String htmlBody = "<html><body>" + statusCode.getMessage() + "</body></html>";
		response.setContentLength(htmlBody.getBytes().length);
		response.setContentType(ContentType.HTML);
		response.setBody(htmlBody.getBytes());
		return response;
	}

}
