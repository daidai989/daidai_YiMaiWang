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
    public Object getOrderListByPage(String serialNumber,String currentNo,String userId) {
        int _currentPageNo = 1;
        int id=0;
        if (userId!=null&&!"".equals(userId)) {
            id = Integer.parseInt(userId);
        }
        if (currentNo!=null&&!"".equals(currentNo)) {
            _currentPageNo = Integer.parseInt(currentNo);
        }
        Page page = new Page();
        page.setPageSize(2);
        page.setCurrentPageNo(_currentPageNo);
        PageInfo<Order> newsList = orderService.getOrderListByPage(serialNumber, page,id);
        orderService.showCrossTime();
        return newsList;
    }

    @RequestMapping("getOrder_tailListByPage")
    public Object getOrder_tailListByPage(String name,String currentNo,Integer orderId) {
        int _currentPageNo = 1;

        if (currentNo!=null&&!"".equals(currentNo)) {
            _currentPageNo = Integer.parseInt(currentNo);
        }
        Page page = new Page();
        page.setPageSize(2);
        page.setCurrentPageNo(_currentPageNo);
        PageInfo<Order_detail> detailList = orderService.getOrder_tailListByPage(name, page,orderId);
        return detailList;
    }
    @RequestMapping("delOrder")
    public ResponseMessage delOrder(Integer id) {
        ResponseMessage responseMessage = orderService.delOrder(id);
        return responseMessage;
    }
    @RequestMapping("delOrder_detail")
    public ResponseMessage delOrder_detail(Integer id) {
        ResponseMessage responseMessage = orderService.delOrder_tailId(id);
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
}
