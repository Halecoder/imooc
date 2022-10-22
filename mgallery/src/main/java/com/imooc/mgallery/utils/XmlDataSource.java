
package com.imooc.mgallery.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.imooc.mgallery.entity.Painting;

/**
 * 数据源类，将xml文件解析为Java对象
 *
 * @author dell
 *
 */
public class XmlDataSource {
    // 为了保证在同一时间，内存中只有一份xml的Document对象，即为了保证加载的xml对象全局唯一
    // 通过static静态关键字保证数据的全局唯一性；（自然也可以通过单例模式）
    private static List<Painting> data = new ArrayList<Painting>();  // 在XmlDataSource类加载的时候，就会创建data对象，这个对象隶属于XmlDataSource类，可以保证是全局唯一；
    private static String dataFile;  // xml文件的地址；
    static {
        // 程序运行编译后，src目录下得java文件会被编译为class文件，这些class文件会被放在classes目录下，而同样处于src目录下的painting.xml文件也会放在classes目录下；
        // XmlDataSource.class.getResoure()得到classes目录的根路径，然后在classes目录的根路径下找到painting.xml，然后getPath()获得painting.xml文件的完整的物理磁盘的地址；
        dataFile = XmlDataSource.class.getResource("/painting.xml").getPath();
        //System.out.println(dataFile);
        // 如painting.xml文件的地址是：c:\new style\painting.xml;可以发现，new和style之间有一个空格，这个空格是个特殊字符；
        // datFile得到painting.xml文件地址的时候，会进行base64转换，实际dataFile的值会是：c:\new%20style\painting.xml，即空格被转化成了%20；
        // 但是如果在后续中，利用JavaIO对painting.xml文件按照“c:\new%20style\painting.xml”读取时，会提示路径找不到，因为%20不会被JavaIO解析；需要手动的将%20转换为空格；
        // URLDecoder的作用就是：将base64转回普通的字符串；
        reload();
    }

    /**
     * 读取xml文件内容到内存中，把这个功能，提炼成了一个方法
     */
    private static void reload() {
        data.clear();// 先清空
        URLDecoder decoder = new URLDecoder();
        try {
            dataFile = decoder.decode(dataFile, "UTF-8");  // 这个需要捕获“不支持的编码格式”异常
            //System.out.println(dataFile);
            SAXReader reader = new SAXReader();
            Document document = reader.read(dataFile);  // 需要捕获“DocumentException”异常
            List<Node> nodes = document.selectNodes("/root/painting");
            for(Node node:nodes) {
                Element element = (Element)node;
                // 提取数据，如何将数据转换成Java对象？通过什么载体来保存油画的数据？所以，需要开发对应的JavaBean承载油画数据；
                String id = element.attributeValue("id");
                String pname = element.elementText("pname");
                //
                Painting painting = new Painting();
                painting.setId(Integer.parseInt(id));
                painting.setPname(pname);
                // 剩余几个采用紧凑的写法
                painting.setCategory(Integer.parseInt(element.elementText("category")));
                painting.setPrice(Integer.parseInt(element.elementText("price")));
                painting.setPreview(element.elementText("preview"));
                painting.setDescription(element.elementText("description"));
                // 将对象存储到data集合中；
                data.add(painting);  // 这样以后，当XmlDataSource这个类被加载以后，data集合中就保存了完整的油画信息；

            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 为了保证外部可以访问data集合，需要给data增加一个出口；；；
     * 这儿添加一个方法，getRawData()：即获取最原始的信息；
     * @return
     */
    public static List<Painting> getRawData(){
        return data;
    }


    /**
     * 向xml中添加
     * @param painting :从前端封装好的Painting对象，一个Painting对象就是一个油画数据
     * @throws DocumentException
     */
    public static void append(Painting painting) {
        //1.读取XML文档，得到Document对象
        SAXReader reader = new SAXReader();
        Writer writer = null;
        try {
            Document document = reader.read(dataFile); // xml文件地址，上面已经定义好了，直接拿来用就可以了
            //2.创建新的painting
            Element root = document.getRootElement();  // 获取根节点
            Element p = root.addElement("painting"); // 创建一个新的节点
            // 下面，就根据原始xml的结构，来以此设置它的属性和子节点了
            //3.创建painting节点的各个子节点
            p.addAttribute("id", String.valueOf(data.size()+1));  // 直接在原有节点数的基础上加一就可以了；
            Element pname = p.addElement("pname");
            pname.setText(painting.getPname());
            p.addElement("category").setText(painting.getCategory().toString());
            p.addElement("price").setText(painting.getPrice().toString());
            p.addElement("preview").setText(painting.getPreview());
            p.addElement("description").setText(painting.getDescription());
            // 自此，就创建了一个新的painting节点，属性节点已经设置好了；；即内存中的Document对象就形成了一个全新的油画数据；
            // 接下来，需要把内存中的油画数据，写入到xml文件中
            //4.写入XML，完成追加操作
            writer = new OutputStreamWriter(new FileOutputStream(dataFile),"UTF-8");
            document.write(writer);
            //System.out.println(dataFile);   // 测试用，测试后删除
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(writer != null) {        // 这儿将writer的关闭操作，写在了finally中；
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            reload();// 将重新加载的方法，写在了finally中，这或许也是为了保证reaload()方法一定会被执行的 一个保证措施吧
        }
    }

    /**
     * 更新操作
     * @param painting
     */
    public static void update(Painting painting) {
        SAXReader reader = new SAXReader();
        Writer writer = null;
        try {
            Document document = reader.read(dataFile);
            // 利用XPath表达式去查询对应id的节点
            List<Node> nodes = document.selectNodes("/root/painting[@id="+painting.getId()+"]");
            // 如果根据传入的id没有搜索到对应点的painting
            if(nodes.size() == 0) {
                throw new RuntimeException("id="+painting.getId()+"编号油画不存在。");
            }
            Element p = (Element)nodes.get(0); // 将nodes集合中唯一的Element节点提取出来
            // p.selectSingleNode("pname")获取，某一个(大的)Painting节点的，值为“pname”的子节点；；返回值类型当然是一个Element，然后直接调用setTest()重新赋值就可以了；
            p.selectSingleNode("pname").setText(painting.getPname());
            p.selectSingleNode("category").setText(painting.getCategory().toString());
            p.selectSingleNode("price").setText(painting.getPrice().toString());
            p.selectSingleNode("preview").setText(painting.getPreview());
            p.selectSingleNode("description").setText(painting.getDescription());
            // 上面设置以后以后，在内存中就会产生一个包含了新数据的painting节点；
            writer = new OutputStreamWriter(new FileOutputStream(dataFile),"UTF-8");
            document.write(writer);//利用Document文档对象的write()方法，对xml文件进行回写，将数据更新在xml中；
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {  // 不支持的编码异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {  // 文件未发现异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            reload();  //由于xml文件发生了改变，需要调用reload方法，是的xml文件在内存中的那一份copy保持最新
        }

    }



    /**
     * 工具方法：删除某条数据
     * @param id:要删除数据的id
     */
    public static void delete(Integer id) {
        SAXReader reader = new SAXReader();
        Writer writer = null;
        try {
            Document document = reader.read(dataFile);
            List<Node> nodes = document.selectNodes("/root/painting[@id="+id+"]");
            if(nodes.size() == 0) {
                throw new RuntimeException("待删除id的油画数据不存在。");
            }
            Element p = (Element)nodes.get(0);
            p.getParent().remove(p);
            writer = new OutputStreamWriter(new FileOutputStream(dataFile),"UTF-8");
            document.write(writer);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if(writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            reload();
        }

    }

}
    