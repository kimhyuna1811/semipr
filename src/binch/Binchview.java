package binch;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.attribute.FileStoreAttributeView;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;


public class Binchview extends JPanel implements ActionListener {
	JTextField tfBinchNum,tfBinchTitle,tfBinchproducer,tfBinchprice;
	JComboBox comBinchMaker;
	JTextArea taBinchContent;
	
	JCheckBox cbMultiInsert;
	JTextField tfInsertCount;
	
	JButton bBinchInsert,bBinchModify,bBinchDelete;
	
	JComboBox comBinchSearch;
	JTextField tfBinchSearch;
	JTable tableBinch;
	
	BinchModel model;
	BinchTableModel tbModelBinch;
	
	JLabel picLabel;
	JButton bChooseFile;
	
	File f=null;
	String fName="";
	
	
	public Binchview() {
		addLayout();
		
		initStyle();
		
		eventProc();
		
		ConnectDB();
		
	
	}
	
	
	

	private void ConnectDB() {
		try {
			model=new BinchModel();
			System.out.println("연결성공");
		} catch (Exception e) {
			System.out.println("연결실패");
		}
		
	}




	private void eventProc() {
		cbMultiInsert.addActionListener(this);//다중입고
		bBinchDelete.addActionListener(this);
		bBinchInsert.addActionListener(this);
		bBinchModify.addActionListener(this);
		tfBinchSearch.addActionListener(this);//검색
		bChooseFile.addActionListener(this);
		
	}
	private void initStyle() {
//		tfBinchNum.setEditable(false);//비활성화
		tfInsertCount.setEditable(false);
	}




	private void addLayout() {
	tfBinchNum=new JTextField();
	tfBinchTitle=new JTextField();
	tfBinchproducer=new JTextField();
	tfBinchprice=new JTextField();
	
	String[] cbMakerStr= {"오리온","해태","롯데","농심","크라운제과"};
	comBinchMaker = new JComboBox(cbMakerStr);
	taBinchContent = new JTextArea();
	
	cbMultiInsert = new JCheckBox("다중입고");
	tfInsertCount = new JTextField("1",5);
	
	taBinchContent= new JTextArea();
	
	bBinchInsert=new JButton("입고");
	bBinchModify=new JButton("수정");
	bBinchDelete=new JButton("삭제");
	bChooseFile=new JButton("파일찾기");
	picLabel=new JLabel();
	
	tfBinchSearch=new JTextField(15);
	
	tbModelBinch = new BinchTableModel();
	tableBinch = new JTable(tbModelBinch);
	tableBinch.setModel(tbModelBinch);
	
	tableBinch.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			int row=tableBinch.getSelectedRow();
			int col=0;
			String data=(String) tableBinch.getValueAt(row, col);
			int no=Integer.parseInt(data);
			
			try {
				Binch bi=model.selectbypk(no);
				selectbypk(bi);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	
	
	
	
	JPanel p_west= new JPanel();
	p_west.setLayout(new BorderLayout());
	
	JPanel p_west_center=new JPanel();
	p_west_center.setLayout(new BorderLayout());
	
	JPanel p_west_center_north=new JPanel();
	p_west_center_north.setLayout(new GridLayout(5, 2));
	p_west_center_north.add(new JLabel("제품번호"));
	p_west_center_north.add(tfBinchNum);
	
	p_west_center_north.add(new JLabel("브랜드"));
	p_west_center_north.add(comBinchMaker);
	
	p_west_center_north.add(new JLabel("제품이름"));
	p_west_center_north.add(tfBinchTitle);
	
	p_west_center_north.add(new JLabel("생산자"));
	p_west_center_north.add(tfBinchproducer);
	
	
	p_west_center_north.add(new JLabel("제품가격"));
	p_west_center_north.add(tfBinchprice);
	
	
	JPanel p_west_center_center = new JPanel();
	p_west_center_center.setLayout(new GridLayout(2, 0));
	
	JPanel p_sull =new JPanel();
	p_sull.setLayout(new BorderLayout());
	p_sull.add(new JLabel("설명"),BorderLayout.WEST);
	p_sull.add(taBinchContent,BorderLayout.CENTER);
	p_sull.add(bChooseFile,BorderLayout.SOUTH);
	p_west_center_center.add(p_sull);
	p_west_center_center.add(picLabel); ////////////////////사진 라벨
	
	p_west_center.add(p_west_center_center,BorderLayout.CENTER);
	p_west_center.add(p_west_center_north,BorderLayout.NORTH);
	
	p_west_center.setBorder(new TitledBorder("제품정보입력"));
	
	JPanel p_west_south=new JPanel();
	p_west_south.setLayout(new GridLayout(2, 1));
	
	JPanel p_west_south_1=new JPanel();
	p_west_south_1.setLayout(new FlowLayout());
	p_west_south_1.add(cbMultiInsert);
	p_west_south_1.add(tfInsertCount);
	p_west_south_1.add(new JLabel("개"));
	p_west_south_1.setBorder(new TitledBorder("다중입력시 선택"));
	
	JPanel p_west_south_2=new JPanel();
	p_west_south_2.setLayout(new GridLayout(1, 3));
	p_west_south_2.add(bBinchInsert);
	p_west_south_2.add(bBinchModify);
	p_west_south_2.add(bBinchDelete);
	
	p_west_south.add(p_west_south_1);
	p_west_south.add(p_west_south_2);
	
	p_west.add(p_west_center,BorderLayout.CENTER);
	p_west.add(p_west_south,BorderLayout.SOUTH);
	
	JPanel p_east = new JPanel();
	p_east.setLayout(new BorderLayout());
	
	JPanel p_east_north =new JPanel();
	p_east_north.add(tfBinchSearch);
	
	p_east_north.setBorder(new TitledBorder("제품검색"));
	p_east.add(p_east_north,BorderLayout.NORTH);
	p_east.add(new JScrollPane(tableBinch),BorderLayout.CENTER);
	
	setLayout(new GridLayout(1, 2));
	add(p_west);
	add(p_east);
}
	
	class BinchTableModel extends AbstractTableModel{
		
		ArrayList data=new ArrayList();
		String[] columnNames= {"제품번호","브랜드","제품이름","생산자","제품가격"};
		@Override
		public int getColumnCount() {
	    	return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			ArrayList temp=(ArrayList) data.get(row);
			return temp.get(col);
		}
		public String getColumnName(int col) {
			return columnNames[col];
		}
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt=e.getSource();
		if (evt==bBinchInsert) {
			
			fName=System.currentTimeMillis()+f.getName();
			insertBinch(fName);
			
			fileSave(f,".//upload2",fName);
		}
		else if (evt==tfBinchSearch) {
			searchBinch();}
		else if (evt==bBinchModify) {
			modify();}
			
		else if (evt==bBinchDelete) {
			delete();
		}else if(evt==bChooseFile) {
			System.out.println("파일선택");
			JFileChooser jc=new JFileChooser();
			jc.showOpenDialog(this);
			f=jc.getSelectedFile();
		}
		
	}








	private void fileSave(File file, String path, String name) {
		
		try {
			File f=new File(path);
			if (!f.exists()) {
				f.mkdirs();//폴더만들기
			}
			String filePath=path+"\\"+name;
			
			FileInputStream fis=new FileInputStream(file);
			FileOutputStream fos=new FileOutputStream(filePath);
			
			int i=0;
			byte[] buffer=new byte[1024];
			
			while ((i=fis.read(buffer,0,1024))!=-1) {
				fos.write(buffer,0,i);
			}
			fis.close();
			fos.close();
			
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	}




	private void delete() {
		Binch bi=new Binch();
		bi.setBicode(Integer.parseInt(tfBinchNum.getText()));
		bi.setBinchName(tfBinchTitle.getText());
		bi.setPrice(tfBinchprice.getText());
		bi.setProducer(tfBinchproducer.getText());
		bi.setExp(taBinchContent.getText());
		bi.setMaker((String)comBinchMaker.getSelectedItem());
		
		try {
			model.deleteBinch(bi);
			JOptionPane.showMessageDialog(null, "수정완료");
			tfBinchNum.setText(null);
			tfBinchprice.setText(null);
			tfBinchproducer.setText(null);
			tfBinchTitle.setText(null);
			taBinchContent.setText(null);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "수정실패");
			e.printStackTrace();
		}
		
	}




	private void insertBinch(String fName) {
		Binch bi=new Binch();
		bi.setBicode(Integer.parseInt(tfBinchNum.getText()));
		bi.setMaker((String)comBinchMaker.getSelectedItem());
		bi.setBinchName(tfBinchTitle.getText());
		bi.setExp(taBinchContent.getText());
		bi.setPrice(tfBinchprice.getText());
		bi.setProducer(tfBinchproducer.getText());
		
		bi.setImgfname(fName);
		int count=Integer.parseInt(tfInsertCount.getText());
		
		try {
			System.out.println(bi);
			
			model.insertBinch(bi);
			JOptionPane.showMessageDialog(null, "입고완료");
			
			tfBinchNum.setText(null);
			tfBinchTitle.setText(null);
			tfBinchprice.setText(null);
			tfBinchproducer.setText(null);
			taBinchContent.setText(null);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "입고실패");
			e.printStackTrace();
		}
		
		
	}
	private void searchBinch() {
		String str=tfBinchSearch.getText();
		
		try {
			ArrayList data=model.searchBinch(str);
			tbModelBinch.data=data;
			tableBinch.setModel(tbModelBinch);
			tbModelBinch.fireTableDataChanged();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	ImageIcon icon;
	
	
	private void modify() {
		Binch bi=new Binch();
		bi.setBicode(Integer.parseInt(tfBinchNum.getText()));
		
		bi.setBinchName(tfBinchTitle.getText());
		bi.setPrice(tfBinchprice.getText());
		bi.setProducer(tfBinchproducer.getText());
		bi.setExp(taBinchContent.getText());
		bi.setMaker((String)comBinchMaker.getSelectedItem());
		
		
		try {
			model.modiftBinch(bi);
			JOptionPane.showMessageDialog(null, "수정완료");
			tfBinchNum.setText(null);
			tfBinchprice.setText(null);
			tfBinchproducer.setText(null);
			tfBinchTitle.setText(null);
			taBinchContent.setText(null);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "수정실패");
			e.printStackTrace();
		}
		
	}
	void selectbypk(Binch bi) {
		tfBinchNum.setText(String.valueOf(bi.getBicode()));
		comBinchMaker.setSelectedItem(bi.getMaker());
		tfBinchTitle.setText(bi.getBinchName());
		tfBinchproducer.setText(bi.getProducer());
		tfBinchprice.setText(bi.getPrice());
		taBinchContent.setText(bi.getExp());
		
		
		
		System.out.println(bi.getImgfname());
		icon=new ImageIcon(".\\upload2\\"+bi.getImgfname());
		ImageIcon newIcon;
		Image image=icon.getImage();
		image.getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),0);
		int imgW=picLabel.getWidth();
		int imgH=picLabel.getHeight();
		Image img=icon.getImage();
		Image newimg=img.getScaledInstance(imgW, imgH,java.awt.Image.SCALE_SMOOTH);
		newIcon=new ImageIcon(newimg);
		picLabel.setIcon(newIcon);
		
	}




}
