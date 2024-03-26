package com.kgc.easybuy.service;

import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.pojo.User;

public interface UserService {
    public ResponseMessage login(User user);
    public ResponseMessage updatePwd(User user);
    public ResponseMessage checkUserExist(User user);
    public ResponseMessage register(User user);
    public ResponseMessage getUser(String token);
    public ResponseMessage logOut(String token);

    public ResponseMessage updateUser(User user);
}
