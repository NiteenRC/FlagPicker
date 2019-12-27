package com.flagpicker.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flagpicker.metric.IMetricService;

/**
 * This MeticRestController has Rest end point helps to monitor hits per service
 * 
 * @author Niteen Chougula
 * @version 1.0
 * @since 2019-12-27
 */
@RestController
public class MeticRestController {

	@Autowired
	private IMetricService metricService;

	@GetMapping("/metric")
	public ResponseEntity<Map<?, ?>> getMetric() {
		return ResponseEntity.ok().body(metricService.getFullMetric());
	}
}
