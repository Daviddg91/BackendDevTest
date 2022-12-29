package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import FeingInterfaces.Similars;
import dto.SimilarProducts;

@Controller
@RequestMapping("/product")
public class SimilarController {

	@Autowired
	Similars similarsImp;
	
	
	@GetMapping("/{productId}/similar")
	public SimilarProducts similarProducts(@RequestParam(name = "productId") Long productId) {
		
		SimilarProducts similarProduct = new SimilarProducts();
		similarProduct.setProductDetail(similarsImp.getProductDetails(productId));
		similarProduct.setListIds(similarsImp.getProductSimilarIds(productId));
		
		return similarProduct;
	}
	
}
