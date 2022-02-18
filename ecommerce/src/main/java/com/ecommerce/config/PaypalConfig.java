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

	private String clientId = "AXHhJ1aYwN3U1i6Xj5DjGCGK47nqAF1GgkimwtpOxHLomMgGgpOrgU7nIV76WiqIvAxzCt4pAYVIh-oX";
	private String clientSecret= "EIcLgPUPaFyuG-rek81P_TQ1A_jNGcTd-o_xfMfiwxZgCfLxncQkHmKGV-P1NNTLK9a_EcsopjNMk9oT";
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
