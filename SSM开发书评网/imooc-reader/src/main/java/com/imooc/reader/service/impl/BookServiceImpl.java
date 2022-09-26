package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.entity.MemberReadState;
import com.imooc.reader.mapper.BookMapper;
import com.imooc.reader.mapper.EvaluationMapper;
import com.imooc.reader.mapper.MemberReadStateMapper;
import com.imooc.reader.service.BookService;
import javafx.scene.control.TextArea;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("bookService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;
    @Resource
    private MemberReadStateMapper memberReadStateMapper;
    @Resource
    private EvaluationMapper evaluationMapper;

    /**
     * 分页查询图书
     * @param categoryId:分类编号；
     * @param order:排序方式
     * @param page ：查询第几页数据；
     * @param rows ：每一页显示多少条数据；
     * @return：IPage：分页对象；
     */
    public IPage<Book> paging(Long categoryId,String order,Integer page, Integer rows) {
        Page<Book> p = new Page<Book>(page,rows);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<Book>();
        //如果，我们在前台传入了有效的分类编号（即我们点击了“前端”、“后端”、“测试”、“产品”这些超链接）
        if (categoryId != null && categoryId != -1) {
            queryWrapper.eq("category_id", categoryId);
        }
        //如果，我们在前台点击了“按热度”或者“按评分”的超链接；
        if (order != null) {
            if (order.equals("quantity")) {//如果我们点击的是“按热度”;
                //那么我们就按这个评价人数字段"evaluation_quantity"，进行降序排列；
                queryWrapper.orderByDesc("evaluation_quantity");
            } else if (order.equals("score")) {//如果我们点击的是“按评分”;
                //那么我们就按这个评价分数字段"evaluation_quantity"，进行降序排列；
                queryWrapper.orderByDesc("evaluation_score");
            }
        }
        IPage<Book> pageObject = bookMapper.selectPage(p, queryWrapper);
        return pageObject;
    }

    /**
     * 根据图书编号，查询图书对象；
     * @param bookId 图书编号；
     * @return 图书对象；
     */
    public Book selectById(Long bookId) {
        Book book = bookMapper.selectById(bookId);
        return book;
    }


    /**
     * 更新图书评分、评价数量
     */
    @Transactional
    public void updateEvaluation(){
        bookMapper.updateEvaluation();
    }


    /**
     * 创建图书(后台功能)
     * @param book
     * @return
     */
    @Transactional
    public Book createBook(Book book){
        bookMapper.insert(book);
        return book;
    }


    /**
     * 更新图书
     * @param book 新图书数据
     * @return 更新后的数据
     */
    @Transactional
    public Book updateBook(Book book){
        bookMapper.updateById(book);
        return  book;
    }




    /**
     * 删除图书（包括book表的图书信息，evaluation的评论信息，member_read_state表的阅读状态信息）
     *
     * @param bookId
     */
    @Transactional
    public void deleteBook(Long bookId) {
        //删除book表中的图书信息
        bookMapper.deleteById(bookId);
        //删除member_read_state表的阅读状态信息
        QueryWrapper<MemberReadState> mrsQueryWrapper = new QueryWrapper<MemberReadState>();
        mrsQueryWrapper.eq("book_id", bookId);
        memberReadStateMapper.delete(mrsQueryWrapper);
        //删除evaluation的评论信息
        QueryWrapper<Evaluation> evaQueryWrapper = new QueryWrapper<Evaluation>();
        evaQueryWrapper.eq("book_id", bookId);
        evaluationMapper.delete(evaQueryWrapper);

    }
}