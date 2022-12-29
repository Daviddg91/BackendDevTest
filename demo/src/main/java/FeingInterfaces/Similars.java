package FeingInterfaces;

import java.util.List;

import dto.ProductDetail;
import feign.Param;
import feign.RequestLine;

public interface Similars {
	    @RequestLine("GET localhost:3001/{productId}")
	    ProductDetail getProductDetails(@Param("productId") long productId);

	    @RequestLine("GET localhost:3001/{productId}/similarids")
	    List<Long> getProductSimilarIds(@Param("productId") long productId);
	}

