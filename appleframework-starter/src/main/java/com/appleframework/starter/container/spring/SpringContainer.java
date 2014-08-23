package com.appleframework.starter.container.spring;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.appleframework.config.PropertyConfigurer;
import com.appleframework.starter.container.Container;

/**
 * SpringContainer. (SPI, Singleton, ThreadSafe)
 * 
 * @author cruise.xu
 */
public class SpringContainer implements Container {

	private static Logger logger = Logger.getLogger(SpringContainer.class);
    
    public static final String DEFAULT_SPRING_CONFIG = "classpath*:config/*.xml";

    static ClassPathXmlApplicationContext context;
    
    public static ClassPathXmlApplicationContext getContext() {
		return context;
	}

	public void start() {
        String configPath = DEFAULT_SPRING_CONFIG;
        context = new ClassPathXmlApplicationContext(configPath.split("[,\\s]+"));
        context.start();
        
        String applicationName = PropertyConfigurer.getString("application.name");
        if(null != applicationName) {
        	context.setDisplayName(applicationName);
        }
    }

    public void stop() {
        try {
            if (context != null) {
                context.stop();
                context.close();
                context = null;
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    public void restart() {
        try {
            this.stop();
            this.start();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    public boolean isRunning() {
    	if (context != null) {
    		return context.isRunning();
    	}
    	else {
    		return false;
    	}
    }

	@Override
	public String getName() {
		if (context != null) {
			return context.getDisplayName();
    	}
    	else {
    		return PropertyConfigurer.getString("application.name");
    	}
	}
    
	@Override
	public String getType() {
		return "SpringContainer";
	}

}