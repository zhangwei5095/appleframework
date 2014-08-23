package com.appleframework.starter.container;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.apache.log4j.Logger;

import com.appleframework.config.EnvConfigurer;
import com.appleframework.starter.container.log4j.Log4jContainer;
import com.appleframework.starter.container.log4j.LoggingConfigManager;
import com.appleframework.starter.container.spring.SpringContainer;
import com.appleframework.starter.container.spring.SpringContainerManager;

/**
 * Main. (API, Static, ThreadSafe)
 * 
 * @author cruise.xu
 */
public class ContainerStarter {

    public static final String SHUTDOWN_HOOK_KEY = "shutdown.hook";
    
    private static Logger logger = Logger.getLogger(ContainerStarter.class);
    
    private static volatile boolean running = true;

    public static void main(String[] args) {
        try {
        	for (int i = 0; i < args.length; i++) {
				String envArgs = args[i];
				if(envArgs.indexOf("env=") > -1) {
					String[] envs = envArgs.split("=");
					EnvConfigurer.env = envs[1];
					logger.warn("配置项：env=" + EnvConfigurer.env);
				}
			}
            
        	MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        	
            final List<Container> containers = new ArrayList<Container>();
            Container springContainer = new SpringContainer();
            Container logContainer = new Log4jContainer();
            containers.add(springContainer);
            containers.add(logContainer);
            
            logger.info("Use container type(" + Arrays.toString(args) + ") to run serivce.");
            
            if ("true".equals(System.getProperty(SHUTDOWN_HOOK_KEY))) {
	            Runtime.getRuntime().addShutdownHook(new Thread() {
	                public void run() {
	                    for (Container container : containers) {
	                        try {
	                            container.stop();
	                            logger.info("Service " + container.getClass().getSimpleName() + " stopped!");
	                        } catch (Throwable t) {
	                            logger.error(t.getMessage(), t);
	                        }
	                        synchronized (ContainerStarter.class) {
	                            running = false;
	                            ContainerStarter.class.notify();
	                        }
	                    }
	                }
	            });
            }
            
            for (Container container : containers) {
                container.start();
                
                try {
					ObjectName oname = new ObjectName("Container:type=" + container.getType());
					
					if(container instanceof SpringContainer) {
						SpringContainerManager mbean = new SpringContainerManager();
						if (mbs.isRegistered(oname)) {
							mbs.unregisterMBean(oname);
						}
						mbs.registerMBean(mbean, oname);
					}
					else if(container instanceof Log4jContainer) {
						LoggingConfigManager mbean = new LoggingConfigManager();
						if (mbs.isRegistered(oname)) {
							mbs.unregisterMBean(oname);
						}
						mbs.registerMBean(mbean, oname);
					}
					else {
						logger.error("container类型出错");
					}
				} catch (Exception e) {
					logger.error("注册JMX服务出错：" + e.getMessage(), e);
				}
                logger.warn("服务 " + container.getClass().getSimpleName() + " 启动!");
            }
            
            logger.warn(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " 所有服务启动成功!");
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            System.exit(1);
        }
        synchronized (ContainerStarter.class) {
            while (running) {
                try {
                    ContainerStarter.class.wait();
                } catch (Throwable e) {
                	logger.error(e.getMessage(), e);
                }
            }
        }
    }
    
}