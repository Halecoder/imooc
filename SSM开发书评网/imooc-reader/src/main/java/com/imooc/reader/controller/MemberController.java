
package com.imooc.reader.controller;

import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.entity.Member;
import com.imooc.reader.service.MemberService;
import com.imooc.reader.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * member会员Controller
 */
@Controller
public class MemberController {
    @Resource
    private MemberService memberService;
    /**
     * 跳转显示register.ftl注册页；
     * @return
     */
    @GetMapping("/register.html")
    public ModelAndView showRegister() {
        return new ModelAndView("/register");
    }

    /**
     * 会员注册
     * @param vc
     * @param username
     * @param password
     * @param nickname
     * @param request
     * @return
     */
    @PostMapping("/registe")
    @ResponseBody
    public Map registe(String vc, String username, String password,String nickname, HttpServletRequest request) {
        Map result = new HashMap();
        //首先从Session会话对象中，获取存储在后端的验证码
        String verifyCode = (String)request.getSession().getAttribute("kaptchaVerifyCode");

        //如果【前端没有输入验证码】或者【当前Session中没有验证码】或者【前端输入的验证码和后端的验证码，在忽略大小写后不一致】：表示验证失败；
        if (vc == null || verifyCode == null||!vc.equalsIgnoreCase(verifyCode)) {
            result.put("code", "VC01");
            result.put("msg", "验证码错误");
        } else {
            try {
                memberService.createMember(username, password,nickname);
                result.put("code", "0");
                result.put("msg", "success处理成功");
            } catch (BussinessException ex) {
                ex.printStackTrace();
                result.put("code", ex.getCode());
                result.put("msg", ex.getMsg());
            }
        }
        return result;
    }


//    跳转显示login.ftl
//    @return

    @GetMapping("login.html")
    public ModelAndView showLogin (){
        return new ModelAndView("/login");
    }

//检查登陆

    @PostMapping("check_login")
    @ResponseBody
    public Map checkLogin(String username, String password, String vc, HttpSession session) {
        Map result = new HashMap();
        //首先从Session会话对象中，获取存储在后端的验证码
        String verifyCode = (String)session.getAttribute("kaptchaVerifyCode");

        //如果【前端没有输入验证码】或者【当前Session中没有验证码】或者【前端输入的验证码和后端的验证码，在忽略大小写后不一致】：表示验证失败；
        if (vc == null || verifyCode == null||!vc.equalsIgnoreCase(verifyCode)) {
            result.put("code", "VC01");
            result.put("msg", "验证码错误");
        } else {
            try {
                Member member = memberService.checkLogin(username,password);
                //保存登陆信息
                session.setAttribute("loginMember",member);
                result.put("code", "0");
                result.put("msg", "success处理成功");
            } catch (BussinessException ex) {
                ex.printStackTrace();
                result.put("code", ex.getCode());
                result.put("msg", ex.getMsg());
            }
        }
        return result;
    }


    /**
     * 更新阅读状态
     * @param memberId 会员id
     * @param bookId 图书id
     * @param readState 阅读状态
     * @return
     */
    @PostMapping("/update_read_state")
    @ResponseBody
    public Map updateReadState(Long memberId, Long bookId, Integer readState) {
        Map result = new HashMap();
        try {
            memberService.updateMemberReadState(memberId, bookId, readState);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BussinessException ex) {
            ex.printStackTrace();
            result.put("code", ex.getCode());
            result.put("msg", ex.getMsg());
        }
        return result;
    }



    /**
     * 发布新的短评
     * @param memberId 用户id
     * @param bookId 图书id
     * @param score 评分
     * @param content 短评内容
     * @return
     */
    @PostMapping("/evaluate")
    @ResponseBody
    public Map evaluate(Long memberId, Long bookId, Integer score, String content) {
        Map result = new HashMap();
        try {
            memberService.evaluate(memberId, bookId, score, content);
            result.put("code", "0");
            result.put("msg", "success");
        } catch (BussinessException ex) {
            ex.printStackTrace();
            result.put("code", ex.getCode());
            result.put("msg", ex.getMsg());
        }
        return result;
    }


    /**
     * 给短评点赞
     * @param evaluationId 用户id
     *
     * @return
     */
    @PostMapping("/enjoy")
    @ResponseBody
    public Map enjoy(Long evaluationId) {
        Map result = new HashMap();
        try {
            Evaluation evaluation = memberService.enjoy(evaluationId);
            result.put("code", "0");
            result.put("msg", "success");
            result.put("evaluation",evaluation);
        } catch (BussinessException ex) {
            ex.printStackTrace();
            result.put("code", ex.getCode());
            result.put("msg", ex.getMsg());
        }
        return result;
    }
}







