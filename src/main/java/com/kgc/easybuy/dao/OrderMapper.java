package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.News;
import com.kgc.easybuy.pojo.Order;
import com.kgc.easybuy.pojo.ResponseMessage;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;

import java.util.List;

/**
 * @author daidai
 */
@Mapper
public interface OrderMapper {

    public boolean addNewOrder(Order order);
    public List<Order> getOrderListByPage(Order order);
    public boolean delOrder(Integer id);
    public boolean updateOrderStatusWithCancel(Integer id);
    public Order getOrderById(Integer id);
    public boolean updateOrderStatusWithPay(String serialNumber);
    public List<Order> showCrossTime();
    public List<Order> getAllOrder(Integer userId);

    /**
     * 获取所有用户的所有订单
     * @param order
     * @return
     */
    public List<Order> getOrderList(Order order);
    public int updateOrderInfo(Order order);
}
