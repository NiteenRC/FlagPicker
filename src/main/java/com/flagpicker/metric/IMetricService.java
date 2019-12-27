package com.flagpicker.metric;

import java.util.Map;

/**
 * This IMetricService has contract for increaseCount and get full metrics
 * 
 * @author Niteen Chougula
 * @version 1.0
 * @since 2019-12-27
 */
public interface IMetricService {

	/**
	 * Increase the count for an each service
	 * 
	 * @param request
	 * @param status
	 */
	void increaseCount(final String request, final int status);

	/**
	 * Return full metrics
	 * 
	 * @return Map<?, ?>
	 */
	Map<?, ?> getFullMetric();
}
