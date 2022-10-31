package com.imooc.cloud.mall.practice.cartorder.service;

import com.github.pagehelper.PageInfo;
import com.imooc.cloud.mall.practice.cartorder.model.pojo.Order;
import com.imooc.cloud.mall.practice.cartorder.model.request.CreateOrderReq;
import com.imooc.cloud.mall.practice.cartorder.model.vo.OrderVO;

import java.util.List;


public interface OrderService {
    /**
     * 创建订单
     *
     * @param createOrderReq
     * @return
     */
    String create(CreateOrderReq createOrderReq);


    /**
     * 根据订单号，获取订单详情
     * @param orderNo
     * @return
     */
    OrderVO detail(String orderNo);


    /**
     * 前台的，获取某用户的订单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo listForCustomer(Integer pageNum, Integer pageSize);


    /**
     * 根据，orderNum，取消订单
     * @param orderNum
     */

    void cancel(String orderNum, Boolean isFromSystem);

    /**
     * 根据订单号，生成对应的支付二维码
     * @param orderNo
     * @return
     */
    String qrcode(String orderNo);



    /**
     * 支付订单
     * @param orderNo
     */
    void pay(String orderNo);


    /**
     * 后台的，针对管理员的，获取订单列表方法；即，获取所有用户的订单的；
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo listForAdmin(Integer pageNum, Integer pageSize);


    /**
     * 发货
     * @param orderNo
     */
    void deliver(String orderNo);


    /**
     * 完结订单
     * @param orderNo
     */
    void finish(String orderNo);

    List<Order> getUnpaidOrders();
}
