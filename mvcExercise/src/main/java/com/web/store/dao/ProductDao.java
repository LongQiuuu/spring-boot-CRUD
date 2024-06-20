package com.web.store.dao;

import java.util.List;

import com.web.store.model.BookBean;
import com.web.store.model.CompanyBean;

public interface ProductDao {
	
	List <BookBean>  getAllProducts();
	
	//一次負責更新一筆的庫存
	void updateStock(int productId, int newQuantity);
	
	//搜尋資料庫 所有不重複的值
	List<String>  getAllCategories();

	List<BookBean>  getProductsByCategory(String category);
	
	//根據給的ＩＤ 給取相對應的訊息
	public BookBean getProductById(int productId);
	
	//新增一個書籍資料
	void  addProduct(BookBean product);
	//取得出版社資料
	CompanyBean  getCompanyById(int companyId);
	//获取系统中所有出版社的列表
	List<CompanyBean>  getCompanyList();
}
