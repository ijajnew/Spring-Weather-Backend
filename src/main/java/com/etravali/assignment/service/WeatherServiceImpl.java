package com.etravali.assignment.service;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.etravali.assignment.rest.WeatherResponse;
import com.etravali.weather.model.OpenWeatherSchema;
import com.etravali.weather.model.WeatherSchema;
import com.etravali.assignment.notification.NotificationService;

@SuppressWarnings("unchecked")
@Service
public class WeatherServiceImpl implements WeatherService {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	//Injecting properties
	@Value("${weather.endpoint}")
	private String weatherEndpoint;
	
	@Value("${weather.endpoint.key}")
	private String weatherEndpointKey;
	
	@Value ("${openWeather.endpoint}")
	private String openWeatherEndpoint;
	
	@Value("${openWeather.endpoint.key}")
	private String openWeatherEndpointKey;
	
	@Autowired
	private List<NotificationService> notificationServices;
	
	private static final Map<String, NotificationService> notificationServiceCache = new HashMap<>();

	//creating post construct to create this after bean creating
    @PostConstruct
    public void initMyServiceCache() {
        for(NotificationService service : notificationServices) {
        	notificationServiceCache.put(service.getType(), service);
        }
    }

    //creating object of notification service based on user selection (email, slack, message)
    public static NotificationService getService(String type) {
    	NotificationService service = notificationServiceCache.get(type);
        if(service == null) throw new RuntimeException("Unknown service type: " + type);
        return service;
    }

	@Override
	public WeatherResponse getWeather(String city, String notificationType) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		// creating params map
		Map uriVariables = new HashMap();
		uriVariables.put("key", weatherEndpointKey);
		uriVariables.put("city", city);
		
		//Calling 1st api to get Temperature
		ResponseEntity<WeatherSchema> output = restTemplate.getForEntity(weatherEndpoint, WeatherSchema.class, uriVariables);
		
		// creating params map
		Map params = new HashMap();
		params.put("key", openWeatherEndpointKey);
		params.put("lat", output.getBody().getLocation().getLat());
		params.put("lon", output.getBody().getLocation().getLon());
		
		//Calling 2nd api to get temperature
		ResponseEntity<OpenWeatherSchema> output2 = restTemplate.getForEntity(openWeatherEndpoint, OpenWeatherSchema.class, params);
		
		WeatherResponse response = new WeatherResponse();
		response.setCity(output.getBody().getLocation().getName());
		response.setState(output.getBody().getLocation().getRegion());
		response.setCountry(output.getBody().getLocation().getCountry());
		
		logger.info("Got response from services.. calculating average temp.");
		
		//Calculate average of two api output
	    Arrays.asList(output.getBody().getCurrent().getTempC(), output2.getBody().getMain().getTemp()).stream()
			.mapToDouble((x) -> x)
			.average()
			.ifPresent(avg -> response.setTemp_c(avg));
	    
		response.setCondition(output.getBody().getCurrent().getCondition().getText());
		
		//getting notification object based on type selected by user
		NotificationService notificationService = getService(notificationType);		
		//sending notification
		response.setMessage(notificationService.sendNotification());
		
		logger.info("Sending response");
		return response;
	}
	

}
