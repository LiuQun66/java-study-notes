package com.aowin.stuff.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.dom4j.DocumentException;

import com.aowin.stuff.Dao.Implements.ImplementFunctions;
import com.aowin.stuff.Model.Person;
import com.aowin.stuff.Utils.Utils;
import com.aowin.stuff.View.AddOrModifyOrSearchView;

public class PersonnelButtonListener implements ActionListener {
	private JTable table2;

	public PersonnelButtonListener(JTable table2) {
		super();
		this.table2 = table2;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel dtm = (DefaultTableModel) table2.getModel();
		AddOrModifyOrSearchView siv = new AddOrModifyOrSearchView();
		ImplementFunctions imf = new ImplementFunctions();
		String command = e.getActionCommand();
		if ("���".equals(command)) {
			/**
			 * ������ϵͳ����Ӹ�����Ϣ
			 */
			siv.addOrModifyOrSearch(command);
			siv.getConfirmButton1().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Person p=Utils.getInformationsAddToSystemAndDatabases(siv, dtm);
					if(p!=null) {
						imf.addPersonInformationsToDatabases(p);
						Utils.addSoloInformationsToSystem(p, dtm);
						siv.getFrame().setVisible(false);
					}
					
				}
			});
			siv.getCancelButton2().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					siv.getFrame().setVisible(false);
				}
			});
		} else if ("�޸�".equals(command)) {	
			int rowIndex=table2.getSelectedRow();
			if(rowIndex==-1) {
				JOptionPane.showMessageDialog(null, "����ʧ�ܣ�����ѡ��Ҫ�޸ĵ�����");
			}else {
				siv.addOrModifyOrSearch(command);
				String ids=(String)dtm.getValueAt(rowIndex, 0);
				int[] id= {Integer.parseInt(ids)};	
				siv.getNumberText1().setText(ids);
				siv.getNumberText1().setEditable(false);
				siv.getNameText2().setText(((String)dtm.getValueAt(rowIndex, 1)));
				siv.getSexComBox().setSelectedItem(dtm.getValueAt(rowIndex, 2));
				siv.getAgeText2().setText((String)dtm.getValueAt(rowIndex, 3));
				siv.getDepartComBox2().setSelectedItem(dtm.getValueAt(rowIndex, 4));
				siv.getSalaryText5().setText((String)dtm.getValueAt(rowIndex, 5));
				siv.getConfirmButton1().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int isModify = JOptionPane.showConfirmDialog(null, "ȷ���޸���");
						if(isModify==0) {
							imf.deleteManySystemAndDatabasesInformations(id);
							Person p=Utils.getInformationsAddToSystemAndDatabases(siv, dtm);
							if(p!=null) {
								dtm.removeRow(rowIndex);
								imf.addPersonInformationsToDatabases(p);
								Utils.addSoloInformationsToSystem(p, dtm);
								siv.getFrame().setVisible(false);
							}
						}
					}
					
				});
				siv.getCancelButton2().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						siv.getFrame().setVisible(false);
					}
				});
			}
		} else if ("ɾ��".equals(command)) {
			int[] rowsIndex = table2.getSelectedRows();
			int[] deleteIds = new int[rowsIndex.length];
			if (rowsIndex.length == 0) {
				JOptionPane.showMessageDialog(null, "����ʧ�ܣ�����ѡ��Ҫɾ��������");
			}else {
				int isDelete = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����");
				if (isDelete == 0) {
					for (int n = 0; n < rowsIndex.length; n++) {
						deleteIds[n] = Integer.parseInt((String) dtm.getValueAt(rowsIndex[n], 0));
					}
					imf.deleteManySystemAndDatabasesInformations(deleteIds);
					for (int x = rowsIndex.length - 1; x >= 0; x--) {
						dtm.removeRow(rowsIndex[x]);
					}
				}
			}
		} else if ("��ѯ".equals(command)) {
			siv.addOrModifyOrSearch(command);
			siv.getSearchlable6().setVisible(true);
			siv.getAndComBox3().setVisible(true);
			siv.getConfirmButton1().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ArrayList al=new ArrayList();
					al.add(siv.getNumberText1().getText());
					al.add(siv.getNameText2().getText());
					al.add( siv.getSexComBox().getSelectedItem());
					al.add(siv.getAgeText2().getText());
					al.add(siv.getDepartComBox2().getSelectedItem());
					al.add(siv.getSalaryText5().getText());
					
					ArrayList<Person> aList=imf.searchInformationsFromPersonnelSystem(al);
					dtm.setRowCount(0);
					siv.getFrame().setVisible(false);
					if(aList.size()!=0) {
						for(Person p:aList) {
							Utils.addSoloInformationsToSystem(p, dtm);
						}
					}else {
						JOptionPane.showMessageDialog(null, "ϵͳ���޴��˵���Ϣ");
					}
				}

			});
			siv.getCancelButton2().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					siv.getFrame().setVisible(false);
				}
			});

		} else if ("����".equals(command)) {
			dtm.setRowCount(0);
			imf.importInformationsToSystem(table2);
		}else if("�˳�".equals(command)){
			System.exit(0);
		}else if("����".equals(command)) {
			File ef=new File("G:\\Test\\a.xml");
			if(!ef.exists()) {
				try {
					ef.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			JFileChooser jfc=new JFileChooser();
			jfc.setCurrentDirectory(ef);
			int result=jfc.showOpenDialog(null);
			if(result==JFileChooser.APPROVE_OPTION) {
				File importFile=jfc.getSelectedFile();
				ArrayList<Person> importList=null;
				importList=Utils.importDatasToSystem(importFile);
				dtm.setRowCount(0);
				for(Person p:importList) {
					Utils.addSoloInformationsToSystem(p, dtm);
				}
				int isImport=JOptionPane.showConfirmDialog(null, "�Ƿ��뵽���ݿ���");
				if(isImport==0) {
					for(Person p:importList) {
						ArrayList idList=imf.getIdsFromDatabases();
						int re=0;
						for(int i=0;i<idList.size();i++) {
							if(p.getId()==(int)idList.get(i)) {
								re=1;
								JOptionPane.showMessageDialog(null, "���ݿ����Ѵ��ڱ��Ϊ"+p.getId()+"����Ϣ�����ܵ�������ͬ����Ϣ���������ñ�ż�������");
							}
						}
						if(re==0) {
							imf.addPersonInformationsToDatabases(p);
						}
					}
					JOptionPane.showMessageDialog(null, "�������");
				}
			}		
		}else if("����".equals(command)) {
			int[] rowsIndex = table2.getSelectedRows();
			if (rowsIndex.length == 0) {
				JOptionPane.showMessageDialog(null, "����ʧ�ܣ�����ѡ��Ҫ����������");
			} else {
				ArrayList<Person> alp=new ArrayList<Person>();
				for(int r=0;r<rowsIndex.length;r++) {
					int id=Integer.parseInt((String)dtm.getValueAt(r, 0));
					String name=(String)dtm.getValueAt(r, 1);
					int sex=0;
					String sexs=(String)dtm.getValueAt(r, 2);
					if("��".equals(sexs)) {
						sex=1;
					}
					int age=Integer.parseInt((String)dtm.getValueAt(r, 3));
					String department=(String)dtm.getValueAt(r, 4);
					int salary=Integer.parseInt((String)dtm.getValueAt(r, 5));
					Person p=new Person(id,name,sex,age,department,salary);
					alp.add(p);
				}
				File xf=new File("G:\\Test\\a.xml");
				if(!xf.exists()) {
					try {
						xf.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				JFileChooser jfc=new JFileChooser();
				jfc.setCurrentDirectory(xf);
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);//����������
				int result=jfc.showSaveDialog(null);
				if(result==JFileChooser.APPROVE_OPTION) {
					File exportFile=jfc.getSelectedFile();
					if(exportFile.length()!=0) {
						int isExport=JOptionPane.showConfirmDialog(null, "ѡ����ļ��������ݣ��������������ļ��е����ݻᱻ���ǣ��Ƿ�������������ļ�");
						if(isExport==0) {
							try {
								Utils.exportDatasByXML(alp, exportFile);
								JOptionPane.showMessageDialog(null, "�����ɹ�");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}else {
						try {
							Utils.exportDatasByXML(alp, exportFile);
							JOptionPane.showMessageDialog(null, "�����ɹ�");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
			}
		}
	}

}
