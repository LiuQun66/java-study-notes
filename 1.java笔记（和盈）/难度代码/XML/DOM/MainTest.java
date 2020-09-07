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
	//��ȡ�Ѿ����ڵ�xml�ļ��е�����
	public  static void get(File f) throws ParserConfigurationException, SAXException, IOException {
//		1.��ý�����������ʵ����DocumentBuilderFactory.newInstance()
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
//		2.��ý�������newDocumentBuilder()
		DocumentBuilder db=dbf.newDocumentBuilder();
//		3.�ý���������Ҫ�鿴��xml�ļ�����parse()����
		Document document=db.parse(f);
//		4.��һ��������ʣ�ͨ����ǩ������ø��ڵ�ļ���(����Ϊ���ڵ㼯��).
		NodeList list=document.getElementsByTagName("Student");
//		�������ڵ�ļ��ϣ����ϵĳ�����getLength()����	
		for(int i=0;i<list.getLength();i++) {
//			�������е�ÿ���ڵ�Ԫ��ǿתΪElement����
			Element student=(Element)list.item(i);
//			��ýڵ������id:getAttribute()
			System.out.println(student.getAttribute("id"));
//			����ӽڵ�ļ��ϣ�getChildNodes()
			NodeList child=student.getChildNodes();
//			�����ӽڵ�ļ��ϣ�
			for(int j=0;j<child.getLength();j++) {
//				�õ�ÿ���ӽڵ㣺�Ȳ���ǿת
				Node n=child.item(j);
//				�жϽڵ�����ͣ�getNodeType(),Node.ELEMENT_NODEΪԪ�ر�ǩ
				if(n.getNodeType()==Node.ELEMENT_NODE) {
//					ǿתΪElement����
					Element e=(Element)n;
//					��ýڵ�ı�ǩ���ʹ�ŵ�ֵ��getTagName()��getTextContent()
					System.out.println(e.getTagName()+" "+e.getTextContent());
				}		
			}			
		}
	}
	public static void get2(File f) throws ParserConfigurationException, SAXException, IOException {
//		1.��ý�����������ʵ����DocumentBuilderFactory.newInstance()
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
//		2.��ý�������newDocumentBuilder()
		DocumentBuilder db=dbf.newDocumentBuilder();
//		3.�ý���������Ҫ�鿴��xml�ļ�����parse()����
		Document document=db.parse(f);
//		4.�������Ӹ��ڵ㿪ʼ����,��ø��ڵ㲢ǿתΪElement��getFirstChild()
		Element students=(Element)document.getFirstChild();
//		��ø��ڵ��µ��ӽڵ㼯�ϣ�getChildNodes()
		NodeList studentList=students.getChildNodes();
//		�����ӽڵ�ļ���
		for(int i=0;i<studentList.getLength();i++) {
			Node n=studentList.item(i);//����ӽڵ�
			if(n.getNodeType()==Node.ELEMENT_NODE) {//�жϽڵ�����
				Element student=(Element)n;//���ڵ�ǿתΪElement����
//				����ӽڵ��µ��ӽڵ㼯��
				NodeList child=student.getChildNodes();
				for(int j=0;j<child.getLength();j++) {//������
					Node nd=child.item(j);//���ÿ���ڵ�:
//					�жϽڵ����ͣ�
					if(nd.getNodeType()==Node.ELEMENT_NODE) {
						Element e=(Element)nd;//���ڵ�ǿתΪElement���ͣ�
//					����ڵ�ı�ǩ���Լ���ŵ�ֵ��
					System.out.println(e.getTagName()+" "+e.getTextContent());
					}
				}
			}
		}
		
	}
	

//		����xml�ļ���������ݣ�
	public static void write(File f) throws ParserConfigurationException, TransformerException {
//		1.����Document(�ļ�)�ڴ�����
		Document d=DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
//		2.�������ڵ㣺createElement
		Element students=d.createElement("Students");
//		3.�����ڵ���ӵ��ڴ����ϣ�appendChild()
		d.appendChild(students);
//		4.�����ڵ㣺createElement()
		for(int i=0;i<4;i++) {
//			�������ڵ㣺
			Element student=d.createElement("Student");
//			�����ӽڵ�
			Element name=d.createElement("name");
			Element age=d.createElement("age");
//			Ϊ�ӽڵ㸳ֵ��setTextContent()
			name.setTextContent("��"+i);
			age.setTextContent("1"+i);
//			���ӽڵ���ӵ����ڵ��У�appendChild()
			student.appendChild(name);
			student.appendChild(age);
//			�����ڵ���ӵ����ڵ��У�
			students.appendChild(student);
//		5.���ô�������document�ڴ������͵�file�ļ��У�
//			�������乤�����ô��乤����newInstance()��������
		TransformerFactory tff=TransformerFactory.newInstance();
//			�������������ô��乤������
		Transformer tf=tff.newTransformer();
//			���䣺��Ҫ��������Source���͵ģ�Result���͵ġ��൱�ڽ��ڴ������䵽�ļ��С�
		Source xmlSource=new DOMSource(d);//��document��Ϊ�����Դ�ļ�
		Result outputTarget=new StreamResult(f);//���ļ�f��Ϊ�����Ŀ��
		tf.transform(xmlSource, outputTarget);
		
			
			
		}
	}
}
