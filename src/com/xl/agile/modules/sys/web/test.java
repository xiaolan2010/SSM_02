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
 * �û����ܲ���
 * ���Թ��ܣ���ɾ�Ĳ�
 * ����״̬����ʵ��
 * @author xiaolan
 *
 */
public class test {
    //������־����
	private static final Log logger = LogFactory.getLog(UserController.class);
	
   	static ApplicationContext ac = new ClassPathXmlApplicationContext("spring-context.xml");
	static UserService userService = ac.getBean(UserService.class);
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		//-----����ID��ѯ��Ϣ----
		//findById();    //OK
		//-----��ѯ�����û�------
		//findAll(); //OK
		//------�����Ϣ------
		//insertUser(); //OK 
		//------ɾ����Ϣ------
		//deleteUserById(6); //OK
		//------�޸ļ�¼------
		//updateUser(); //OK 
	}
	/**
	 * ����ID��ѯ
	 */
	public static void findById() {
		User user=userService.findById(5);
		if(user != null) {
			System.out.println("-------ID��5  �û���Ϣ---------");
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
	 * ����û�����
	 */
	public static void insertUser() {
		int rcount=1000;
		for(int i=0;i<=rcount;i++) {
			int num=(int) (Math.random()*2000);
			User user=new User();
			user.setName("RMB-"+num);
			user.setPwd("pwd-"+num);
			if(num/2==0) {
				user.setSex("��");
			}else {
				user.setSex("Ů");
			}
			user.setHome("BeiJing");
			user.setInfo("��������"+num);
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
     * �����û�
     * 
     */
    public static void updateUser(){
    	User user=new User();
    	user.setId(8);
		user.setName("XiaoLan");
		user.setPwd("1111111");
		user.setSex("Ů");
		user.setHome("BeiJing");
		user.setInfo("��������");
		try {
	    	userService.updateUser(user);
			logger.info("Update User info OK");
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}

    }
    
    /**
     * ����IDɾ���û�
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
     * ���������û�
     */
    public static void findAll(){
    	String name="";
    	String info="";
        List<User>list=userService.findAll(name,info);
        //System.out.println(list);
        for(User d:list) {
    		System.out.println("----ID��"+d.getId()+" �û���Ϣ-----");
        	System.out.println("ID:"+d.getId());
    		System.out.println("Name:"+d.getName());
    		System.out.println("Pwd:"+d.getPwd());
    		System.out.println("Sex:"+d.getSex());
    		System.out.println("Home:"+d.getHome());
    		System.out.println("Info:"+d.getInfo());
        }
      

        
    }


}
