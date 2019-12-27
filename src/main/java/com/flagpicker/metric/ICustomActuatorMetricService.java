package com.flagpicker.metric;

/**
 * This ICustomActuatorMetricService has contract for increaseCount for custom
 * 
 * @author Niteen Chougula
 * @version 1.0
 * @since 2019-12-27
 */
public interface ICustomActuatorMetricService {

	/**
	 * Used to increase custom count
	 * 
	 * @param status
	 */
	void increaseCount(final int status);
}
