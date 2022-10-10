package com.imooc.mall.service;

import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.model.pojo.User;

/**
 * 描述：用户service
 */
public interface UserService {
    /**
     * 获取User信息的方法
     * @return
     */
    User getUser();

    /**
     * 注册方法
     * @param userName
     * @param password
     */
    void register(String userName,String password) throws ImoocMallException;



    /**
     * 登录方法
     * @param userName
     * @param password
     * @return
     * @throws ImoocMallException
     */
    User login(String userName, String password) throws ImoocMallException;


    /**
     * 更新个性签名
     * @param user
     * @throws ImoocMallException
     */
    void updateInformation(User user) throws ImoocMallException;


    /**
     * 判断某个用户是不是管理员用户
     * @param user
     * @return
     */
    boolean checkAdminRole(User user);
}
