package bpaunescu.simplewebserver.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * Class that handles validation and read operations on files
 */
public class ResourceManager {
	
	private String root;
	private String homePage;
	
	/**
	 * Constructs a new object using the {@code path} parameter
	 * @param path 
	 * - absolute path to the root directory of the resources
	 * @param defaultResource
	 * - home page(i.e. index.html)
	 */
	public ResourceManager(String path, String defaultResource){
		root = path;
		homePage = defaultResource;
		
		while (root.endsWith("/"))
			root = root.substring(0, root.length() - 1);
	}
	
	/**
	 * Validates the URL given as parameter against security breaches
	 * @param url 
	 * - relative path of the resource
	 * @return
	 * {@code true} if the URL is valid, {@code false} otherwise
	 */
	public boolean isAllowed(String url) {
		return url.indexOf("..") + url.indexOf(":") + url.indexOf("|") == -3;
	}
	
	/**
	 * Checks if the resource indicated by the URL exists
	 * @param url
	 * - relative path of the resource
	 * @return
	 * {@code true} if resource exists, {@code false} otherwise
	 */
	public boolean resourceExists(String url) {
		File file;
	
		if (url.equals("/")) {
			file = new File(root + "/" + homePage);
		} else {
			file = new File(root + url);
		}
		
		return file.isFile();
	}
	
	/**
	 * Reads the resource indicated by the URL
	 * @param url
	 * - relative path of the resource
	 * @return
	 * an array of bytes with the contents of the resource
	 * @throws IOException
	 */
	public byte[] readResource(String url) throws IOException {
		String fullPath = root + url;
		
		if (url.equals("/")) {
			fullPath += homePage;
		}
		Path pathObject = Paths.get(fullPath);
		
		return Files.readAllBytes(pathObject);
	}
	
	/**
	 * 
	 * @param url
	 * - relative path of the resource
	 * @return
	 * the full path of the resource
	 */
	public String getResourceFullPath(String url) {
		String fullPath = root + url;
		
		if (url.equals("/")) {
			fullPath += homePage;
		}
		
		return fullPath;
	}
	
	/**
	 * 
	 * @param url
	 * - relative path of the resource
	 * @return
	 * the file extension of the resource
	 */
	public String getExtension(String url) {
		String resource = url;
		
		if (url.equals("/")) {
			resource = homePage;
		}
		
		return resource.lastIndexOf(".") > 0 ? resource.substring(resource.lastIndexOf(".") + 1) : "";
	} 
}
