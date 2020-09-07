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
//		��ȡ�Ѿ����ڵ�xml�ļ��е�����
	public static void get(File f) throws DocumentException {
//		DOM4Jֻ�ܴӸ��ڵ��ȡ��
//		1.������ȡ����new SAXReader()
		SAXReader sr=new SAXReader();
//		2.��ȡ�ļ�,�������Document��������read()
		Document doc=sr.read(f);
//		3.������ĸ��ڵ㣺getRootElement()
		Element books=doc.getRootElement();
//		4.�õ����ڵ��µĽڵ㼯���壺Element.Elements()���ص���List��Ľڵ�.
		List<Element> l=books.elements();
		for(Element book:l) {//�����ڵ�
//			���book�ڵ��id����ֵ
			System.out.println(book.attributeValue("id"));
//			��ýڵ���ӽڵ㼯�ϣ�
			List<Element>list=book.elements();
			for(Element child:list) {//�����ӽڵ�
//				�õ��ڵ�����ƺ����ݣ�
			System.out.println(child.getName()+" "+child.getText());
			}
		}
		
	}

	public static void write(File f) throws IOException {
		FileWriter fw=null;
		XMLWriter xw=null;
		try {
//		1.����document�ڴ�����
//			����DocumentHelper.createDocument()����Document
			Document doc=DocumentHelper.createDocument();
//			�����ڵ㣺addElement()
			Element bookShelf=doc.addElement("BookShelf");
//			���ڵ�����Ϊ���ڵ㣺setRootElement()
			doc.setRootElement(bookShelf);
//			����ѭ���ķ�ʽ����ڵ�����ӽڵ㣺
			for(int i=0;i<4;i++) {
//				��ӽڵ�
				Element books=bookShelf.addElement("Books");
//				����ӵĽڵ��������ԣ�addAttribute()
				books.addAttribute("id", "100"+i);
//				���ڵ�����ӽڵ㣺
				Element name=books.addElement("name");
				Element price=books.addElement("price");
//				����ӵ��ӽڵ��������ݣ�setText()
				name.setText("Χ��");
				price.setText("30");
			}
//		2.�����ķ�ʽ��document����д��ָ����xml�ļ���
//			����һ���ļ�д������new FileWriter()
			fw=new FileWriter(f);
//			���������ʽ��OutputFormat.createPrettyPrint()
			OutputFormat opf=OutputFormat.createPrettyPrint();
//			���ñ����ʽ
			opf.setEncoding("GBK");
//			����һ��XMLд������new XMLWriter()
			xw = new XMLWriter(fw ,opf );
//			��document����д��xml�ļ���
			xw.write(doc);	
		}finally {
//		3.�ر���Դ
			fw.close();
			xw.close();
		}
		
	}
}
