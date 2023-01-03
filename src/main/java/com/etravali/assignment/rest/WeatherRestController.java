package com.etravali.assignment.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etravali.assignment.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherRestController {
	
	@Autowired
	public WeatherService weatherService;
	
	//to get temperature
	@GetMapping("/temperature/{city}/{notificationType}")
	public WeatherResponse getWeather(@PathVariable String city,
			@PathVariable String notificationType){
		return weatherService.getWeather(city, notificationType);
	}

}
