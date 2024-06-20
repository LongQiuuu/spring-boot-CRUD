package com.web.store.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.dao.ProductDao;
import com.web.store.model.BookBean;
import com.web.store.model.CompanyBean;
@Repository
public class ProductDaoImpl implements ProductDao {

	SessionFactory factory;
	@Autowired
	public ProductDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public List<BookBean> getAllProducts() {
		String hql = "FROM BookBean";
		Session session = factory.getCurrentSession();
		List<BookBean> bookBeans = session.createQuery(hql,BookBean.class).getResultList();
		return bookBeans;
	}

	@Override
	public void updateStock(int productId, int newQuantity) {
		//建立session工廠
		 Session session = factory.getCurrentSession();
		 //進行資料庫產品更新
		    String hql = "UPDATE BookBean b SET b.stock = :stock WHERE bookId = :id";
		    session.createQuery(hql).setParameter("stock", newQuantity)
		                            .setParameter("id", productId)
		                            .executeUpdate();
		
	}
	
	//搜尋資料庫 所有不重複的值
	@Override
	public List<String> getAllCategories() {
		 Session session = factory.getCurrentSession();
		 String hql = "SELECT DISTINCT category FROM BookBean";
		 //getResultList只有查詢類 才需要宣告查詢的型態String.class
		 List<String> list = session.createQuery(hql, String.class)
                   					  .getResultList();
		return list;
	}

	@Override
	public List<BookBean> getProductsByCategory(String category) {
		 Session session = factory.getCurrentSession();
		 String hql = "FROM BookBean WHERE category = :cate";
		 //getResultList只有查詢類 才需要宣告查詢的型態String.class
		 List<BookBean> bookBean = session.createQuery(hql, BookBean.class)
                 						.setParameter("cate", category)
                 						.getResultList();
		return bookBean;
	}
	//根據給的ＩＤ 給取相對應的訊息
	@Override
	public BookBean getProductById(int productId) {
		 Session session = factory.getCurrentSession();
		 BookBean bookBean =session.get(BookBean.class,productId );
		return bookBean;
	}

	//新增一個書籍資料
	@Override
	public void addProduct(BookBean product) {
		 Session session = factory.getCurrentSession();
		 //	獲取對應的出版社信息
		 CompanyBean cb = getCompanyById(product.getCompanyId());
		 product.setCompanyBean(cb);
		 session.save(product);
	}
	//取得出版社資料
	@Override
	public CompanyBean getCompanyById(int companyId) {
		 Session session = factory.getCurrentSession();
		
		 return session.get(CompanyBean.class, companyId);
	}
	//获取系统中所有出版社的列表
	@Override
	public List<CompanyBean> getCompanyList() {
		Session session = factory.getCurrentSession();
	    String hql = "FROM CompanyBean";
	    List<CompanyBean> companyBeanlist = session.createQuery(hql, CompanyBean.class)
	    		                        .getResultList();
	    return companyBeanlist;
	}

	
	
}
