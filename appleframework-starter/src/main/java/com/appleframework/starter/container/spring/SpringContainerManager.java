package com.appleframework.starter.container.spring;

import com.appleframework.starter.container.Container;



/**
 * Main. (API, Static, ThreadSafe)
 * 
 * @author Cruise.Xu
 */
public class SpringContainerManager implements SpringContainerManagerMBean {
	
	Container springContainer = new SpringContainer();

	@Override
	public String getName() {
		return springContainer.getName();
	}

	@Override
	public void restart() {
		springContainer.restart();
	}
	
	@Override
	public void start() {
		springContainer.start();
	}

	@Override
	public void stop() {
		springContainer.stop();
		
	}

	@Override
	public boolean isRunning() {
		return springContainer.isRunning();
	}
   
}