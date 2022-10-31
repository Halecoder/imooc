
package com.imooc.cloud.mall.practice.cartorder.controller;

import com.github.pagehelper.PageInfo;

import com.imooc.cloud.mall.practice.cartorder.service.OrderService;
import com.imooc.cloud.mall.practice.common.common.ApiRestResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：后台订单Controller
 */
@RestController
public class OrderAdminController {
    @Autowired
    OrderService orderService;

    @ApiOperation("后台管理员的，订单列表")
    @GetMapping("/admin/order/list")
    public ApiRestResponse listForAdmin(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        PageInfo pageInfo = orderService.listForAdmin(pageNum,  pageSize);
        return ApiRestResponse.success(pageInfo);
    }



    /**
     * 后台管理员的，订单发货
     * @param orderNo
     * @return
     */
    @ApiOperation("后台管理员的，订单发货")
    @PostMapping("/admin/order/delivered")
    public ApiRestResponse delivered(@RequestParam("orderNo") String orderNo) {
        orderService.deliver(orderNo);
        return ApiRestResponse.success();
    }


    /**
     * 前后台通用：订单完结；
     * @param orderNo
     * @return
     */
    @ApiOperation("前后台通用的：完结订单")
    @PostMapping("/order/finish")
    public ApiRestResponse finish(@RequestParam("orderNo") String orderNo) {
        orderService.finish(orderNo);
        return ApiRestResponse.success();
    }
}

  