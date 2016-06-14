package bpaunescu.simplewebserver.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 *	Enum describes the type of files served to the client
 */
public enum ContentType {
	
	TEXT("text/plain"), 
	HTML("text/html"), 
	JPEG("image/jpeg"), 
	PNG("image/png"), 
	JS("application/javascript"),
	CSS("text/css");

	private static final Map<String, ContentType> types;
    static {
        Map<String, ContentType> map = new HashMap<>();
        map.put("", TEXT);
        map.put("txt", TEXT);
        map.put("text",  TEXT);
        map.put("html", HTML);
        map.put("htm", HTML);
        map.put("jpg", JPEG);
        map.put("jpeg", JPEG);
        map.put("png", PNG);
        map.put("js", JS);
        map.put("css", CSS);
        types = Collections.unmodifiableMap(map);
    }
	
	private String value;

	ContentType(String value) {
		this.value = value;
	}

	/**
	 * 
	 * @return
	 * A the {@code String} value to be used in http headers(MIME type)
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Creates a new {@code ContentType} object from the given {@code String} parameter
	 * @param type
	 * @return
	 * A new {@code ContentType} object
	 */
	public static ContentType fromString(String type) {
		return types.get(type);
	}
}
