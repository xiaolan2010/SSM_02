package com.xl.agile.modules.sys.web;
import static org.hamcrest.CoreMatchers.containsString;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.xl.agile.modules.sys.entity.User;
import com.xl.agile.modules.sys.service.UserService;

/**
 * 用户功能测试
 * 测试功能：增删改查
 * 功能状态：已实测
 * @author xiaolan
 *
 */
public class test {
    //创建日志对象
	private static final Log logger = LogFactory.getLog(UserController.class);
	
   	static ApplicationContext ac = new ClassPathXmlApplicationContext("spring-context.xml");
	static UserService userService = ac.getBean(UserService.class);
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		//-----根据ID查询信息----
		//findById();    //OK
		//-----查询所有用户------
		//findAll(); //OK
		//------添加信息------
		//insertUser(); //OK 
		//------删除信息------
		//deleteUserById(6); //OK
		//------修改记录------
		//updateUser(); //OK 
	}
	/**
	 * 根据ID查询
	 */
	public static void findById() {
		User user=userService.findById(5);
		if(user != null) {
			System.out.println("-------ID：5  用户信息---------");
			System.out.println("ID:"+user.getId());
			System.out.println("Name:"+user.getName());
			System.out.println("Pwd:"+user.getPwd());
			System.out.println("Sex:"+user.getSex());
			System.out.println("Home:"+user.getHome());
			System.out.println("Info:"+user.getInfo());
			System.out.println("----------------");
		}else {
			logger.error("is NUll");
		}
	}
	
	/**
	 * 添加用户数据
	 */
	public static void insertUser() {
		int rcount=1000;
		for(int i=0;i<=rcount;i++) {
			int num=(int) (Math.random()*2000);
			User user=new User();
			user.setName("RMB-"+num);
			user.setPwd("pwd-"+num);
			if(num/2==0) {
				user.setSex("男");
			}else {
				user.setSex("女");
			}
			user.setHome("BeiJing");
			user.setInfo("我是世界"+num);
			try {
				userService.insertUser(user);
				logger.info("Insert User OK");
			}catch (Exception e) {
				// TODO: handle exception
				logger.error(e.getMessage());
			}
		}

	}
	
    /**
     * 更新用户
     * 
     */
    public static void updateUser(){
    	User user=new User();
    	user.setId(8);
		user.setName("XiaoLan");
		user.setPwd("1111111");
		user.setSex("女");
		user.setHome("BeiJing");
		user.setInfo("我是宇宙");
		try {
	    	userService.updateUser(user);
			logger.info("Update User info OK");
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}

    }
    
    /**
     * 根据ID删除用户
     */
    public static void deleteUserById(int userid){
    	try {
        	userService.deleteUserById(userid);
        	logger.info("delete " +userid+ " User info OK");
    	}catch (Exception e) {
			// TODO: handle exception
    		logger.error(userid + "User:"+e.getMessage());
		}

    }
    
    /**
     * 查找所有用户
     */
    public static void findAll(){
    	String name="";
    	String info="";
        List<User>list=userService.findAll(name,info);
        //System.out.println(list);
        for(User d:list) {
    		System.out.println("----ID："+d.getId()+" 用户信息-----");
        	System.out.println("ID:"+d.getId());
    		System.out.println("Name:"+d.getName());
    		System.out.println("Pwd:"+d.getPwd());
    		System.out.println("Sex:"+d.getSex());
    		System.out.println("Home:"+d.getHome());
    		System.out.println("Info:"+d.getInfo());
        }
      

        
    }


}
