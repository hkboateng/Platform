/**
 * hkboateng
 */
package com.boateng.abankus.listener;

import java.util.logging.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author hkboateng
 *
 */
public class PlatformSessionListener implements HttpSessionListener{

	private static final Logger logger = Logger.getLogger(PlatformSessionListener.class.getSimpleName());
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		logger.info("New Session has being created...."+event.getSession().getId());
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {

		if(event.getSession().getLastAccessedTime() > 480000){
			logger.info("Current session has being destroyed."+event.getSession().getId());
			event.getSession().invalidate();
			
		}
		

	}

}
