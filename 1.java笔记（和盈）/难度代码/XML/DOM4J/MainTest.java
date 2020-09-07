package XML.DOM4J;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class MainTest {
	public static void main(String[] args) throws IOException, DocumentException {
		File f=new File("src//BookShelf.xml");
		if(!f.exists()) {
			f.createNewFile();
		}
		write(f);
		get(f);
	}
//		获取已经存在的xml文件中的数据
	public static void get(File f) throws DocumentException {
//		DOM4J只能从根节点读取：
//		1.创建读取器：new SAXReader()
		SAXReader sr=new SAXReader();
//		2.读取文件,并获得其Document对象树：read()
		Document doc=sr.read(f);
//		3.获得树的根节点：getRootElement()
		Element books=doc.getRootElement();
//		4.得到根节点下的节点集合体：Element.Elements()返回的是List类的节点.
		List<Element> l=books.elements();
		for(Element book:l) {//遍历节点
//			获得book节点的id属性值
			System.out.println(book.attributeValue("id"));
//			获得节点的子节点集合：
			List<Element>list=book.elements();
			for(Element child:list) {//遍历子节点
//				得到节点的名称和内容：
			System.out.println(child.getName()+" "+child.getText());
			}
		}
		
	}

	public static void write(File f) throws IOException {
		FileWriter fw=null;
		XMLWriter xw=null;
		try {
//		1.创建document内存树：
//			采用DocumentHelper.createDocument()创建Document
			Document doc=DocumentHelper.createDocument();
//			创建节点：addElement()
			Element bookShelf=doc.addElement("BookShelf");
//			将节点设置为根节点：setRootElement()
			doc.setRootElement(bookShelf);
//			采用循环的方式向根节点中添加节点：
			for(int i=0;i<4;i++) {
//				添加节点
				Element books=bookShelf.addElement("Books");
//				给添加的节点设置属性：addAttribute()
				books.addAttribute("id", "100"+i);
//				给节点添加子节点：
				Element name=books.addElement("name");
				Element price=books.addElement("price");
//				给添加的子节点设置内容：setText()
				name.setText("围城");
				price.setText("30");
			}
//		2.用流的方式将document对象写到指定的xml文件中
//			创建一个文件写入流：new FileWriter()
			fw=new FileWriter(f);
//			美化输出格式：OutputFormat.createPrettyPrint()
			OutputFormat opf=OutputFormat.createPrettyPrint();
//			设置编码格式
			opf.setEncoding("GBK");
//			创建一个XML写入流：new XMLWriter()
			xw = new XMLWriter(fw ,opf );
//			将document对象写入xml文件中
			xw.write(doc);	
		}finally {
//		3.关闭资源
			fw.close();
			xw.close();
		}
		
	}
}
