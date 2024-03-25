package com.kgc.easybuy.service;

import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.pojo.*;
import org.aspectj.weaver.ast.Or;

import java.util.List;

/**
 * @author daidai
 */
public interface OrderService {
    public ResponseMessage addNewOrder(List<Cat> cats,User user);

    public PageInfo<Order> getOrderListByPage(String title, Page page,Integer userId);

    public ResponseMessage delOrder(Integer id);
    public ResponseMessage updateOrderStatusWithCancel(Integer id);
    public void showCrossTime();


    public ResponseMessage combineOrder(List<Order> orders);
    public ResponseMessage getAllOrder(Integer userId);



}
