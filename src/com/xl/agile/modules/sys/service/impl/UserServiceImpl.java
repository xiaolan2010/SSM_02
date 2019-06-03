package com.xl.agile.modules.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xl.agile.modules.sys.dao.UserMapper;
import com.xl.agile.modules.sys.entity.User;
import com.xl.agile.modules.sys.service.UserService;

/**
 * 1.��Ҫ��@Service���ע���ʶ����һ��ҵ����
 * 2.��Ҫ��@Autowired����@Resource��mapper�Ľӿڶ���ע�뵽spring��
 * 3.����ʹ�õ��Ƿ�ע�����������
 * 
 * @author xiaolan
 *
 */
@Service(value="User")
public class UserServiceImpl implements UserService{
	
	@Autowired
    private UserMapper userMapper;//mapper�Ľӿڶ���ע�붼Spring��

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
