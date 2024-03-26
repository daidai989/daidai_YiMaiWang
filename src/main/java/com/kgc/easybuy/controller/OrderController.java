package com.kgc.easybuy.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.pojo.*;
import com.kgc.easybuy.service.NewsService;
import com.kgc.easybuy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author daidai
 */
@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("getOrderListByPage")
    public Object getOrderListByPage(Order order,Page page) {
        page.setPageSize(2);
        PageInfo<Order> newsList = orderService.getOrderListByPage(page,order);
        orderService.showCrossTime();
        return newsList;
    }
    @RequestMapping("delOrder")
    public ResponseMessage delOrder(Integer id) {
        ResponseMessage responseMessage = orderService.delOrder(id);
        return responseMessage;
    }

    @RequestMapping("OrderSubmit")
    public ResponseMessage OrderSubmit(@RequestBody Map<String, Object> data) {
        ResponseMessage responseMessage = new ResponseMessage<>();
        List<Map<String, String>> products = (List<Map<String, String>>) data.get("products");
        Map<String, String> user = (Map<String, String>) data.get("user");
        String productsJson = JSON.toJSONString(products);
        String userJson = JSON.toJSONString(user);

        // 使用Fastjson将JSON字符串转换为Java类对象
        List<Cat> catList = JSON.parseArray(productsJson, Cat.class);
        User userObj = JSON.parseObject(userJson, User.class);
        responseMessage = orderService.addNewOrder(catList, userObj);
        return responseMessage;
    }
    @RequestMapping("updateOrderStatusWithCancel")
    public ResponseMessage updateOrderStatusWithCancel(Integer id) {
        ResponseMessage responseMessage = orderService.updateOrderStatusWithCancel(id);
        return responseMessage;
    }
    @RequestMapping("getAllOrders")
    public ResponseMessage getAllOrders(Integer userId) {
        ResponseMessage responseMessage = orderService.getAllOrder(userId);
        return responseMessage;
    }
    @RequestMapping("combineOrder")
    public ResponseMessage combineOrder(@RequestBody List<Order> orders) {
        ResponseMessage responseMessage = orderService.combineOrder(orders);
        return responseMessage;
    }
    @RequestMapping("updateOrderInfo")
    public ResponseMessage updateOrderInfo(Order order) {
        ResponseMessage responseMessage = orderService.updateOrderInfo(order);
        return responseMessage;
    }

    @RequestMapping("getOrderList")
    public ResponseMessage getOrderList(Order order,Page page) {
        ResponseMessage responseMessage = orderService.getOrderList(order,page);
        return responseMessage;
    }

    @RequestMapping("getOrderById")
    public ResponseMessage getOrderById(Integer id) {
        ResponseMessage responseMessage = orderService.getOrderById(id);
        return responseMessage;
    }
}
