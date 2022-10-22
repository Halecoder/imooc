
package com.imooc.mgallery.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页类
 * @author dell
 *
 */
public class PageModel {
    private int page; // 页号：当前是第几页；
    private int totalPages; //总页数：一共有多少页，这个需要
    private int rows;  // 每页记录数：每页有几条数据；
    private int totalRows;  // 总记录数：原始的数据易容有多少条；totalRows/rows:就可以得到总页数了；；；
    private int pageStartRow;  // 当前页从第n行开始：当前页是从原始数据的第几行开始的；
    private int pageEndRow;   // 当前页到第n行结束：当前页的最后一条数据是原始数据的第几行；
    private boolean hasNextPage;  // 是否有下一页；
    private boolean hasPreviousPage; //是否有上一页；
    private List pageData;  // 当前页的数据

    public PageModel() {}
    /**
     * @param data:原始完整的数据；
     * @param page：要查询第几页数据；
     * @param rows：每一页有几条；
     */
    public PageModel(List data,int page,int rows) {
        this.page = page;
        this.rows = rows;
        totalRows = data.size();
        // 总页数，向上取整；Math.ceil()：作用是向上取整；Math.ceil()返回的数据类型是double，所以下面转成了int；
        // 注：Math.floor()：向下取整，返回的数据类型也是double类型;
        // Java中如8/3 = 2；而不是2.66666；；；；(rows * 1f)的作用是把除数转成浮点数，这样(totalRows/(rows * 1f))得到的就是浮点数了；
        totalPages = new Double(Math.ceil(totalRows/(rows * 1f))).intValue();

        pageStartRow = (page-1)*rows;   // 0
        pageEndRow = page*rows;  // 6

        // totalRows:20  | totalPage:4  | rows:6
        // pageEndRow = 4*6=24>20  执行subList会抛出下标越界异常；所以做个判断处理
        if(pageEndRow > totalRows) {
            pageEndRow = totalRows;
        }
        pageData = data.subList(pageStartRow, pageEndRow); // subList()截取，可以取到pageStartRow，取不到pageEndRow，即是左闭右开的；

        if(page > 1) {   // 如果当前页号大于1，存在上一页；
            hasPreviousPage = true;
        }else {
            hasPreviousPage = false;
        }
        if(page < totalPages) {  // 如果当前页号小于总页数，存在下一页；
            hasNextPage = true;
        }else {
            hasNextPage = false;
        }

    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public int getRows() {
        return rows;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public int getTotalRows() {
        return totalRows;
    }
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }
    public int getPageStartRow() {
        return pageStartRow;
    }
    public void setPageStartRow(int pageStartRow) {
        this.pageStartRow = pageStartRow;
    }
    public int getPageEndRow() {
        return pageEndRow;
    }
    public void setPageEndRow(int pageEndRow) {
        this.pageEndRow = pageEndRow;
    }
    public boolean isHasNextPage() {
        return hasNextPage;
    }
    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }
    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }
    public List getPageData() {
        return pageData;
    }
    public void setPageData(List pageData) {
        this.pageData = pageData;
    }

    //	public static void main(String[] args) {
    //		List sample = new ArrayList();
    //		for(int i=1;i<=100;i++) {
    //			sample.add(i);
    //		}
    //		PageModel pageModel = new PageModel(sample,6,8);
    //		System.out.println("当前页数据："+pageModel.getPageData());
    //		System.out.println("总页数"+pageModel.getTotalPages());
    //		System.out.println("起始行号："+pageModel.getPageStartRow());
    //		System.out.println("结束行号"+pageModel.getPageEndRow());
    //
    //	}

}