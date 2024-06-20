package com.ispan.eeit.controller;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ispan.eeit.model.Employee;
import com.ispan.eeit.service.EmployeeService;
import com.ispan.eeit.utils.SystemService;

@Controller
public class SetInitialEmployeesController extends AbstractController{
    
	Logger log = LoggerFactory.getLogger(SetInitialEmployeesController.class);
	
	EmployeeService  employeeService;
	ServletContext  servletContext;
	// 初始測試資料
	Object[][] emps = {
		      {null, "sg001", "吳一男", "1960-5-1", "enan_wu@SquidGame.com", "/images/001.jpg"},  
		      {null, "sg067", "姜曉",   "1985-7-2",  "Jiang_Xiao@outlook.com", "/images/067.jpg"},   
		      {null, "sg101", "張德秀", "1970-10-12", "Dexiu_chang1975@gmail.com", "/images/101.jpg"},  
		      {null, "sg212", "韓美女", "1976-11-2", "korean_beauty1984@outlook.com", "/images/212.jpg"},  
		      {null, "sg218", "曹尚佑", "1985-4-23","CaoShangyou1984@SquidGame.com", "/images/218.jpg"},  
		      {null, "sg456", "成奇勳", "1982-6-8","ChengQixun1980@gmail.com", "/images/456.jpg"}
		};
	
//	@Autowired
    public SetInitialEmployeesController(EmployeeService employeeService, ServletContext servletContext) {
		this.employeeService = employeeService;
		this.servletContext = servletContext;
	}

	@GetMapping("/employeeInit")
	public String initEmployee() throws Exception {
		for(Object[] oa : emps) {
			String employeeId = (String)oa[1];
			String name = (String)oa[2];
			Date   birthday = Date.valueOf((String)oa[3]);
			String email = (String)oa[4];
			String filepath = (String)oa[5];
			// 利用ClassPathResource來讀取系統內的圖檔，圖檔要放在類別路徑之下
			ClassPathResource resource = new ClassPathResource(filepath);
			File file = resource.getFile();
			// 準備建立 Data URI 
			String mimeType = servletContext.getMimeType(filepath);
			String dataUri = "data:" + mimeType + ";base64," +  SystemService.fileToBase64(file);
			Clob clob = null;
			try {
				// 將Data URI置入 Clob物件內
				clob = SystemService.stringToClob(dataUri);
			} catch(SQLException e) {
				throw new ServletException(e);
			}
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			Employee employee = new Employee(null, employeeId, name, birthday, email, clob, ts);
			employeeService.save(employee);
			// 要將圖檔寫入Server端的資料夾
			File imageMainFolder = new File(SystemService.EMPLOYEE_IMAGE_FILE_FOLDER);
			String fileExt = ".txt";
			File outFile = new File(imageMainFolder, "Employee_" + employee.getId() + fileExt);
			try (
				PrintWriter pw = new PrintWriter(outFile);
			){
				pw.print(dataUri);
			}
			
		}
		log.info("/employeesdjpa, 初始資料已經成功寫入表格");
		return "redirect:/employee/queryEmployee";
	}
}
