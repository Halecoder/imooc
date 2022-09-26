package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;

public interface BookService {
    /**
     * 分页查询图书
     * @param categoryId:分类编号；
     * @param order:排序方式
     * @param page：查询第几页数据；
     * @param rows：每一页显示多少条数据；
     * @return：IPage：分页对象；
     */
    public IPage<Book> paging(Long categoryId,String order,Integer page,Integer rows);

    /**
     * 根据图书编号，查询图书对象；
     * @param bookId 图书编号；
     * @return 图书对象；
     */
    public Book selectById(Long bookId);



    /**
     * 更新图书评分、评价数量
     */
    public void updateEvaluation();


    /**
     * 创建图书(后台功能)
     * @param book
     * @return
     */
    public Book createBook(Book book);


    /**
     * 更新图书
     * @param book 新图书数据
     * @return 更新后的数据
     */
    public Book updateBook(Book book);


    /**
     *删除图书所有信息，包括book表里所有信息，evaluation的评论信息，member_read_state表的阅读状态
     * @param bookId
     * @return
     */
    public void deleteBook(Long bookId);
}