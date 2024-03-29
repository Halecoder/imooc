package com.imooc.cloud.mall.practice.categoryproduct.model.dao;


import com.imooc.cloud.mall.practice.categoryproduct.model.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);


    /**
     * 根据【分类目录名】查询分类
     * @param name
     * @return
     */
    Category selectByName(String name);


    /**
     * 查询所有目录分页
     * @return
     */
    List<Category> selectList();


    /**
     * 根据parentId查询对应级别分类数据
     * @param parentId
     * @return
     */
    List<Category> selectCategoriesByParentId(@Param("parentId") Integer parentId);
}