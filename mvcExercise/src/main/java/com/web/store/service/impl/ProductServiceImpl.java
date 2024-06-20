package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.dao.ProductDao;
import com.web.store.model.BookBean;
import com.web.store.model.CompanyBean;
import com.web.store.service.ProductService;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
	
	
	ProductDao  productDao;
	@Autowired
	public ProductServiceImpl(ProductDao proDuctDao) {
		super();
		this.productDao = proDuctDao;
	}




	@Override
	public List<BookBean> getAllProducts() {
		// TODO Auto-generated method stub
		return productDao.getAllProducts();
	}




	@Transactional
	@Override
	public int updateAllStocks() {
		//故意測試錯誤更新====
		int updateCount =0;
		//故意測試錯誤更新====
	    List<BookBean> allProducts = productDao.getAllProducts();
	    for(BookBean bb : allProducts) {
	        if (bb.getStock() != null && bb.getStock() < 50) {
	        	productDao.updateStock(bb.getBookId(), bb.getStock() + 50);
	        	 updateCount++;
	        }
	        //故意測試錯誤更新====
//	        if (n>=7) {
//	        	throw new RuntimeException ("更新時發生意外（測試用）");
//	        }
	      //故意測試錯誤更新====
	    }
	    return updateCount;
	}



	//Transactional啟動交易
	@Transactional
	@Override
	public List<String> getAllCategories() {
		return productDao.getAllCategories();
	}



	//Transactional啟動交易
	@Transactional
	@Override
	public List<BookBean> getProductsByCategory(String category) {
		
		return productDao.getProductsByCategory(category);
	}


	//根據給的ＩＤ 給取相對應的訊息
	@Transactional
	@Override
	public BookBean getProductById(int productId) {
		return productDao.getProductById(productId);
	}



	@Transactional
	@Override
	public void addProduct(BookBean product) {
		productDao.addProduct(product);
		
	}



	@Transactional
	@Override
	public CompanyBean getCompanyById(int companyId) {
		productDao.getCompanyById(companyId);
		return productDao.getCompanyById(companyId);
	}



	@Transactional
	@Override
	public List<CompanyBean> getCompanyList() {
		
		return productDao.getCompanyList();
	}
}
