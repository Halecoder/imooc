package com.imooc.mall.service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.request.AddCategoryReq;
import com.imooc.mall.model.request.UpdateCategoryReq;
import com.imooc.mall.model.vo.CategoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryService {
    /**
     * 增加目录分类
     * @param addCategoryReq
     */
    void add(AddCategoryReq addCategoryReq);

    /**
     * 更新目录分类
     * @param updateCategory
     */
    void update(Category updateCategory);

    /**
     * 删除目录分类
     * @param id
     */
    void delete(Integer id);

    /**
     * 查询所有目录分页数据，并包装成PageInfo对象
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    /**
     * 递归查询得到，分类目录数据(针对前台)
     * @return
     */
    List<CategoryVO> listCategoryForCustomer(Integer parentId);
}
