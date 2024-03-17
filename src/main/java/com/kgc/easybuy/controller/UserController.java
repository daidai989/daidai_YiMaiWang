package com.kgc.easybuy.controller;

import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.pojo.User;
import com.kgc.easybuy.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping("userLogin")
    @ResponseBody
    public Object login(HttpServletRequest request,User user){
        logger.info("UserController login user:"+user);
        ResponseMessage message = userService.login(user);
        if (message.getData() != null){
            User userTemp = (User) message.getData();
            request.setAttribute("user",userTemp);
        }
        return message;
    }

    @RequestMapping("updatePwd")
    @ResponseBody
    public Object updatePwd(User user){
        logger.info("UserController login user:"+user);
        ResponseMessage message = userService.updatePwd(user);
        logger.debug("UserController login message：" + message);
        return message;
    }

    @RequestMapping("checkUserExist")
    @ResponseBody
    public Object checkUserExist(User user){
        logger.info("UserController checkUserExist user:"+user);
        ResponseMessage message = userService.checkUserExist(user);
        logger.debug("UserController checkUserExist message：" + message);
        return message;
    }
}
