package com.kgc.easybuy.dao;

import com.kgc.easybuy.pojo.Order;
import com.kgc.easybuy.pojo.Order_detail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author daidai
 */
@Mapper
public interface Order_detailMapper {

    public boolean addNewOrder_detail(Order_detail order_detail);

    public boolean updateOrderId(@Param("orderId") Integer orderId,@Param("ids") List<Integer> ids);

    public List<Order_detail> getOrder_tailList(Integer id);
}
