package com.etravali.assignment.service;

import com.etravali.assignment.rest.WeatherResponse;

public interface WeatherService {
	
	public WeatherResponse getWeather(String city, String notificationType);

}
