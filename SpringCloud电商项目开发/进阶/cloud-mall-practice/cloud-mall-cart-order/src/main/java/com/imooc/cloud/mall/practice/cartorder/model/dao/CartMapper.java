package com.imooc.cloud.mall.practice.cartorder.model.dao;


import com.imooc.cloud.mall.practice.cartorder.model.pojo.Cart;
import com.imooc.cloud.mall.practice.cartorder.model.vo.CartVO;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);



    /**
     * 根据userId，从cart表和product表中，查询购物车数据
     * @param userId
     * @return
     */
    List<CartVO> selectList(@Param("userId") Integer userId);


    /**
     * 根据productId和userId，从cart表中，查询数据
     * @param userId
     * @param productId
     * @return
     */
    Cart selectCartByUserIdAndProductId(Integer userId, Integer productId);



    /**
     * 根据userId、ProductId、selected,更新购物车中某个商品的selected字段
     * @param userId
     * @param productId
     * @param selected
     * @return
     */
    Integer selectOrNot(@Param("userId") Integer userId,@Param("productId") Integer productId,
                        @Param("selected") Integer selected);


}