package com.imooc.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.*;
import com.imooc.reader.service.BookService;
import com.imooc.reader.service.CategoryService;
import com.imooc.reader.service.EvaluationService;
import com.imooc.reader.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private BookService bookService;
    @Resource
    private EvaluationService evaluationService;
    @Resource
    private MemberService memberService;

    /**
     * 显示首页
     * @return
     */
    @GetMapping("/")
    public ModelAndView showIndex() {
        ModelAndView modelAndView = new ModelAndView("/index");
        List<Category> list = categoryService.selectAll();
        modelAndView.addObject("categoryList", list);
        return modelAndView;
    }

    /**
     * 图书分页查询
     * @param p:页码（显示第几页）
     * @return 分页对象
     */
    @GetMapping("books")
    @ResponseBody
    public IPage<Book> selectBook(Long categoryId, String order,Integer p) {
        if (p == null) {//容错处理，如果前端没有传页码数据p，那么就默认显示第一页
            p = 1;
        }
        IPage<Book> pageObject = bookService.paging(categoryId,order,p,10);
        return pageObject;
    }

    /**
     * 显示图书详情页
     * @return
     */
    @GetMapping("/book/{id}")
    public ModelAndView showDetail(@PathVariable("id") Long id, HttpSession session) {
        Book book = bookService.selectById(id);
        List<Evaluation> evaluationList = evaluationService.selectByBookId(id);
        ModelAndView mav = new ModelAndView("/detail");
        Member member =(Member) session.getAttribute("loginMember");
        if(member != null){
            //获取会员阅读状态
            MemberReadState memberReadState = memberService.selectMemberReadState(member.getMemberId(), id);
            mav.addObject("memberReadState",memberReadState);
        }
        mav.addObject("evaluationList",evaluationList);
        mav.addObject("book",book);
        return mav;
    }
}

  