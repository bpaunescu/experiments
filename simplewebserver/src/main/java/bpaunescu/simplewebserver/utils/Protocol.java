package bpaunescu.simplewebserver.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * This Enum describes compatible protocols. Offers methods for 
 * reading name and version. Only HTTP/1.0 and HTTP/1.1 are supported.
 */
public enum Protocol {
	
	HTTP_1_0("http", 1, 0), HTTP_1_1("http", 1, 1);
	
	private static final Map<String, Protocol> protocols;
    static {
        Map<String, Protocol> map = new HashMap<>();
        map.put("HTTP/1.0", HTTP_1_0);
        map.put("HTTP/1.1", HTTP_1_1);
        protocols = Collections.unmodifiableMap(map);
    }
	
	private String name;
	private int major;
	private int minor;
	
	Protocol(String name, int major, int minor){
		this.name = name;
		this.major = major;
		this.minor = minor;
	}
	
	/**
	 * 
	 * @return 
	 * Protocol name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return
	 * Major number of protocol version
	 */
	public int getVersionMajor() {
		return major;
	}
	
	/**
	 * 
	 * @return
	 * Minor number of protocol version
	 */
	public int getVersionMinor() {
		return minor;
	}
	
	/**
	 * 
	 * @return
	 * Full protocol version
	 */
	public String getFullVersion() {
		return new String(getVersionMajor() + "." + getVersionMinor());
	}
	
	/**
	 * 
	 * @param protocol
	 * @return
	 * A new {@code Protocol} object created from a {@code String}
	 */
	public static Protocol fromString(String protocol) {
		return protocols.get(protocol.toUpperCase());
	}
	
	@Override
	public String toString() {
		return getName().toUpperCase() + "/" + getFullVersion();
	}
}
