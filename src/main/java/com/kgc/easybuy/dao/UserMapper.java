package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    public User login(User user);
    public int updatePwd(User user);
    public User checkUserExist(User user);
    public int register(User user);
    public List<User> getUserList(User user);
    public int updateUser(User user);
    public int deleteUser(int id);
    public User getUserById(int id);
    public int addUser(User user);
}
