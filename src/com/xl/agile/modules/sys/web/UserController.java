package com.xl.agile.modules.sys.web;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xl.agile.modules.sys.entity.User;
import com.xl.agile.modules.sys.service.UserService;

/**
 * UserController
 * 1.需要用这个注解标识这是一个控制器
 * 2.需要用@Autowired或者@Resource把service的接口对象注入到spring中（如下加个注解就好）
 * @author xiaolan
 *
 */
@Controller
@RequestMapping(value="/sys/user")
public class UserController {

		private static final Log logger = LogFactory.getLog(UserController.class);

		@Resource
	    private UserService userService;
	    /**
	     * 跳转到添加的页面
	     * @return
	     */
	    @RequestMapping(value="userAdd")
	    public String insertPage(User user,Model model){
	    	model.addAttribute("user",user);
	        return "modules/"+"sys/userAdd";
	    }
	    
	    /**
	     * 跳转到添加的页面
	     * @return
	     */
	    @RequestMapping(value="userEdit")
	    public String editPage(User user,Model model,int userid){
	    	user=userService.findById(userid);
	    	model.addAttribute("user",user);
	        return "modules/"+"sys/userEdit";
	    }
	    
	    /**
	     * 添加用户
	     * @param user
	     * @return
	     */
	    @RequestMapping(value="insert")
	    public String insertUser(User user){
	    	userService.insertUser(user);
	        return "redirect:list";
	    }
	    
	    /**
	     * 根据ID删除用户
	     * @param user
	     * @return
	     */
	    @RequestMapping(value="delete")
	    public String deleteUserById(int userid){
	    	userService.deleteUserById(userid);
	        return "redirect:list";
	    }
	    
	    /**
	     * 根据ID查找用户
	     * @param user
	     * @return
	     */
	    @RequestMapping(value="details")
	    public String findById(HttpServletRequest request,Model model,int userid){
	        User user=userService.findById(userid);
	        model.addAttribute("user", user);
	        return "modules/"+"sys/userInfo";
	    }
	    
	    /**
	     * 更新用户
	     * @param user
	     * @return
	     */
	    @RequestMapping(value="update",method=RequestMethod.POST)
	    public String updateUser(User user,Model model){
	    	userService.updateUser(user);
	        return "redirect:list";
	    }

	    /**
	     * 查询所有用户
	     * @param request
	     * @param pageNum  第N页
	     * @param pageSize 每页N条记录
	     * @param model
	     * @return
	     * 分页采用：使用PageInfo的用法
	     */
	    @RequestMapping(value="list")
	    public ModelAndView list(User user, HttpServletRequest request,
	    		@RequestParam(required=true,defaultValue="1") Integer pageNum,
	            @RequestParam(required=false,defaultValue="10") Integer pageSize,
	    		Model model){
	    	pageNum = pageNum == null?1:pageNum;
	        pageSize = pageSize == null?10:pageSize;
	    	//1.startPage(第几页, 多少条数据)
	        PageHelper.startPage(pageNum, pageSize);
	        // where
	        String str_name="";
	        String str_info="";
	        if(StringUtils.isNoneBlank(user.getName())) {
	        	str_name=user.getName();
	        }
	        if(StringUtils.isNoneBlank(user.getInfo())) {
	        	str_info=user.getInfo();
	        }
	        //2.排序
	        PageHelper.orderBy("id desc");
	        //2.Mybatis查询分页数据
	        List<User> list=userService.findAll(str_name,str_info);
	        //3.用PageInfo对结果进行包装 （分页时，实际返回的结果list类型是Page<E>，如果想取出分页信息，需要强制转换为Page<E>）
	        PageInfo<User> page=new PageInfo<User>(list);
	        
	        //返回ModelAndView
	        ModelAndView modelAndView = new ModelAndView();
	        //相当于request的setAttribute方法,在jsp页面中通过pageInfo取数据
	        modelAndView.addObject("pageInfo", page);
	        //指定视图
	        modelAndView.setViewName("modules/"+"sys/userList");
	        return modelAndView;
	    }

}
