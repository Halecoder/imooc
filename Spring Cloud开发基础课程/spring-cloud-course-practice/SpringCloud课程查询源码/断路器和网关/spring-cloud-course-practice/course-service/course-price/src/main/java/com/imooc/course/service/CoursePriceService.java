package com.imooc.course.service;

import com.imooc.course.entity.CourseAndPrice;
import com.imooc.course.entity.CoursePrice;
import java.util.List;

/**
 * 描述：     课程价格服务
 */
public interface CoursePriceService {

    CoursePrice getCoursePrice(Integer courseId);

    List<CourseAndPrice> getCoursesAndPrice();
}
