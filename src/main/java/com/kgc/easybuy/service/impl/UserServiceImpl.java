package com.kgc.easybuy.service.impl;

import com.kgc.easybuy.dao.UserMapper;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.pojo.User;
import com.kgc.easybuy.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Random;

@Service

public class UserServiceImpl implements UserService {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseMessage login(User user) {
        logger.info("UserServiceImpl login user:"+user);
        User login = userMapper.login(user);
        logger.info("UserServiceImpl login login:"+login);
        if (login != null){
            return  ResponseMessage.success("登录成功",login);
        }
        return  ResponseMessage.error("登录失败");
    }

    @Override
    public ResponseMessage updatePwd(User user) {
        logger.info("UserServiceImpl updatePwd user:"+user);
        int count = userMapper.updatePwd(user);
        logger.info("UserServiceImpl updatePwd count:"+count);
        if (count > 0){
            return  ResponseMessage.success("修改成功",count);
        }
        return  ResponseMessage.error("修改失败");
    }

    @Override
    public ResponseMessage checkUserExist(User user) {
        logger.info("UserServiceImpl checkUserExist user:"+user);
        User isExist = userMapper.checkUserExist(user);
        logger.info("UserServiceImpl checkUserExist login:"+isExist);
        if (isExist != null){
            return ResponseMessage.success("用户存在",isExist);
        }
        return ResponseMessage.error("用户不存在");
    }
    @Override
    public ResponseMessage register(User user) {
        int updateRow = userMapper.register(user);
        if (updateRow>0) {
            return ResponseMessage.success();
        }else {
            return ResponseMessage.error("添加失败");
        }
    }

}
