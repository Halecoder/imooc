package com.imooc.course.client;

import com.imooc.course.entity.Course;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 描述：     课程列表的Feign客户端
 */
@FeignClient("course-list")
public interface CourseListClient {

    @GetMapping("/courses")
    List<Course> courseList();
}
