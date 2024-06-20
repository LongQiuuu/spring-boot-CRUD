package com.web.store.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.store.model.BookBean;
import com.web.store.model.CompanyBean;
import com.web.store.service.ProductService;

//要成為控制器要加這個
@Controller
//跨網頁送訊息 (哪些物件要加到Session裡面去)
@SessionAttributes ({"successMessage","kitty"})
public class ProductController {
	
	private static Logger Log =LoggerFactory.getLogger( ProductController .class);
	
	ProductService productService;
	ServletContext servletContext;
	
	@Autowired
	public ProductController(ProductService productService, ServletContext servletContext) {
		this.productService = productService;
		this.servletContext = servletContext;
	}

	
	


	@GetMapping("/products")
	public String list(Model model) {
		//這邊是直接new的寫法
//		CompanyBean cb = new CompanyBean(1, "學貫行銷股份有限公司", "台北市林森南路四號六樓", 
//										"http://www.xbook.com.tw/");
//		BookBean bb = new BookBean(1002, "PHP&MySQL程式設計實例講座", "陳惠貞", 580.0, 0.6, 
//									"bookxb002.jpg", "P832", null, 25,	"Web", cb);
		
		
		//這邊是從資料庫的寫法
		List<BookBean> bookBeans =productService.getAllProducts();
		
		//直接加一個物件 new寫法
		//model.addAttribute("product", bb);
		//資料庫寫法
		model.addAttribute("products", bookBeans);
		return "products";
	}
		
		//更新產品資料庫方法
		@GetMapping("/update/stock")
		public String updateStock(Model model) {
			try {
				//成功得時候對瀏覽器發出更新請求
				int count=productService.updateAllStocks();
				//跨網頁送訊息 使用session
				model.addAttribute("successMessage", "成功更新"+count+"筆記錄");
				model.addAttribute("kitty", "初步接觸spring MVC 的session Attributes");
				//新增或修改成功 要使用redirect通知瀏覽器
			    return "redirect:/products";
			}catch (Exception e) {
				//發生錯誤時執行
				System.out.println(e.getMessage());
				List<BookBean> bookBeans =productService.getAllProducts();
				model.addAttribute("products", bookBeans);
				model.addAttribute("errorMessage",e.getMessage());
				return "products";
			}
			
			
		} 
		//請他顯示一個網頁 裡面有所有的的類型
		@GetMapping("/queryByCategory")
		public String getCategoryList(Model model) {
		    List<String>  list = productService.getAllCategories();
		    model.addAttribute("categoryList", list);
		    return "types/category";
		}
		//選了什麼類型顯示裡面的內容
		//大{}包著會變動的
		@GetMapping("/products/{category}")
		public String getProductsByCategory(
				@PathVariable("category") String category, 
				Model model
		){
		    List<BookBean> products = productService.getProductsByCategory(category);
		    model.addAttribute("products", products);
		    return "products";
		}
	
		
		//根據給的ＩＤ 給取相對應的訊息
		@GetMapping("/product")
		public String getProductById(Model model,@RequestParam("id") Integer productId ){
			BookBean bookBean=productService.getProductById(productId);
		    model.addAttribute("product", bookBean);
		    return "product";
		}
		
		//根據給的ＩＤ 給取相對應的訊息 更改路徑變數
		@GetMapping("/product/{id}")
		public String findProductById(Model model,@PathVariable("id") Integer productId ){
			BookBean bookBean=productService.getProductById(productId);
			model.addAttribute("product", bookBean);
			return "product";
		}

		//products/add 路径时，向视图中传递一个新的 BookBean 对象，以便在表单中填充新图书的信息。
		@GetMapping("/products/add")
		public String getAddNewProductForm(Model model) {
		    BookBean bb = new BookBean();
		    model.addAttribute("bookBean", bb); 
		    return "addProduct";
		}
		//新增功能
//		@PostMapping("/products/add")
//		public String processAddNewProductForm(
//				//用"bookBean"來收前端送來的資料
//				@ModelAttribute("bookBean") BookBean bb
//		) { 
//			productService.addProduct(bb);
//		    return "redirect:/products";
//		}
		
		////自動抓取資料並放入下拉式選單第一種寫法
		@ModelAttribute("companyList")
		public Map<Integer, String> getCompanyList() {
		    Map<Integer, String> companyMap = new HashMap<>();
		    List<CompanyBean> list = productService.getCompanyList();
		    for (CompanyBean cb : list) {
		        companyMap.put(cb.getId(), cb.getName());
		    }
		    return companyMap;
		}
		
		
		//自動抓取資料並放入下拉式選單第二種寫法
		@ModelAttribute("companyList2")
		public List<CompanyBean> getCompanyList2() {

		    List<CompanyBean> list = productService.getCompanyList();

		    return list;
		}
		
		
		@ModelAttribute("categoryList")
		public List<String> getCategoryList() {
		    return productService.getAllCategories();
		}


		@ModelAttribute("kitty")//每次请求处理方法被调用之前执行
		 public BookBean getBook() {
		  Log.info("getBooK()");
		  BookBean bookBean = new BookBean();
		  return bookBean; //自動成為屬性物件
		 }
		
		 @ModelAttribute //每次请求处理方法被调用之前执行
		 public void getData(Model model) {
		  Log.info("getData()");
		  String message = "hola";
		  Integer balance = 25;
		  Double[] da = {100.0,3.14};
		  model.addAttribute("msg", message);
		  model.addAttribute("blc", balance);
		  model.addAttribute("data", da);	
		 }
		 
		 //處理日期的格式
		 @InitBinder
			public void initBinder(WebDataBinder binder, WebRequest request) {
			    // java.util.Date 假設有個訂單時間讓使用者自己輸入
			    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
			    dateFormat.setLenient(false);    // 2005-18-35
			    CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
			    binder.registerCustomEditor(Date.class, ce);
			    
			    // java.sql.Date   讓使用者輸入生日 只有年月日     
			    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			    dateFormat2.setLenient(false);
			    CustomDateEditor ce2 = new CustomDateEditor(dateFormat2, true);
			    binder.registerCustomEditor(java.sql.Date.class, ce2);
			}
		 
		 
		 //允許前端可以傳送的白名單
		 @InitBinder
		 public void whiteListing(WebDataBinder binder) {
		     binder.setAllowedFields(
		     "author", 
		     "bookNo", 
		     "category", 
		     "price", 
		     "title", 
		     "companyId" 
		     );
		 }
		 
		 //添加新表單的方法
		 // BindingResult result 存放錯誤的資訊
		 @PostMapping("/products/add")
		 public String processAddNewProductForm(
		         @ModelAttribute("bookBean") BookBean bb,
		         BindingResult result ) {
		     String[] suppressedFields = result.getSuppressedFields();
		     if (suppressedFields.length > 0) {
		         throw new RuntimeException("嘗試傳入不允許的欄位: " + 
		         StringUtils.arrayToCommaDelimitedString(suppressedFields));
		     }
		     productService.addProduct(bb);
		     return "redirect:/products";
		 }
		 

		// return "forward:/anotherFWD": 轉發(forward)給能夠匹配給 /anotherFWD的控制器方法
		// 將與下一棒的程式共用同一個請求物件
		// return "anotherFWD": 也是轉發，但Spring框架會視anotherFWD為視圖的邏輯名稱來尋找
		// 對應的視圖，然後由該視圖來產生回應
		@GetMapping("/forwardDemo")
		public String forward(Model model, HttpServletRequest request) {
			String uri = request.getRequestURI();
			model.addAttribute("modelData0", "這是以/forwardDemo送來的請求");
			model.addAttribute("uri0", uri);
			return "forward:/anotherFWD";
		}

		// 被轉發的方法，將與前一個方法共用同一個請求物件
		@GetMapping("/anotherFWD")
		public String forwardA(Model model, HttpServletRequest request) {
			String uri = request.getRequestURI();
			model.addAttribute("modelData1", "這是以/anotherFWD送來的請求");
			model.addAttribute("uri1", uri);
			return "forwardedPage";
		}

		// return "redirect:/redirectAnother": 通知瀏覽器對新網址 /redirectAnother發出請求，即重定向
		// (redirect)。由於是另外一個請求，所以放在原來之請求物件內的資料將不存在。必須將屬性物件儲存
		// 在 RedirectAttributes物件內，另外一個請求才會看的到對應的視圖，然後由該視圖來產生回應。
		@GetMapping("/redirectDemo")
		public String redirect(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
			String uri = request.getRequestURI();
			model.addAttribute("modelData2", "這是以/redirectDemo送來的請求，即將通知瀏覽器對" + "新網址發出請求，但瀏覽器不會顯示這樣的訊息");
			model.addAttribute("uri2", uri);
			redirectAttributes.addFlashAttribute("modelData3", "這是加在RedirectAttributes" + "物件內的屬性物件，瀏覽器會顯示");
			redirectAttributes.addFlashAttribute("uri3", uri);
			return "redirect:/redirectAnother";
		}

		// -------------------------
		// 瀏覽器對新網址重新發出的請求將會由這個請求器方法來處理
		@GetMapping("/redirectAnother")
		public String redirectA(Model model, HttpServletRequest request) {
			return "redirectedPage";
		}
		
		@GetMapping("/getPicture/{bookId}")
		public ResponseEntity<byte[]>  getPicture (HttpServletResponse response,
				@PathVariable Integer bookId
				){
			//原本的圖片的位置路徑
			 String filePath = "/resources/images/NoImage.jpg";
			 //圖片將來都會放在這個變數
			 byte[] media = null;
			 //定義回應service的容器
			 HttpHeaders headers = new HttpHeaders();
			 String filename =null;
			 int len = 0;
			 
			 //依照組件 讀取我們的BookBean
			  BookBean bean = productService.getProductById(bookId);
			 //確定BookBean有讀到
			  if (bean != null) {
				  Blob blob =bean.getCoverImage();
				//BookBean原來的檔名
				   filename = bean.getFileName();
				  try {
					if (blob !=null) {
						  //InputStream is =blob.getBinaryStream();
						  long length =blob.length();
						  media =blob.getBytes(1, (int)length);
					  }else {
						  //送出預設圖片
						  media= toByteArray(filePath);
					  }
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			  }else {
				  //萬一查不到我們圖片也送一個預設的
				  media= toByteArray(filePath);
			  }
			  headers.setCacheControl(CacheControl.noCache().getHeaderValue());
			  String mimeType = servletContext.getMimeType(filename);
			  MediaType mediaType = MediaType.valueOf(mimeType);
			  System.out.println("mediaType =" + mediaType);
			  headers.setContentType(mediaType);
			  
			  ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
			  return responseEntity;
		}
		//指定文件路径的文件讀取為位元組陣列
		private byte[] toByteArray(String filepath) {
		    byte[] b = null;
		    //servletContext.getRealPath(filepath); 圖片在系統下的位置
		    String realPath = servletContext.getRealPath(filepath);
		    try {
		        File file = new File(realPath);
		        long size = file.length();
		        b = new byte[(int) size];
		        InputStream fis = servletContext.getResourceAsStream(filepath);
		        //如果你有傳東西進來 我就一次讀滿
		        fis.read(b);
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return b;
		}
		 
}
