package dto;

import java.util.List;

public class SimilarProducts {

	List<Long> listIds;
	ProductDetail productDetail;
	public List<Long> getListIds() {
		return listIds;
	}
	public void setListIds(List<Long> listIds) {
		this.listIds = listIds;
	}
	public ProductDetail getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
	
	
	
}
