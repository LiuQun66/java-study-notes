package XML.DOM;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MainTest {
	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, SAXException {
		File f=new File("src//Library.xml");
		get(f);
		get2(f);
		if(!f.exists()) {
			f.createNewFile();
		}
//		write(f);
	}
	//获取已经存在的xml文件中的数据
	public  static void get(File f) throws ParserConfigurationException, SAXException, IOException {
//		1.获得解析器工厂的实例：DocumentBuilderFactory.newInstance()
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
//		2.获得解析器：newDocumentBuilder()
		DocumentBuilder db=dbf.newDocumentBuilder();
//		3.用解析器解析要查看的xml文件：用parse()方法
		Document document=db.parse(f);
//		4.法一：随机访问，通过标签名来获得父节点的集合(集合为父节点集合).
		NodeList list=document.getElementsByTagName("Student");
//		遍历父节点的集合：集合的长度用getLength()方法	
		for(int i=0;i<list.getLength();i++) {
//			将集合中的每个节点元素强转为Element类型
			Element student=(Element)list.item(i);
//			获得节点的属性id:getAttribute()
			System.out.println(student.getAttribute("id"));
//			获得子节点的集合：getChildNodes()
			NodeList child=student.getChildNodes();
//			遍历子节点的集合：
			for(int j=0;j<child.getLength();j++) {
//				得到每个子节点：先不用强转
				Node n=child.item(j);
//				判断节点的类型：getNodeType(),Node.ELEMENT_NODE为元素标签
				if(n.getNodeType()==Node.ELEMENT_NODE) {
//					强转为Element类型
					Element e=(Element)n;
//					获得节点的标签名和存放的值：getTagName()、getTextContent()
					System.out.println(e.getTagName()+" "+e.getTextContent());
				}		
			}			
		}
	}
	public static void get2(File f) throws ParserConfigurationException, SAXException, IOException {
//		1.获得解析器工厂的实例：DocumentBuilderFactory.newInstance()
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
//		2.获得解析器：newDocumentBuilder()
		DocumentBuilder db=dbf.newDocumentBuilder();
//		3.用解析器解析要查看的xml文件：用parse()方法
		Document document=db.parse(f);
//		4.法二：从根节点开始遍历,获得根节点并强转为Element：getFirstChild()
		Element students=(Element)document.getFirstChild();
//		获得根节点下的子节点集合：getChildNodes()
		NodeList studentList=students.getChildNodes();
//		遍历子节点的集合
		for(int i=0;i<studentList.getLength();i++) {
			Node n=studentList.item(i);//获得子节点
			if(n.getNodeType()==Node.ELEMENT_NODE) {//判断节点类型
				Element student=(Element)n;//将节点强转为Element类型
//				获得子节点下的子节点集合
				NodeList child=student.getChildNodes();
				for(int j=0;j<child.getLength();j++) {//遍历：
					Node nd=child.item(j);//获得每个节点:
//					判断节点类型：
					if(nd.getNodeType()==Node.ELEMENT_NODE) {
						Element e=(Element)nd;//将节点强转为Element类型：
//					输出节点的标签名以及存放的值：
					System.out.println(e.getTagName()+" "+e.getTextContent());
					}
				}
			}
		}
		
	}
	

//		生成xml文件并添加数据：
	public static void write(File f) throws ParserConfigurationException, TransformerException {
//		1.创建Document(文件)内存树：
		Document d=DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
//		2.创建根节点：createElement
		Element students=d.createElement("Students");
//		3.将根节点添加到内存树上：appendChild()
		d.appendChild(students);
//		4.创建节点：createElement()
		for(int i=0;i<4;i++) {
//			创建父节点：
			Element student=d.createElement("Student");
//			创建子节点
			Element name=d.createElement("name");
			Element age=d.createElement("age");
//			为子节点赋值：setTextContent()
			name.setTextContent("张"+i);
			age.setTextContent("1"+i);
//			将子节点添加到父节点中：appendChild()
			student.appendChild(name);
			student.appendChild(age);
//			将父节点添加到根节点中：
			students.appendChild(student);
//		5.利用传输器将document内存树传送到file文件中：
//			创建传输工厂：用传输工厂的newInstance()方法创建
		TransformerFactory tff=TransformerFactory.newInstance();
//			创建传输器：用传输工厂创建
		Transformer tf=tff.newTransformer();
//			传输：需要两个参数Source类型的，Result类型的。相当于将内存树传输到文件中。
		Source xmlSource=new DOMSource(d);//将document作为传输的源文件
		Result outputTarget=new StreamResult(f);//将文件f作为传输的目标
		tf.transform(xmlSource, outputTarget);
		
			
			
		}
	}
}
