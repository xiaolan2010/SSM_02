package com.xl.agile.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xl.agile.modules.sys.dao.UserMapper;
import com.xl.agile.modules.sys.entity.User;
import com.xl.agile.modules.sys.service.UserService;

/**
 * 1.需要用@Service这个注解标识者是一个业务类
 * 2.需要用@Autowired或者@Resource把mapper的接口对象注入到spring中
 * 3.这是使用的是非注解的事物配置
 * 
 * @author xiaolan
 *
 */
@Service(value="User")
public class UserServiceImpl implements UserService{
	
	@Autowired
    private UserMapper userMapper;//mapper的接口对象注入都Spring中

	@Override
	public List<User> findAll(String name,String info) {
		return userMapper.findAll(name,info);
	}

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);    
	}

	@Override
	public void deleteUserById(int userid) {
		 userMapper.deleteUserById(userid);
	}

	@Override
	public User findById(int userid) {
		System.out.println("UserServiceImpl ->.findById()");
		return userMapper.findById(userid);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

}
