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
 * 1.��Ҫ�����ע���ʶ����һ��������
 * 2.��Ҫ��@Autowired����@Resource��service�Ľӿڶ���ע�뵽spring�У����¼Ӹ�ע��ͺã�
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
	     * ��ת����ӵ�ҳ��
	     * @return
	     */
	    @RequestMapping(value="userAdd")
	    public String insertPage(User user,Model model){
	    	model.addAttribute("user",user);
	        return "modules/"+"sys/userAdd";
	    }
	    
	    /**
	     * ��ת����ӵ�ҳ��
	     * @return
	     */
	    @RequestMapping(value="userEdit")
	    public String editPage(User user,Model model,int userid){
	    	user=userService.findById(userid);
	    	model.addAttribute("user",user);
	        return "modules/"+"sys/userEdit";
	    }
	    
	    /**
	     * ����û�
	     * @param user
	     * @return
	     */
	    @RequestMapping(value="insert")
	    public String insertUser(User user){
	    	userService.insertUser(user);
	        return "redirect:list";
	    }
	    
	    /**
	     * ����IDɾ���û�
	     * @param user
	     * @return
	     */
	    @RequestMapping(value="delete")
	    public String deleteUserById(int userid){
	    	userService.deleteUserById(userid);
	        return "redirect:list";
	    }
	    
	    /**
	     * ����ID�����û�
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
	     * �����û�
	     * @param user
	     * @return
	     */
	    @RequestMapping(value="update",method=RequestMethod.POST)
	    public String updateUser(User user,Model model){
	    	userService.updateUser(user);
	        return "redirect:list";
	    }

	    /**
	     * ��ѯ�����û�
	     * @param request
	     * @param pageNum  ��Nҳ
	     * @param pageSize ÿҳN����¼
	     * @param model
	     * @return
	     * ��ҳ���ã�ʹ��PageInfo���÷�
	     */
	    @RequestMapping(value="list")
	    public ModelAndView list(User user, HttpServletRequest request,
	    		@RequestParam(required=true,defaultValue="1") Integer pageNum,
	            @RequestParam(required=false,defaultValue="10") Integer pageSize,
	    		Model model){
	    	pageNum = pageNum == null?1:pageNum;
	        pageSize = pageSize == null?10:pageSize;
	    	//1.startPage(�ڼ�ҳ, ����������)
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
	        //2.����
	        PageHelper.orderBy("id desc");
	        //2.Mybatis��ѯ��ҳ����
	        List<User> list=userService.findAll(str_name,str_info);
	        //3.��PageInfo�Խ�����а�װ ����ҳʱ��ʵ�ʷ��صĽ��list������Page<E>�������ȡ����ҳ��Ϣ����Ҫǿ��ת��ΪPage<E>��
	        PageInfo<User> page=new PageInfo<User>(list);
	        
	        //����ModelAndView
	        ModelAndView modelAndView = new ModelAndView();
	        //�൱��request��setAttribute����,��jspҳ����ͨ��pageInfoȡ����
	        modelAndView.addObject("pageInfo", page);
	        //ָ����ͼ
	        modelAndView.setViewName("modules/"+"sys/userList");
	        return modelAndView;
	    }

}
