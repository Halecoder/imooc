
package com.imooc.mgallery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.mgallery.service.PaintingService;
import com.imooc.mgallery.utils.PageModel;

/**
 * Servlet implementation class PaintingController
 */
@WebServlet("/page")
public class PaintingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // 因为在Controller中，需要调用Service，所以这儿先创建PaintingService类的对象；
    private PaintingService paintingService = new PaintingService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaintingController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        // 1.接受HTTP请求的参数
        String page = request.getParameter("p");
        String rows = request.getParameter("r");
        String category = request.getParameter("c");
        if(page == null) {    // 如果前台请求的时候没有附带“p”和“r”两个参数，那么page和rows都会是空的，
            page = "1";       // 这样下面的paintingService.pagination()会报空指针异常；所以这儿判断处理一下；
        }                      // 如果page或者rows为空，就给其附一个默认值；
        if(rows == null) {
            rows = "6";
        }
        // 2.调用Service方法，得到处理结果；
        PageModel pageModel = paintingService.pagination(Integer.parseInt(page), Integer.parseInt(rows),category);
        // 3.将结果放在当前的请求属性中；
        request.setAttribute("pageModel", pageModel);  // 这儿的pageModel必须是一个标准的javaBean，这样才能被前台的JSP的el表达式等识别获取
        // 4.请求转发至对应的JSP（view视图），进行数据展现；
        // 使用请求转发，将当前的请求转发到inde.jsp上；这儿的视图放在了/WEB-INF/jsp目录下；
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }

}
    