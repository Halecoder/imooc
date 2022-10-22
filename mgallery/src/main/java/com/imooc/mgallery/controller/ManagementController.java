

package com.imooc.mgallery.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imooc.mgallery.entity.Painting;
import com.imooc.mgallery.service.PaintingService;
import com.imooc.mgallery.utils.PageModel;

/**
 * 后台管理功能Controller；
 * 后台系统，所需要的增，删，改，查的操作，都在这一个Controller类中完成；
 * Servlet implementation class ManagementController
 */
@WebServlet("/management")
public class ManagementController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // 创建PaintingService对象；；
    // 即无论是前台系统的PaintingController，还是后台系统的ManagementController都调用PaintingService中提供的方法；
    private PaintingService paintingService = new PaintingService();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求体中的字符集编码方式；；；
        // get请求没有请求体，所以，这个语句是为doPost()方法中执行doGet(request,response)后，跳转过来的post请求来设置的；
        // 即这条代码是为doPost()来进行服务的；
        request.setCharacterEncoding("UTF-8");
        // 设置响应的字符集编码方式
        response.setContentType("text/html;charset=utf-8");

        String method = request.getParameter("method");
        if(method.equals("list")) {  // 当前台传入的method参数值为“list”的时候，代表是分页请求，调用定义的list方法；
            this.list(request,response);  // 然后，将分页处理的代码写在list方法中就可以了；
        }else if(method.equals("delete")) {  // 当method参数值为“delete”时，表示是删除请求，调用定义的delete方法；
            this.delete(request, response);
        }else if(method.equals("show_create")) {
            // method参数为“show_create”，表示是新增；调用新增的方法，跳转到create.jsp
            this.showCreatePage(request, response);
        }else if(method.equals("create")) {
            this.create(request, response);
        }else if(method.equals("show_update")) {
            // method参数为“show_update”，表示是修改；调用修改的方法，跳转到update.jsp
            this.showUpdatePage(request,response);
        }else if(method.equals("update")) {
            this.update(request, response);
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response); // doPost调用了doGet()方法，所以，把逻辑代码都写在doGet方法中就可以了；
    }

    /**
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p = request.getParameter("p");
        String r = request.getParameter("r");
        if(p==null) {
            p = "1";
        }
        if(r==null) {
            r = "6";
        }
        PageModel pageModel = paintingService.pagination(Integer.parseInt(p), Integer.parseInt(r));
        request.setAttribute("pageModel", pageModel);
        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
    }

    /**
     * 显示【新增】页面；这个方法，是一个纯粹的入口；跳转到create.jsp前端页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void showCreatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(request, response);
    }

    /**
     * 新增油画数据；； 处理来自于create.jsp提交过来的表单数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws FileUploadException
     */
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 使用FileUpload需要按照以下步骤
        // 1.初始化FileUpload组件
        FileItemFactory factory = new DiskFileItemFactory();
        /**
         * FileItemFactory ：将前端表单的数据转化为一个一个的FileItem对象；即用于数据的转换；
         * ServletFileUpload ：为FileUpload组件提供凝Java web层面上的HTTP请求解析的功能；即提供对web的支持；
         */
        ServletFileUpload sf = new ServletFileUpload(factory);
        // 2.遍历所有的FileItem
        try {
            // 对原有的request请求进行解析；这个方法执行后，就会将当前请求中每一个提交来的表单数据转化为一个一个的FileItem对象；
            // 并且方法的返回值是一个List
            // 这个会抛出“FileUploadException”，这儿建议对异常进行捕捉而不是抛出；
            // 这个“FileUploadException”异常抛出的时机是：当对form表单进行解析时候，发现当前表单的编码格式并不是【enctype="multipart/form-data"】时候，就会抛出“FileUploadException”异常
            List<FileItem> formData = sf.parseRequest(request);
            Painting painting = new Painting();
            for(FileItem fi:formData) {
                // FileItem对象的isFormField()方法可以判断：这个FileItem对象是一个普通输入项，还是一个文件上传框；
                // 如果FileItem对象是一个普通输入项，该FileItem对象调用isFormField()方法，返回true；
                // 如果FileItem对象是一个文件上传框，该FileItem对象调用isFormField()方法，返回false；
                if(fi.isFormField()) {
                    // 这个输出，只是开发阶段的测试用，后续会有其他处理方式；；；
                    switch (fi.getFieldName()) {
                        case "pname":
                            // 当请求中是一个form，而且这个form的编码方式是“multipart/form-data”时候，在doGet()方法中的【request.setCharacterEncoding("UTF-8");】会失效；；；所以这儿在获取请求中（表单的）参数值的时候，需要设置下编码方式
                            painting.setPname(fi.getString("UTF-8"));
                            break;
                        case "category":
                            painting.setCategory(Integer.parseInt(fi.getString("UTF-8")));
                            break;
                        case "price":
                            painting.setPrice(Integer.parseInt(fi.getString("UTF-8")));
                            break;
                        case "description":
                            painting.setDescription(fi.getString("UTF-8"));
                            break;
                        default:
                            break;
                    }
                    //System.out.println("普通输入项"+fi.getFieldName()+":"+fi.getString("UTF-8"));
                }else {
                    // 这个输出，只是开发阶段的测试用，后续会有其他处理方式；；；
                    System.out.println("文件上传框"+fi.getFieldName());
                    // 3.将前端上传的文件，保存到服务器某个目录中
                    // getRealPath()方法：获取Tomcat在实际运行环境中，某个对应的目录在（部署了该Tomcat）服务器上的物理地址；
                    String path = request.getServletContext().getRealPath("/upload");
                    System.out.println(path);
                    //String fileName = "test.jpg";
                    // UUID：根据计算机（实际中就是Tomcat部署的服务器）的本地特性，根据当前时间，计算机网卡的mac地址，或
                    // 者其他的，诸如此类独特的特性，生成一个全世界唯一的字符串；
                    // UUID类是Java内置的，可以直接使用；调用randomUUID()就可以得到随机字符串；
                    // 以这个随机字符串作为文件名，根本不用担心重名的问题；
                    String fileName = UUID.randomUUID().toString();
                    // 得到文件扩展名：getName()：获取文件的名称；然后substring()获取文件的扩展名
                    String suffix = fi.getName().substring(fi.getName().lastIndexOf("."));
                    fi.write(new File(path,fileName+suffix));
                    painting.setPreview("/upload/"+fileName + suffix);// 设置油画地址
                }
            }
            paintingService.create(painting);
            // 由此，后台部分，油画的新增操作已经完成了；；；；然后可以再跳转到油画列表页面，对数据进行展示
            // 使用响应重定向，调回到油画列表页面；
            // 上面【完成新增数据】和【显示列表页】，这两者并没有明显的直接关系；；；【显示列表页】仅仅是让浏览器跳转到一个全新的功能上，对于此类场景就可以使用响应重定向；
            // 如果此时，【新增完数据 】之后，不是显示列表页，而是弹出另外一个页面，进行新增数据以后的后续操作，这个操作和前面的新增数据是紧密联系的，此时就需要使用请求转发，将当前的请求转给下面的功能，继续进行操作；
            response.sendRedirect("/management?method=list");
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 显示【修改】页面；这个方法，是一个纯粹的入口；跳转到update.jsp前端页面
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void showUpdatePage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");// 首先获取前台传递过来的id号
        Painting painting = paintingService.findById(Integer.parseInt(id));  //调动Model层的方法，获取根据id查询的painting对象
        request.setAttribute("painting", painting);// 将查询得到的painting对象，设置成request对象的参数；方便前端获取
        request.getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(request, response);
    }

    /**
     * 更新油画数据
     * @param request
     * @param response
     */
    private void update(HttpServletRequest request, HttpServletResponse response) {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sf = new ServletFileUpload(factory);
        int isPriviewModified = 0; // 是否上传新文件的标识符
        Painting painting = new Painting();
        try {
            List<FileItem> formData = sf.parseRequest(request);
            for(FileItem fi:formData) {
                if(fi.isFormField()) {
                    switch (fi.getFieldName()) {
                        case "id":
                            painting.setId(Integer.parseInt(fi.getString("UTF-8")));
                            break;
                        case "pname":
                            // 当请求中是一个form，而且这个form的编码方式是“multipart/form-data”时候，在doGet()方法中的【request.setCharacterEncoding("UTF-8");】会失效；；；所以这儿在获取请求中（表单的）参数值的时候，需要设置下编码方式
                            painting.setPname(fi.getString("UTF-8"));
                            break;
                        case "category":
                            painting.setCategory(Integer.parseInt(fi.getString("UTF-8")));
                            break;
                        case "price":
                            painting.setPrice(Integer.parseInt(fi.getString("UTF-8")));
                            break;
                        case "isPreviewModified":
                            isPriviewModified = Integer.parseInt(fi.getString("UTF-8"));
                            break;
                        case "description":
                            painting.setDescription(fi.getString("UTF-8"));
                            break;
                        default:
                            break;
                    }
                }else if(isPriviewModified == 1) {// 只有当其上传了新的图片文件，formData中才会有文件项，也才会执行到这个else语句
                    String path = request.getServletContext().getRealPath("/upload");
                    String fileName = UUID.randomUUID().toString();
                    String suffix = fi.getName().substring(fi.getName().lastIndexOf("."));
                    fi.write(new File(path,fileName+suffix));
                    painting.setPreview("/upload/"+fileName + suffix);// 设置油画地址
                }
            }
            paintingService.update(painting, isPriviewModified);
            response.sendRedirect("/management?method=list");
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 删除油画数据
     * @param request
     * @param response
     * @throws IOException
     */
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            paintingService.delete(id);
            String result = "{\"result\":\"ok\"}";
            JSONObject json = JSON.parseObject(result);
            response.getWriter().println(json);
            //成功后就返回，成功的JSON字符串
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            String result = "{\"result\":"+e.getMessage()+"}";
            JSONObject json = JSON.parseObject(result);
            response.getWriter().println(json);
        }

    }
}