 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import restCalls.SimilarCalls;

@SpringBootApplication
@ComponentScan(basePackages = { "cacheConfig","controllers","models","restCalls","services"
})

@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableCaching
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		HttpComponentsClientHttpRequestFactory componentsClientHttpRequestFactory =
				new HttpComponentsClientHttpRequestFactory();
		componentsClientHttpRequestFactory.setConnectTimeout(2000);
		
		return new RestTemplate(componentsClientHttpRequestFactory);
	}
	


}

