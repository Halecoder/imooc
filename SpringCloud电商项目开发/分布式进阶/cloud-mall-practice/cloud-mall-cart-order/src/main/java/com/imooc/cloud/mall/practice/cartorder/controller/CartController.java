package com.imooc.cloud.mall.practice.cartorder.controller;

import com.imooc.cloud.mall.practice.cartorder.filter.UserInfoFilter;
import com.imooc.cloud.mall.practice.cartorder.model.vo.CartVO;
import com.imooc.cloud.mall.practice.cartorder.service.CartService;
import com.imooc.cloud.mall.practice.common.common.ApiRestResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 描述：购物车模块的Controller
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
//    @Autowired
//    UserFeignClient userFeignClient;


    /**
     * 购物车模块：添加商品到购物车；
     * @param productId
     * @param count
     * @return
     */
    @ApiOperation("添加商品到购物车")
    @PostMapping("/add")
    public ApiRestResponse add(@RequestParam("productId") Integer productId, @RequestParam("count") Integer count) {
        List<CartVO> cartVOList =  cartService.add(UserInfoFilter.userThreadLocal.get().getId(), productId, count);
        return ApiRestResponse.success(cartVOList);
    }



    /**
     * 购物车模块：购物车列表
     * @return
     */
    @ApiOperation("购物车列表")
    @GetMapping("/list")
    public ApiRestResponse list() {
        List<CartVO> cartVOList = cartService.list(UserInfoFilter.userThreadLocal.get().getId());
        return ApiRestResponse.success(cartVOList);
    }


    /**
     * 购物车模块：更新购物车某个商品的数量
     * @param productId
     * @param count
     * @return
     */
    @ApiOperation("更新购物车某个商品的数量")
    @PostMapping("/update")
    public ApiRestResponse update(@RequestParam("productId") Integer productId, @RequestParam("count") Integer count) {
        List<CartVO> cartVOList =  cartService.update(UserInfoFilter.userThreadLocal.get().getId(), productId, count);
        return ApiRestResponse.success(cartVOList);
    }



    /**
     * 购物车模块：删除购物车的某个商品
     * @param productId
     * @return
     */
    @ApiOperation("删除购物车的某个商品")
    @PostMapping("/delete")
    public ApiRestResponse delete(@RequestParam("productId") Integer productId) {
        List<CartVO> cartVOList =  cartService.delete(UserInfoFilter.userThreadLocal.get().getId(), productId);
        return ApiRestResponse.success(cartVOList);
    }




    /**
     * 购物车模块：选中/不选中购物车的某个商品
     * @param productId
     * @param selected
     * @return
     */
    @ApiOperation("选中/不选中购物车的某个商品")
    @PostMapping("/select")
    public ApiRestResponse select(@RequestParam("productId") Integer productId,
                                  @RequestParam("selected") Integer  selected) {
        List<CartVO> cartVOList =  cartService.selectOrNot(UserInfoFilter.userThreadLocal.get().getId(), productId,  selected);
        return ApiRestResponse.success(cartVOList);
    }



    /**
     * 购物车模块：全选/全不选购物车的商品
     * @param selected
     * @return
     */
    @ApiOperation("全选/全不选购物车的商品")
    @PostMapping("/selectAll")
    public ApiRestResponse selectAll(@RequestParam("selected") Integer selected) {
        List<CartVO> cartVOList =  cartService.selectAllOrNot(UserInfoFilter.userThreadLocal.get().getId(), selected);
        return ApiRestResponse.success(cartVOList);
    }
}


