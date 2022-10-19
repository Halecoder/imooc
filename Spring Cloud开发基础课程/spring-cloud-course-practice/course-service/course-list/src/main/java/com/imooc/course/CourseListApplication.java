package com.imooc.course;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.annotation.XmlSchema;

/**
 * 描述：项目启动类
 */
@SpringBootApplication
public class CourseListApplication {
    public static void main(String[] args){
        SpringApplication.run(CourseListApplication.class,args);
    }
}
