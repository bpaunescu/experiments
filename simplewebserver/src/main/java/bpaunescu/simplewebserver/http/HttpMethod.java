package bpaunescu.simplewebserver.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 *	Enum that describes HTTP methods
 */
public enum HttpMethod {
	
	GET, PUT, POST, DELETE;
	
	private static final Map<String, HttpMethod> methods;
    static {
        Map<String, HttpMethod> map = new HashMap<>();
        map.put("GET", GET);
        map.put("PUT", PUT);
        map.put("POST", POST);
        map.put("DELETE", DELETE);
        methods = Collections.unmodifiableMap(map);
    }
	
    /**
     * Creates a new {@code HttpMethod} from the input {@code String}
     * @param method
     * @return
     * A new {@code HttpMethod} object
     */
	public static HttpMethod fromString(String method) {
		return methods.get(method.toUpperCase());
	}
}
