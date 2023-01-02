package  services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;

import models.SimilarProducts;
import restCalls.SimilarCalls;

@Service
public class SimilarProductService {
    
    @Autowired
    private SimilarCalls similarsCalls;
    
    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
//			@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "THREAD"),
//          @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "3000"),
//          @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT, value = "true"),
          @HystrixProperty(name = HystrixPropertiesManager.EXECUTION_TIMEOUT_ENABLED, value = "false"),

            },
            threadPoolKey = "similarInfoPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value = "2"),
                    @HystrixProperty(name = "maxQueueSize",value = "200"),

            })
	public SimilarProducts getSimilarProducts(Long productId) {
		
		SimilarProducts similarProduct = new SimilarProducts();
 
			similarProduct.setProductDetail(similarsCalls.productDetailCall(productId));
			similarProduct.setListIds(similarsCalls.similarsProductListCall(productId));
 
		return similarProduct;
	}
    
    

    public SimilarProducts getFallbackCatalogItem(Long productId) {
//        return new CatalogItem("Movie Not Found","",rating.getRating());
    	System.out.println("error ");
		return new SimilarProducts();
    }
}


