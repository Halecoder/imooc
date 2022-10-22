package com.imooc.mgallery.utils;

/**
 * 数据源类，用于讲XML文件解析为Java对象
 */
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.imooc.mgallery.entity.Painting;


public class XmlDataSource {
	//static静态关键字保证数据全局唯一
	private static List data = new ArrayList();
	private static String dataFile;
	static {
		// painting.xml文件完整物理地址
		// c:\new style\painting.xml
		// c:\new%20style\painting.xml
		dataFile = XmlDataSource.class.getResource("/src/painting.xml").getPath();
		
		URLDecoder decoder = new URLDecoder();
		try {
			decoder.decode(dataFile,"UTF-8");
			System.out.println(dataFile);
			//利用Dom4j对XML进行解析
			SAXReader reader = new SAXReader();
			//1. 获取Document文档对象
			Document document = reader.read(dataFile);
			//2.Xpath得到XML节点集合
			List<Node> nodes = document.selectNodes("/root/painting");
			
			for(Node node : nodes) {
				Element element = (Element)node;
				String id = element.attributeValue("id");
				String pname = element.elementText("pname");
				Painting painting = new Painting();
				painting.setId(Integer.parseInt(id));
				painting.setPname(pname);
				painting.setCategory(Integer.parseInt(element.elementText("category")));
				painting.setPrice(Integer.parseInt(element.elementText("price")));
				painting.setPreview(element.elementText("preview"));
				painting.setDescription(element.elementText("description"));
				data.add(painting);
//				System.out.println(id+":" + pname);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 *  获取所有油画Painting对象
	 * @return Painting List
	 */
	public static List<Painting> getRawData(){
		return data;
	}
	public static void main(String[] args) {
//		new XmlDataSource();
		List<Painting> ps = XmlDataSource.getRawData();
		System.out.println(ps);
	}
}
