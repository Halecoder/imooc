
package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.entity.Member;
import com.imooc.reader.entity.MemberReadState;
import com.imooc.reader.mapper.EvaluationMapper;
import com.imooc.reader.mapper.MemberMapper;
import com.imooc.reader.mapper.MemberReadStateMapper;
import com.imooc.reader.service.MemberService;
import com.imooc.reader.service.exception.BussinessException;
import com.imooc.reader.utils.MD5Utils;
import javafx.scene.control.TextArea;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("memberService")
@Transactional
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private MemberReadStateMapper memberReadStateMapper;
    @Resource
    private EvaluationMapper evaluationMapper;

    /**
     * 会员注册，创建新会员
     *
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return
     */
    public Member createMember(String username, String password, String nickname) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>();
        queryWrapper.eq("username", username);
        List<Member> memberList = memberMapper.selectList(queryWrapper);
        if (memberList.size() > 0) {
            throw new BussinessException("M01", "用户名已存在");
        }
        Member member = new Member();
        member.setUsername(username);
        member.setNickname(nickname);
        int salt = new Random().nextInt(1000) + 1000;
        String md5 = MD5Utils.md5Digest(password, salt);
        member.setPassword(md5);
        member.setSalt(salt);
        member.setCreateTime(new Date());
        memberMapper.insert(member);
        return member;
    }



    /**
     * 登陆检查
     * @param username 用户名
     * @param password 密码
     * @return 登陆对象
     */
    public Member checkLogin (String username,String password){
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>();
        queryWrapper.eq("username",username);
        //用户名在表中全局唯一，所以使用selectOne
        Member member = memberMapper.selectOne(queryWrapper);
        if(member == null){
            throw new BussinessException("M02","用户名不存在");

        }
        if(!MD5Utils.md5Digest(password,member.getSalt()).equals(member.getPassword())){
            throw new BussinessException("M03","输入密码错误");
        }
        //上面都没问题
        return member;
    }

    /**
     * 获取阅读状态
     * @param memberId 会员id
     * @param bookId 图书id
     * @return 阅读状态对象
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public MemberReadState selectMemberReadState(Long memberId, Long bookId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("member_id",memberId);
        queryWrapper.eq("book_id",bookId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(queryWrapper);
        return memberReadState;

    }



    /**
     * 更新阅读状态
     *
     * @param memberId  会员id
     * @param bookId    图书id
     * @param readState 阅读状态
     * @return
     */
    public MemberReadState updateMemberReadState(Long memberId, Long bookId, Integer readState) {
        //首先，要查询；当前用户针对这本书的阅读状态
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("member_id", memberId);
        queryWrapper.eq("book_id", bookId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(queryWrapper);
        // 如果这个会员针对这本书，没有阅读状态：那么我们需要做的是新建一条阅读状态，保存到member_read_state表；
        if (memberReadState == null) {
            memberReadState = new MemberReadState();
            memberReadState.setBookId(bookId);
            memberReadState.setMemberId(memberId);
            memberReadState.setReadState(readState);
            memberReadState.setCreateTime(new Date());
            memberReadStateMapper.insert(memberReadState);
        } else {        // 如果这个会员针对这本书，已经有了阅读状态：那么我们需要做的是更新这条阅读状态；
            memberReadState.setReadState(readState);
            memberReadStateMapper.updateById(memberReadState);
        }
        return memberReadState;
    }



    /**
     * 发布新的短评
     *
     * @param memberId 用户id
     * @param bookId   图书id
     * @param score    评分
     * @param content  短评内容
     * @return 短评对象
     */
    public Evaluation evaluate(Long memberId, Long bookId, Integer score, String content) {
        Evaluation evaluation = new Evaluation();
        evaluation.setBookId(bookId);
        evaluation.setMemberId(memberId);
        evaluation.setScore(score);
        evaluation.setContent(content);
        evaluation.setCreateTime(new Date());
        evaluation.setState("enable");//设置审核状态，默认为enable
        evaluation.setEnjoy(0);//设置初始点赞数量
        evaluationMapper.insert(evaluation);
        return evaluation;
    }

    /**
     * 给短评点赞
     *
     * @param evaluationId 短评编号
     * @return 短评对象
     */
    public Evaluation enjoy(Long evaluationId){
        Evaluation evaluation = evaluationMapper.selectById(evaluationId);
        evaluation.setEnjoy(evaluation.getEnjoy() + 1);
        evaluationMapper.updateById(evaluation);
        return evaluation;
    }



}