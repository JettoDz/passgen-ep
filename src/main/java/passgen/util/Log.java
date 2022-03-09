package passgen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import passgen.controllers.PassgenController;

public class Log {
	
	private static final Logger LOG = LoggerFactory.getLogger(PassgenController.class); 
	
	private Log() {}
	
	public static void error(String msg, Throwable t) {
		LOG.error(msg, t);
	}

}
