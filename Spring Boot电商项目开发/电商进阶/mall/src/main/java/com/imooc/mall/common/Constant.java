package com.imooc.mall.common;

import com.google.common.collect.Sets;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;


/**
 * 描述：存放变量
 */
@Component
public class Constant {

    public static  final String SALT = "hdfjwkefhkl,[cjicvo";//盐值
    public static  final String IMOOC_MALL_USER = "imooc_mall_user";
    public static final  String EMAIL_FROM = "3283152875@qq.com";
    public static final String EMAIL_SUBJECT = "您的验证码";
    public static String  FILE_UPLOAD_DIR;

    public static final String WATER_MARK_JPG = "watermark.jpg";

    public static final Integer IMAGE_SIZE = 400;

    public static final Float IMAGE_OPACITY = 0.5f;





    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir){
        FILE_UPLOAD_DIR = fileUploadDir;
    }



    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price desc","price asc");
    }

    /**
     * 商品上下架状态
     */
    public interface SaleStatus{
        int NOT_SALE = 0;//商品下架的状态
        int SALE = 1;//商品上架的状态
    }

    /**
     * 购物车中的商品，是否被选中
     */
    public interface CartIsSelected {
        int UN_CHECKED = 0;//未选中
        int CHECKED = 1;//选中
    }



    /**
     * 枚举类，来说明订单状态
     */
    public enum OrderStatusEnum {
        CANCELED(0, "用户已取消"),
        NOT_PAY(10, "未付款"),
        PAID(20,"已付款"),
        DELIVERED(30,"已发货"),
        FINISHED(40,"交易完成");

        private int code;
        private String value;

        OrderStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public static OrderStatusEnum codeOf(int code) {
            for (OrderStatusEnum orderStatusEnum:values() ) {
                if (orderStatusEnum.getCode() == code) {
                    return orderStatusEnum;
                }
            }
            throw new ImoocMallException(ImoocMallExceptionEnum.NO_ENUM);


        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


    public static final String JWT_KEY = "imooc-mall";
    public static final String JWT_TOKEN = "jwt_token";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_ROLE = "user_role";
    public static final Long EXPIRE_TIME = 60 * 1000 * 60 * 24 * 1000L;//单位是毫秒
}
