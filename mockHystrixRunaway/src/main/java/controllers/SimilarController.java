package  controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.SimilarProducts;
import services.SimilarProductService;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class SimilarController {

	@Autowired
	SimilarProductService similarService;

	@Autowired 
	CacheManager cacheManager;
	
	@GetMapping("/{productId}/similar")
	public SimilarProducts similarProducts(@PathVariable Long productId) {
		return similarService.getSimilarProducts(productId);
	}
	
	
	@GetMapping("/cache")
	public SimilarProducts getCache() {
		cacheManager.getCache("CacheProductDetails");
		
		return null;
	}
	
}
