package com.kgc.easybuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.dao.UserMapper;
import com.kgc.easybuy.pojo.Page;
import com.kgc.easybuy.pojo.ResponseMessage;
import com.kgc.easybuy.pojo.User;
import com.kgc.easybuy.service.UserService;
import com.kgc.easybuy.util.JwtUtil;
import com.kgc.easybuy.util.Md5Util;
import com.kgc.easybuy.util.RedisUtil;
import com.kgc.easybuy.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public ResponseMessage login(User user) {
        String md5String = Md5Util.getMD5String(user.getPassword());
        user.setPassword(md5String);
        User login = userMapper.login(user);
        if (login != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", login.getId());
            claims.put("loginName", login.getLoginName());
            String token = JwtUtil.genToken(claims);
            if (token==null){
                return ResponseMessage.error("token为空");
            }
            redisUtil.setStrToRedis(token,token);
            return  ResponseMessage.success("登录成功",token);
        }
        return  ResponseMessage.error("登录失败");
    }

    @Override
    public ResponseMessage updatePwd(User user) {
        user.setPassword(Md5Util.getMD5String(user.getPassword()));
        int count = userMapper.updatePwd(user);
        if (count > 0){
            return  ResponseMessage.success("修改成功",count);
        }
        return  ResponseMessage.error("修改失败");
    }

    @Override
    public ResponseMessage checkUserExist(User user) {
        User isExist = userMapper.checkUserExist(user);
        if (isExist != null){
            return ResponseMessage.success("用户存在",isExist);
        }
        return ResponseMessage.error("用户不存在");
    }

    @Override
    public ResponseMessage register(User user) {
        user.setPassword(Md5Util.getMD5String(user.getPassword()));
        int updateRow = userMapper.register(user);
        if (updateRow>0) {
            return ResponseMessage.success();
        }else {
            return ResponseMessage.error("添加失败");
        }
    }

    @Override
    public ResponseMessage getUser(String token) {
        User user = new User();
        Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("loginName");
        user.setLoginName(username);
        User newUser = userMapper.checkUserExist(user);
        return ResponseMessage.success(newUser);
    }

    @Override
    public ResponseMessage logOut(String token) {
        ResponseMessage strByRedis = redisUtil.getStrByRedis(token);
        if (strByRedis.getData()!=null){
            redisUtil.delStrByRedis(token);
            return ResponseMessage.success("注销成功");
        } else {
            return ResponseMessage.error("注销失败，token不存在");
        }
    }

    @Override
    public ResponseMessage getUserList(User user, Page page) {
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        List<User> userList = userMapper.getUserList(user);
        PageInfo pageInfo = new PageInfo(userList);
        if (userList != null){
            return ResponseMessage.success(pageInfo);
        }
        return ResponseMessage.error("数据为空");
    }

    @Override
    public ResponseMessage updateUser(User user) {
        int count = userMapper.updateUser(user);
        if (count > 0){
            return ResponseMessage.success();
        }
        return ResponseMessage.error("修改失败！");
    }

    @Override
    public ResponseMessage deleteUser(int id) {
        int count = userMapper.deleteUser(id);
        if (count > 0){
            return ResponseMessage.success();
        }
        return ResponseMessage.error("修改失败！");
    }

    @Override
    public ResponseMessage getUserById(int id) {
        User user = userMapper.getUserById(id);
        if (user != null){
            return ResponseMessage.success(user);
        }
        return ResponseMessage.error("没用该用户！");
    }

    @Override
    public ResponseMessage addUser(User user) {
        user.setPassword(Md5Util.getMD5String(user.getPassword()));
        int count = userMapper.addUser(user);
        if (count > 0){
            return ResponseMessage.success();
        }
        return ResponseMessage.error("添加失败！");
    }
}
