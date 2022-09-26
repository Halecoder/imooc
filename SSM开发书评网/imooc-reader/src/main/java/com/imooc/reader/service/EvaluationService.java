
package com.imooc.reader.service;

import com.imooc.reader.entity.Evaluation;

import java.util.List;

public interface EvaluationService {
    /**
     * 按图书编号查询图书的有效短评
     * @param bookId 图书编号
     * @return 短评的List
     */
    public List<Evaluation> selectByBookId(Long bookId);

}
  