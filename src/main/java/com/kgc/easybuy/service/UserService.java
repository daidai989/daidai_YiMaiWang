package com.kgc.easybuy.service;

import com.kgc.easybuy.pojo.Page;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.pojo.User;

import java.util.List;

public interface UserService {
    public ResponseMessage login(User user);
    public ResponseMessage updatePwd(User user);
    public ResponseMessage checkUserExist(User user);
    public ResponseMessage register(User user);
    public ResponseMessage getUser(String token);
    public ResponseMessage logOut(String token);
    public ResponseMessage getUserList(User user,Page page);
    public ResponseMessage updateUser(User user);
    public ResponseMessage deleteUser(int id);
    public ResponseMessage getUserById(int id);
    public ResponseMessage addUser(User user);
}
