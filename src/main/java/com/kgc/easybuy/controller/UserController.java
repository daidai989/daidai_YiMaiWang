package com.kgc.easybuy.controller;

import com.kgc.easybuy.config.SendEmail;
import com.kgc.easybuy.pojo.Page;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.pojo.User;
import com.kgc.easybuy.service.UserService;
import com.kgc.easybuy.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private SendEmail sendEmail;


    @RequestMapping("userLogin")
    @ResponseBody
    public ResponseMessage login(HttpServletResponse res, User user){
        ResponseMessage message = userService.login(user);
        if (message.getCode()==200) {
            String token = (String) message.getData();
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(3600); // 设置cookie的有效期为1小时
            res.addCookie(cookie);
        }
        return message;
    }

    @RequestMapping("updatePwd")
    @ResponseBody
    public ResponseMessage updatePwd(User user){
        ResponseMessage message = userService.updatePwd(user);
        return message;
    }

    @RequestMapping("checkUserExist")
    @ResponseBody
    public ResponseMessage checkUserExist(User user){
        ResponseMessage message = userService.checkUserExist(user);
        return message;
    }
    @RequestMapping("getUser")
    @ResponseBody
    public ResponseMessage getUser(String token){
        ResponseMessage user = userService.getUser(token);
        return user;
    }

    @RequestMapping("sendEmailCode")
    @ResponseBody
    public Object sendEmailCode(String userName){
        int code = sendEmail.sendEmail(userName);
        return code;
    }
    @RequestMapping("doRegister")
    @ResponseBody
    public ResponseMessage toRegister(@RequestBody User user) {
        ResponseMessage responeseMsg = userService.register(user);
        return responeseMsg;
    }
    @RequestMapping("logout")
    @ResponseBody
    public ResponseMessage logout(String token) {
        ResponseMessage responeseMsg = userService.logOut(token);
        return responeseMsg;
    }
    @RequestMapping("updateUser")
    @ResponseBody
    public ResponseMessage updateUser(@RequestBody User user){
        ResponseMessage updateUser = userService.updateUser(user);
        return updateUser;
    }

    @RequestMapping("getUserList")
    @ResponseBody
    public ResponseMessage getUserList(User user,Page page){
        ResponseMessage userList = userService.getUserList(user,page);
        return userList;
    }
    @RequestMapping("updateUser")
    @ResponseBody
    public ResponseMessage updateUser(@RequestBody User user){
        ResponseMessage updateUser = userService.updateUser(user);
        return updateUser;
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public ResponseMessage deleteUser(int id){
        ResponseMessage updateUser = userService.deleteUser(id);
        return updateUser;
    }
    @RequestMapping("getUserById")
    @ResponseBody
    public ResponseMessage getUserById(int id) {
        ResponseMessage userById = userService.getUserById(id);
        return userById;
    }

    @RequestMapping("addUser")
    @ResponseBody
    public ResponseMessage addUser(@RequestBody User user) {
        ResponseMessage userById = userService.addUser(user);
        return userById;
    }
}
