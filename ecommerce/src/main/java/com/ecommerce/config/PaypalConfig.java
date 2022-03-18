package com.ecommerce.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfig {

	private String clientId = "AT466csfkdtD71dGeoWGmz4oUQJmodSqfcqfLhw9pufuluDUVtN3i7J9pUYGiD-U1mkHqvNWAXWIFi4H";
	private String clientSecret= "EIpCYypzEY52Av2yxSC5SFO2eTrZXwgzNqo9jo9tHGbJBIItQH0A4zxNOJuu_7d238d9b6vpn5ikqOXn";
	private String mode="sandbox";
	
	@Bean
	public Map<String, String> paypalSdkConfig(){
		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", mode);
		return sdkConfig;
	}
	
	@Bean
	public OAuthTokenCredential authTokenCredential(){
		return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
	}
	
	@Bean
	public APIContext apiContext() throws PayPalRESTException{
		APIContext apiContext = new APIContext(authTokenCredential().getAccessToken());
		apiContext.setConfigurationMap(paypalSdkConfig());
		return apiContext;
	}
}
