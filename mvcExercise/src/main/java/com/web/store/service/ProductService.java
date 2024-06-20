package com.web.store.service;

import java.util.List;

import com.web.store.model.BookBean;
import com.web.store.model.CompanyBean;

public interface ProductService {
	
	List <BookBean>  getAllProducts();
	
	int updateAllStocks();
	
	
	//搜尋資料庫 获取所有图书的类别 且不重複
	List<String>  getAllCategories();
	//根据给定的类别获取相应类别的图书列表。
	List<BookBean>  getProductsByCategory(String category);
	
	//根據給的ＩＤ 給取相對應的訊息
	public BookBean getProductById(int productId);
	
	void  addProduct(BookBean product);
	CompanyBean  getCompanyById(int companyId);
	List<CompanyBean>  getCompanyList();

}
