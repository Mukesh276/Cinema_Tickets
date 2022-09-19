package uk.gov.dwp.uc.pairtest.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {

		Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

		logger.log(Level.INFO, "--------Exception logged for developers ----------");
		logger.log(Level.INFO, e.getMessage(), e);

		logger.log(Level.INFO, "--------Exception to be shown for users ----------");
		logger.log(Level.INFO, e.getMessage());
	}
}
