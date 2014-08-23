package com.appleframework.starter.container.log4j;

import org.apache.log4j.Logger;

import com.appleframework.config.PropertyConfigurer;
import com.appleframework.starter.container.Container;


public class Log4jContainer implements Container {

	private static Logger logger = Logger.getLogger(Log4jContainer.class);

	@Override
	public void start() {
		logger.warn("Log4jContainer start");
	}

	@Override
	public void stop() {
		logger.warn("Log4jContainer stop");

	}

	@Override
	public void restart() {
		logger.warn("Log4jContainer restart");
	}

	@Override
	public boolean isRunning() {
		return true;
	}

	@Override
	public String getName() {
		return PropertyConfigurer.getString("application.name");
	}

	@Override
	public String getType() {
		return "LogContainer";
	}

}
