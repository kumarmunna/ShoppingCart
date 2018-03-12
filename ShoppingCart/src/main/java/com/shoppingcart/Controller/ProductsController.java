package com.shoppingcart.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingcart.Beans.ProductsBean;
import com.shoppingcart.DAO.ProductsDao;
import com.shoppingcart.DAO.impl.HibernateDaoImpl;
import com.shoppingcart.Model.LoginBean;
import com.shoppingcart.Validator.ProductValidator;

@Controller
public class ProductsController {

	@Autowired
	private ProductsDao productsDao;
	
	@Autowired
	private ProductValidator productValidator;
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		System.out.println(" Validating Product Jsp ");
		binder.setDisallowedFields("data");
	      binder.addValidators(productValidator);
	}
	
	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ProductList");
		List<ProductsBean> productList = productsDao.getProductList();
		mv.addObject("productList", productList);
		return mv;
	}

	/**
	 * Method use to redirect on the add product view.
	 * @return
	 */
	@RequestMapping("/product")
	public ModelAndView product(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addProducts");
		mv.addObject("productbean",new ProductsBean());
		System.out.println("Method: product ....END ");
		return mv;
	}
	/**
	 * Method used for add product in database.
	 * 
	 * @param productsBean
	 * @param result
	 * @param model
	 * @return
	 * @throws SerialException
	 * @throws SQLException
	 */
	@RequestMapping(value= "/addProduct",method=RequestMethod.POST)
	public ModelAndView SaveProductInDataBase(@ModelAttribute("productbean") ProductsBean productsBean,
			BindingResult result, Model model) throws SerialException, SQLException{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addProducts");
		productsDao.saveProducts(productsBean);
		
		return mv;
	}
	/**
	 * Method used for edit product.
	 * @param productCode
	 * @return
	 */
	@RequestMapping("/editAndSaveProduct")
	public ModelAndView editProduct(@ModelAttribute("productbean") @Validated ProductsBean productsBean,
			BindingResult result, Model model) {
		ModelAndView mv = new ModelAndView();
		if(result.hasErrors()){
			System.out.println(" in has error =-> "+result.getFieldError());
			mv.setViewName("EditProduct");
		}else{
			productsDao.editProducts(productsBean);
			mv.setViewName("redirect:/productList");
		}
		return mv;
	}
	/**
	 * This method is used to redirect to edit product page.
	 * @param productCode
	 * @return
	 */
	@RequestMapping("/editProduct")
	public ModelAndView editPageRedirect(@RequestParam("productCode") String productCode){
		ModelAndView mv = new ModelAndView();
		ProductsBean productsBean = productsDao.getProductsDetail(productCode);
		mv.addObject("productbean", productsBean);
		mv.setViewName("EditProduct");
		return mv;
	}
	
	@RequestMapping("/productList")
	public ModelAndView getProductsList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ProductList");
		List<ProductsBean> productList = productsDao.getProductList();
		mv.addObject("productList", productList);
		return mv;
	}
}