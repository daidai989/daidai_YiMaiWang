package com.kgc.easybuy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.easybuy.config.ProductEsRepository;
import com.kgc.easybuy.dao.CatMapper;
import com.kgc.easybuy.dao.OrderMapper;
import com.kgc.easybuy.dao.Order_detailMapper;
import com.kgc.easybuy.dao.ProductMapper;
import com.kgc.easybuy.pojo.*;
import com.kgc.easybuy.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author daidai
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private Order_detailMapper  order_detailMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CatMapper catMapper;

    @Autowired
    private ProductEsRepository per;


    @Override
    public PageInfo<Order> getOrderListByPage(String serialNumber, Page page,Integer userId) {

        PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize());
        List<Order> orderList = orderMapper.getOrderListByPage(serialNumber,userId);
        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public PageInfo<Order_detail> getOrder_tailListByPage(String name, Page page,Integer orderId) {

        PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize());
        List<Order_detail> orderList = order_detailMapper.getOrder_tailListByPage(name,orderId);
        PageInfo<Order_detail> pageInfo = new PageInfo<>(orderList);
        return pageInfo;
    }

    @Override
    public ResponseMessage delOrder(Integer id) {
        boolean b = orderMapper.delOrder(id);
        List<Order_detail> order_tailList = order_detailMapper.getOrder_tailList(id);
        for (Order_detail order :order_tailList){
            order_detailMapper.delOrder_tailId(order.getId());
            if (!b){
                return ResponseMessage.error("删除失败");
            }
        }
        if (b) {
            return ResponseMessage.success(b);
        }
        return ResponseMessage.error("删除失败");
    }

    @Override
    public ResponseMessage updateOrderStatusWithCancel(Integer id) {
        boolean b = orderMapper.updateOrderStatusWithCancel(id);
        Order orderById = orderMapper.getOrderById(id);
        List<Order_detail> order_tailList = order_detailMapper.getOrder_tailList(orderById.getId());
        for (Order_detail order_detail : order_tailList) {
            Integer productId = order_detail.getProductId();
            Product productById = productMapper.getProductById(productId);
            productById.setStock(productById.getStock()+order_detail.getCount());
            boolean flag = productMapper.updateStock(productId, productById.getStock());
            per.save(productById);
            if (!flag){
                return ResponseMessage.error("修改库存失败");
            }
        }
        if (b) {
            return ResponseMessage.success(b);
        }
        return ResponseMessage.error("修改状态失败");
    }

    @Override
    public void showCrossTime() {
        List<Order> orders = orderMapper.showCrossTime();
        for (Order order : orders){
            orderMapper.updateOrderStatusWithCancel(order.getId());
            List<Order_detail> order_tailList = order_detailMapper.getOrder_tailList(order.getId());
            for (Order_detail order_detail:order_tailList){
                Integer productId = order_detail.getProductId();
                Product productById = productMapper.getProductById(productId);
                productById.setStock(productById.getStock()+order_detail.getCount());
                boolean flag = productMapper.updateStock(productId, productById.getStock());
                if (!flag){
                    throw new RuntimeException("库存修改失败");
                }
            }
        }

    }

    @Override
    public ResponseMessage combineOrder(List<Order> orders) {
        boolean flag = true;
        Order secondOrder = new Order();
        double sum=0;
        for (Order order : orders) {
            if (flag){
                secondOrder.setUserId(order.getUserId());
                secondOrder.setLoginName(order.getLoginName());
                secondOrder.setUserAddress(order.getUserAddress());
                System.out.println(secondOrder);
                flag=false;
            }
             sum+= order.getCost();
            if (order.getStatus()==1){
                return ResponseMessage.error(order.getSerialNumber()+"这个订单已经支付啦");
            }
            if (order.getStatus()==2){
                return ResponseMessage.error(order.getSerialNumber()+"这个订单已经取消啦");
            }
            orderMapper.delOrder(order.getId());
        }
        secondOrder.setCost(sum);
        String uuid = UUID.randomUUID().toString();
        secondOrder.setSerialNumber(uuid);
        boolean b = orderMapper.addNewOrder(secondOrder);
        if (b){
            return ResponseMessage.success("合并成功");
        }else {
            return ResponseMessage.error("合并失败");
        }
    }

    @Override
    public ResponseMessage getAllOrder(Integer userId) {
        List<Order> orderList = orderMapper.getAllOrder(userId);
        if (orderList.size()>0){
            return ResponseMessage.success(orderList);
        }
        return ResponseMessage.error("列表无数据");
    }
    @Override
    public ResponseMessage addNewOrder(List<Cat> cats,User user) {

        if (cats.size()<1){
            return ResponseMessage.error("没有商品哦");
        }
        if (user==null){
            return ResponseMessage.error("用户未登录");
        }
        Product product = new Product();
        Order order=new Order();
        Order_detail order_detail = new Order_detail();
        order.setUserId(user.getId());
        order.setLoginName(user.getLoginName());
        order.setUserAddress(user.getAddress());
        String uuid = UUID.randomUUID().toString();
        order.setSerialNumber(uuid);
        double sum=0;
        List<Integer> ids=new ArrayList<Integer>();
        for (Cat cat : cats) {
            product.setId(cat.getProductId());
            Product productById = productMapper.getProductById(product.getId());
            if (productById.getStock()-cat.getCount()<2 ){
                return ResponseMessage.error(productById.getName()+"库存不足了哦");
            }
            productById.setStock(productById.getStock()-cat.getCount());
            per.save(productById);
            boolean flag = productMapper.updateStock(productById.getId(), productById.getStock());
            order_detail.setProductId(cat.getProductId());
            order_detail.setCount(cat.getCount());
            order_detail.setCost(cat.getPrice()*cat.getCount());
            boolean flag2 = order_detailMapper.addNewOrder_detail(order_detail);
            sum+=cat.getPrice()*cat.getCount();
            ids.add(order_detail.getId());
            boolean b = catMapper.deleteProduct(cat.getId());
        }
        order.setCost(sum);
        boolean flag1 = orderMapper.addNewOrder(order);
        boolean flag3 = order_detailMapper.updateOrderId(order.getId(),ids);
        if (flag1) {
            return ResponseMessage.success(order.getSerialNumber());
        }
        return ResponseMessage.error("添加失败");
    }
    @Override
    public ResponseMessage delOrder_tailId(Integer id){
        boolean flag1 = order_detailMapper.delOrder_tailId(id);
        if (flag1) {
            return ResponseMessage.success(flag1);
        }
        return ResponseMessage.error("删除失败");
    }
}
