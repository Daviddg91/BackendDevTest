package restCalls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Optional;

import models.ProductDetail;

@Service
public class SimilarCalls {

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired 
    Environment env;
    
    @Cacheable(value="CacheProductDetails", key="#productId", condition = "#result != null")
    public ProductDetail productDetailCall(Long productId)  {   
    	ProductDetail productOuput = null;
    	try {
    	ProductDetail productDetailRest = restTemplate.getForObject(env.getProperty("mockurl") + "/product/" + productId  , ProductDetail.class);
    	Optional<ProductDetail> isProductDetail = Optional.fromNullable(productDetailRest);
    	if (isProductDetail.isPresent()) {
    		productOuput = isProductDetail.get();
    	} 
    	}catch(Exception e) {
    		System.out.println("error " + e);
    	}
    	return productOuput;
    }
    
    @Cacheable(value="CacheSimilarsProductList", key="#productId", condition = "#result != null")
    public Long[] similarsProductListCall(Long productId) {    	
    	Long[] productOuput = null;
    	try {
    		Long[] productDetailRest = restTemplate.getForObject(env.getProperty("mockurl") + "/product/" + productId +"/similarids " , Long[].class);
    	Optional<Long[]> isProductDetail = Optional.fromNullable(productDetailRest);
    	if (isProductDetail.isPresent()) {
    		productOuput = isProductDetail.get();
    	} 
    	}catch(Exception e) {
    		System.out.println("error " + e);
    	}
    	return productOuput;
     
    }


    
}
