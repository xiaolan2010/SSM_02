package com.xl.agile.modules.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xl.agile.modules.sys.entity.User;

public interface UserMapper {

    public List<User> findAll(@Param(value="name") String name,@Param(value="info") String info);
    //public List<User> findAll(String name);
    public void insertUser(User user);
    public void deleteUserById(int id);
    public User findById(int id);
    public void updateUser(User user);

}
