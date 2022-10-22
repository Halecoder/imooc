

package com.imooc.mgallery.entity;

/**
 * 保存油画数据的JavaBean
 * @author dell
 *
 */
public class Painting {
    private Integer id; // 油画编号
    private String pname;  // 名称
    private Integer category;  // 分类 1-现实主义，2-抽象主义；
    private Integer price;  // 通常在油画中，油画的价格都是整数，所以这儿价格采用Integer而不是Float；
    private String preview;  // 油画图片地址
    private String description;  // 描述
    public Painting() {}
//    public Painting(Integer id, String pname, Integer category, Integer price, String preview, String description) {
//        super();
//        this.id = id;
//        this.pname = pname;
//        this.category = category;
//        this.price = price;
//        this.preview = preview;
//        this.description = description;
//    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public Integer getCategory() {
        return category;
    }
    public void setCategory(Integer category) {
        this.category = category;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getPreview() {
        return preview;
    }
    public void setPreview(String preview) {
        this.preview = preview;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
    