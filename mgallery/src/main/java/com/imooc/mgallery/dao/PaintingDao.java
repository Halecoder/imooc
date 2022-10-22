
package com.imooc.mgallery.dao;

import java.util.ArrayList;
import java.util.List;

import com.imooc.mgallery.entity.Painting;
import com.imooc.mgallery.utils.PageModel;
import com.imooc.mgallery.utils.XmlDataSource;

/**
 * Dao类；调用PageModel类和XmlDataSource这个两个工具类；
 * 数据访问对象类，作用是获取最原始的xml数据，并且对其进行分页；
 * @author dell
 *
 */
public class PaintingDao {

    /**
     * 实现分页的方法
     * @param page 查询第几页数据；
     * @param rows 每一页显示几条
     * @return
     */
    public PageModel pagination(int page,int rows) {
        List<Painting> xmlDataSourceList = XmlDataSource.getRawData();  // 先获取xml完整的数据；
        PageModel pageModel = new PageModel(xmlDataSourceList,page,rows);
        return pageModel;
    }

    /**
     * 对pagination方法进行重载
     * @param category 类别
     * @param page  查询第几页数据
     * @param rows  每一页显示几条数据
     * @return
     */
    public PageModel pagination(int category,int page,int rows) {
        List<Painting> xmlDataSourceList = XmlDataSource.getRawData();  // 先获取xml完整的数据；
        List<Painting> categoryList = new ArrayList<Painting>();   // 保存符合类别要求的数据
        for(Painting p:xmlDataSourceList) {
            if(p.getCategory() == category) {
                categoryList.add(p);
            }
        }
        PageModel pageModel = new PageModel(categoryList,page,rows);
        return pageModel;
    }

    /**
     * 新增油画数据
     * @param painting
     */
    public void create(Painting painting) {
        XmlDataSource.append(painting);
    }

    /**
     * 工具方法
     * @param id id号
     * @return 返回一个id对象
     */
    public Painting findById(Integer id) {
        List<Painting> data = XmlDataSource.getRawData();//得到原始数据
        Painting painting = null;
        for(Painting p  :  data) { //根据id号去查，如果查到了就返回pating对象，没有就返回null
            if(p.getId() == id) {
                painting = p;
                break;
            }
        }
        return painting;
    }


    public void update(Painting painting) {

        XmlDataSource.update(painting);
    }



    /**
     * 删除方法，调用工具类XmlDataSource的delete方法，删除油画数据
     * @param id
     */
    public void delete(Integer id) {
        XmlDataSource.delete(id);
    }
}
