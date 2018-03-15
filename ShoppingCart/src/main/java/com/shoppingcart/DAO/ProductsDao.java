package com.shoppingcart.DAO;

import java.util.List;

import com.shoppingcart.Beans.ProductsBean;

public interface ProductsDao {

	public void saveProducts(ProductsBean productsBean);
	public void editProducts(ProductsBean productsBean);
	public ProductsBean getProductsDetail(String productCode);
	public List<ProductsBean> getProductList();
	public ProductsBean getProductToCart(String productCode);
	public void deleteProduct(ProductsBean productsBean);
	public void deleteProductByCode(String productCode);
	public double getPrice(String productCode);
}
