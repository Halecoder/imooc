
package com.imooc.mgallery.service;

import java.util.List;

import com.imooc.mgallery.dao.PaintingDao;
import com.imooc.mgallery.entity.Painting;
import com.imooc.mgallery.utils.PageModel;

/**
 * 这个类主要职责：完成的程序业务逻辑；
 * 涉及到与底层数据交互的工作，交给Dao类去实现
 * @author dell
 *
 */
public class PaintingService {
    private PaintingDao paintingDao = new PaintingDao();
    /**
     * 调用PaintingDao类的pagination()方法，获得分页数据；
     * 这个类的内容看似和PageModel类的内容雷同，但是这个类还是必须的，在实际的开发中，需要遵从MVC原
     * 则的按层逐级调用的规范；；；所以，即便没有其他的业务逻辑，我们也要写一个Service，然后让这个Service去调用Dao；
     * @param page 当前第几页
     * @param rows 每页有几条数据
     * @return 分页对象
     */
    public PageModel pagination(int page,int rows,String...category) {
        if(rows == 0) {   // 可以看到，Service类中不但需要调用Dao来进行数据访问；
            // Service类还包括：一些前置条件的检查，以及得到调用结果后的后置数据的处理，这些工作都是与底层数据无关的
            // Service类中的方法用于处理完整的业务逻辑，Service类中方法需要尽量写的完整；
            // 而Dao中的方法只与底层数据进行交互的；；；
            // 所以，在这个例子中，即使Service类中的方法和Dao中的内容基本相同，也必须要要写这个Service类；
            throw new RuntimeException("无效的rows参数");
        }
        if((category.length==0)||(category[0]==null)) {   // 如果没有传递可选参数category的时候；
            return paintingDao.pagination(page, rows);
        }else {
            return paintingDao.pagination(Integer.parseInt(category[0]),page, rows);
        }

    }

    /**
     * 新增油画数据
     * 因为这儿逻辑比较简单，所以没有进行前置或者后置处理
     * @param painting
     */
    public void create(Painting painting) {
        paintingDao.create(painting);
    }


    /**
     * 按编号查询油画
     * @param id 油画编号
     * @return 油画对象
     */
    public Painting findById(Integer id) {
        Painting p = paintingDao.findById(id);
        if(p==null) {
            throw new RuntimeException("[id=" + id +"]油画不存在");
        }
        return p;
    }


    /**
     * 更新业务逻辑
     * @param newPainting 新的油画数据
     * @param isPreviewModified 是否修改Preview属性
     */
    public void update(Painting newPainting,Integer isPreviewModified) {
        //createtime:
        //在原始数据基础上覆盖更新
        Painting oldPainting = this.findById(newPainting.getId());
        oldPainting.setPname(newPainting.getPname());
        oldPainting.setCategory(newPainting.getCategory());
        oldPainting.setPrice(newPainting.getPrice());
        oldPainting.setDescription(newPainting.getDescription());
        if(isPreviewModified == 1) {
            oldPainting.setPreview(newPainting.getPreview());
        }
        paintingDao.update(oldPainting);
    }



    /**
     * 删除有有油画数据
     * @param id
     */
    public void delete(Integer id) {
        paintingDao.delete(id);
    }


}

