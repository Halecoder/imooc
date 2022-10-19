package com.imooc.course.controller;

import com.imooc.course.entity.CoursePrice;
import com.imooc.course.service.CoursePriceService;
import javax.xml.ws.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：     课程价格控制器
 */
@RestController
public class CoursePriceController {

    @Autowired
    CoursePriceService coursePriceService;

    @GetMapping("/price")
    public Integer getCoursePrice(Integer courseId) {
        CoursePrice coursePrice = coursePriceService.getCoursePrice(courseId);
        return coursePrice.getPrice();
    }
}
