package com.imooc.reader.task;

import com.imooc.reader.service.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ComputeTask {
    @Resource
    private BookService bookService;

    //任务调度
    @Scheduled(cron = "0 * * * * ?")
    public void updateEvaluation() {
        bookService.updateEvaluation();
        System.out.println("已经更新所有图书的评分、评价人数");
    }
}