package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.News;
import com.kgc.easybuy.pojo.Order;
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
    public List<Order> getOrderListByPage(@Param("serialNumber") String serialNumber,@Param("userId") Integer userId);
    public boolean delOrder(Integer id);
    public boolean updateOrderStatusWithCancel(Integer id);
    public Order getOrderById(Integer id);

    public boolean updateOrderStatusWithPay(String serialNumber);
    public List<Order> showCrossTime();
    public List<Order> getAllOrder(Integer userId);






}
