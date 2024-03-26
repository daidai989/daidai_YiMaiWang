package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public User login(User user);

    public int updatePwd(User user);

    public User checkUserExist(User user);
    public int register(User user);

    public int updateUser(User user);


}
