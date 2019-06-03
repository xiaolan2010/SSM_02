package com.xl.agile.modules.sys.service;

import java.util.List;
import com.xl.agile.modules.sys.entity.User;


public  interface  UserService {

    List<User>findAll(String name,String info);
    public void insertUser(User user);
    public void deleteUserById(int userid);
    public User findById(int userid);
    public void updateUser(User user);

}
