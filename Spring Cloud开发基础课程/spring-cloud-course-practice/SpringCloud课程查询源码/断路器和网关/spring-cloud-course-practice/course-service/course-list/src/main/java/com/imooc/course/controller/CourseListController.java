package com.imooc.course.controller;

import com.imooc.course.entity.Course;
import com.imooc.course.service.CourseListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：     CourseListController课程列表Controller
 */
@RestController
public class CourseListController {

    @Autowired
    CourseListService courseListService;

    @GetMapping("/courses")
    public List<Course> courseList() {
        return courseListService.getCourseList();
    }
}
