package passgen.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Logging {
	
	default Logger getLogger() {
		return LoggerFactory.getLogger(getClass());
	}  
	
	default void info(String msg) {
		getLogger().info(msg);
	}
	
	default void error(String msg, Throwable t) {
		getLogger().error(msg, t);
	}

}
