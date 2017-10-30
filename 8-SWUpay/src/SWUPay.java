import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;




public class SWUPay extends JFrame {

	Connection con = makeConnection();
	
	private JTable table1, table2, table3, table4, selectedmenu;
	DefaultTableModel tableModel1, tableModel2, tableModel3, tableModel4, selectedMenuModel;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final Object [] colNames = {"메뉴 이름", "가격", "갯수"};
	private final Object [] selectedmenu_title = {"메뉴이름","갯수","가격"};
	
	JComboBox combo = null;
	int person_num =1;	//인원수
	JLabel lblResult;	//최종결과 나오는 레이블
	JRadioButton rdbtnCount1,rdbtnCount2,rdbtnCount3,rdbtnCount4,rdbtnCount5; //라디오버튼
	JPanel tab1,tab2,tab3,tab4 ;
	
	public SWUPay() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SWUPay.class.getResource("/image/icon.jpg")));
		setTitle("SWUpay");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 516, 563);
		contentPane = new JPanel();
		contentPane.setFont(new Font("SansSerif", Font.PLAIN, 11));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableModel1 = new DefaultTableModel(null, colNames);
		tableModel2 = new DefaultTableModel(null, colNames);
		tableModel3 = new DefaultTableModel(null, colNames);
		tableModel4 = new DefaultTableModel(null, colNames);
		selectedMenuModel = new DefaultTableModel(null,selectedmenu_title);	
	
		JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.TOP);
		tabPanel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		tabPanel.setBounds(10, 22, 353, 228);
		contentPane.add(tabPanel);
		
		combo = new JComboBox();
	   	for(int i=1; i<=5; i++)
	   	{
	   		combo.addItem(i);
	   	}
	   	
		//용우동 판넬
	   	Init_menu1();
		tab1 = new JPanel();
		tab1.setFont(new Font("SansSerif", Font.PLAIN, 10));
		tabPanel.addTab("\uC6A9\uC6B0\uB3D9", null, tab1, null);
		tab1.setLayout(null);
		
		JScrollPane Menuscrollpane = new JScrollPane();
		Menuscrollpane.setBounds(10, 11, 328, 178);
		table1 = new JTable(tableModel1);
		Menuscrollpane.setViewportView(table1);
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tab1.add(Menuscrollpane);
		//콤보박스 넣기		
		table1.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(combo));
		
		
		//멕시칸치킨
		Init_menu2();
		tab2 = new JPanel();
		tab2.setFont(new Font("SansSerif", Font.PLAIN, 10));
		tabPanel.addTab("\uBA55\uC2DC\uCE78\uCE58\uD0A8", null, tab2, null);
		tab2.setLayout(null);
		
		JScrollPane scrollMenu2 = new JScrollPane();
		scrollMenu2.setBounds(10, 11, 328, 178);
		table2 = new JTable(tableModel2);
		scrollMenu2.setViewportView(table2);
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		tab2.add(scrollMenu2);
		//콤보박스 넣기		
		table2.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(combo));
				
		//김가네
		Init_menu3();
		tab3 = new JPanel();
		tab3.setFont(new Font("SansSerif", Font.PLAIN, 10));
		tabPanel.addTab("\uAE40\uAC00\uB124", null, tab3, null);
		tab3.setLayout(null);
		
		JScrollPane scrollMenu3 = new JScrollPane();
		scrollMenu3.setBounds(10, 11, 328, 178);
		table3 = new JTable(tableModel3);
		scrollMenu3.setViewportView(table3);
		table3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tab3.add(scrollMenu3);
		//콤보박스 넣기		
		table3.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(combo));
			
		//마이메뉴
		Init_mymenu();
		tab4 = new JPanel();
		tab4.setFont(new Font("SansSerif", Font.PLAIN, 10));
		tabPanel.addTab("마이메뉴", null, tab4, null);
		tab4.setLayout(null);
		
		JScrollPane scrollMenu4 = new JScrollPane();
		scrollMenu4.setBounds(10, 11, 328, 178);
		tab4.add(scrollMenu4);
		
		table4 = new JTable(tableModel4);
		scrollMenu4.setViewportView(table4);
		table4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tab4.add(scrollMenu4);
		//콤보박스 넣기		
		table4.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(combo));

		
		
		
		//버튼
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new OKAtionListener());
		btnOK.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnOK.setBounds(370, 162, 120, 54);
		contentPane.add(btnOK);
		
		JButton btnTryAgain = new JButton("try again");
		btnTryAgain.addActionListener(new TryAgainActionListener());
		btnTryAgain.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnTryAgain.setBounds(370, 107, 120, 51);
		contentPane.add(btnTryAgain);
		
		JButton btnAddMenu = new JButton("Add Menu");
		btnAddMenu.addActionListener(new AddMenuActionListener());
		btnAddMenu.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnAddMenu.setBounds(370, 79, 120, 23);
		contentPane.add(btnAddMenu);
		
		JButton btnRemoveMenu = new JButton("Remove Menu");
		btnRemoveMenu.addActionListener(new removeMenuListener());
		btnRemoveMenu.setFont(new Font("SansSerif", Font.BOLD, 11));
		btnRemoveMenu.setBounds(370, 53, 120, 23);
		contentPane.add(btnRemoveMenu);
		
		
		
		//계산 버튼		
		JButton btnSwupay = new JButton("SWUpay");
		btnSwupay.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSwupay.addActionListener(new swupayListener());
		
		btnSwupay.setBounds(157, 427, 185, 53);
		contentPane.add(btnSwupay);
				
		//라디오버튼 판넬 - 인원수 정하는 판넬
		JPanel countPanel = new JPanel();
		countPanel.setBorder(new TitledBorder(null, "\uC778\uC6D0 \uC218", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		countPanel.setBounds(16, 364, 468, 56);
		contentPane.add(countPanel);
		
		rdbtnCount1 = new JRadioButton("1\uBA85");
		rdbtnCount1.setSelected(true);
		rdbtnCount1.setFont(new Font("SansSerif", Font.PLAIN, 11));
		buttonGroup.add(rdbtnCount1);
		countPanel.add(rdbtnCount1);
		
		rdbtnCount2 = new JRadioButton("2\uBA85");
		rdbtnCount2.setFont(new Font("SansSerif", Font.PLAIN, 11));
		buttonGroup.add(rdbtnCount2);
		countPanel.add(rdbtnCount2);
		
		rdbtnCount3 = new JRadioButton("3\uBA85");
		rdbtnCount3.setFont(new Font("SansSerif", Font.PLAIN, 11));
		buttonGroup.add(rdbtnCount3);
		countPanel.add(rdbtnCount3);
		
		rdbtnCount4 = new JRadioButton("4\uBA85");
		rdbtnCount4.setFont(new Font("SansSerif", Font.PLAIN, 11));
		buttonGroup.add(rdbtnCount4);
		countPanel.add(rdbtnCount4);
		
		rdbtnCount5 = new JRadioButton("5\uBA85");
		rdbtnCount5.setFont(new Font("SansSerif", Font.PLAIN, 11));
		buttonGroup.add(rdbtnCount5);
		countPanel.add(rdbtnCount5);
		
		
		//계산결과
		lblResult = new JLabel();
		lblResult.setForeground(Color.BLACK);
		lblResult.setBackground(Color.WHITE);
		lblResult.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setBounds(20, 488, 460, 23);
		contentPane.add(lblResult);
		
		//선택메뉴보이는곳
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\uC120\uD0DD\uD55C\uBA54\uB274", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(20, 251, 460, 113);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 440, 78);
		panel.add(scrollPane);
		
		selectedmenu = new JTable(selectedMenuModel);
		selectedmenu.setFont(new Font("SansSerif", Font.PLAIN, 11));
		scrollPane.setViewportView(selectedmenu);
		
	
		this.setVisible(true);
	}
	//ok 눌렀을때
	class OKAtionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			selectedMenuModel.setNumRows(0);
			try {
				Statement stmt = con.createStatement();
				String sql;
				
				//menu1의 num 수정
				for(int i=0; i<table1.getRowCount(); i++)
				{
					if((int)table1.getValueAt(i, 2)!= 0)
					{
						Object name =  table1.getValueAt(i, 0);
						sql="update menu1 set num=" + table1.getValueAt(i, 2)+ " where name= '"+name+"';";
						stmt.executeUpdate(sql);
					}
				}
				//menu2의 num 수정
				for(int i=0; i<table2.getRowCount(); i++)
				{
					if((int)table2.getValueAt(i, 2)!= 0)
					{
						Object name =  table2.getValueAt(i, 0);
						sql="update menu2 set num=" + table2.getValueAt(i, 2)+ " where name= '"+name+"';";
						stmt.executeUpdate(sql);
					}
				}
				//menu3의 num 수정
				for(int i=0; i<table3.getRowCount(); i++)
				{
					if((int)table3.getValueAt(i, 2)!= 0)
					{
						Object name =  table3.getValueAt(i, 0);
						sql="update menu3 set num=" + table3.getValueAt(i, 2)+ " where name= '"+name+"';";
						stmt.executeUpdate(sql);
					}
				}
				//mymenu의 num 수정
				for(int i=0; i<table4.getRowCount(); i++)
				{
					if((int)table4.getValueAt(i, 2)!= 0)
					{
						Object name =  table4.getValueAt(i, 0);
						sql="update mymenu set num=" + table4.getValueAt(i, 2)+ " where name= '"+name+"';";
						stmt.executeUpdate(sql);
					}
				}
				
				//table1의 정보가져오기
				sql = "select name, price, num from menu1 where num != 0";
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()){
					String name = rs.getString("name");
					int num = rs.getInt("num");
					int price = rs.getInt("price")*num;
					
					Object rowdata[]={name,num,price};
					selectedMenuModel.addRow(rowdata);
				}
				//table2의 정보가져오기
				sql = "select name, price, num from menu2 where num != 0";
				rs = stmt.executeQuery(sql);
				
				while(rs.next()){
					String name = rs.getString("name");
					int num = rs.getInt("num");
					int price = rs.getInt("price")*num;
					
					Object rowdata[]={name,num,price};
					selectedMenuModel.addRow(rowdata);
				}//table3의 정보가져오기
				sql = "select name, price, num from menu3 where num != 0";
				rs = stmt.executeQuery(sql);
				
				while(rs.next()){
					String name = rs.getString("name");
					int num = rs.getInt("num");
					int price = rs.getInt("price")*num;
					
					Object rowdata[]={name,num,price};
					selectedMenuModel.addRow(rowdata);
				}
				//mymenu의 정보가져오기
				sql = "select name, price, num from mymenu where num != 0";
				rs = stmt.executeQuery(sql);
				
				while(rs.next()){
					String name = rs.getString("name");
					int num = rs.getInt("num");
					int price = rs.getInt("price")*num;
					
					Object rowdata[]={name,num,price};
					selectedMenuModel.addRow(rowdata);
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			
			
		}
		
	}
	//메뉴 삭제하기 눌렀을때
	class removeMenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				Statement stmt = con.createStatement();
				String sql;
				int index;
				if(tab1.isShowing()) {
					index=table1.getSelectedRow();
					sql = "delete from menu1 where name='"+table1.getValueAt(index, 0)+"';";
					stmt.execute(sql);
					tableModel1.removeRow(index);
				}
				if(tab2.isShowing()) {
					index=table2.getSelectedRow();
					sql = "delete from menu2 where name='"+table2.getValueAt(index, 0)+"';";
					stmt.execute(sql);
					tableModel2.removeRow(index);
				}
				if(tab3.isShowing()) {
					index=table3.getSelectedRow();
					sql = "delete from menu3 where name='"+table3.getValueAt(index, 0)+"';";
					stmt.execute(sql);
					tableModel3.removeRow(index);
				}
				if(tab4.isShowing()) {
					index=table4.getSelectedRow();
					sql = "delete from mymenu where name='"+table4.getValueAt(index, 0)+"';";
					stmt.execute(sql);
					tableModel4.removeRow(index);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	//메뉴추가하기 눌렀을 때 (AddMenuDialog부름)
	class AddMenuActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
			if(tab1.isShowing()){
				AddMenuDialog mydlg = new AddMenuDialog(tableModel1, "menu1");
			}
			if(tab2.isShowing()){
				AddMenuDialog mydlg = new AddMenuDialog(tableModel2, "menu2");
			}
			if(tab3.isShowing()){
				AddMenuDialog mydlg = new AddMenuDialog(tableModel3, "menu3");
			}
			if(tab4.isShowing()){
				AddMenuDialog mydlg = new AddMenuDialog(tableModel4, "mymenu");
			}
			
		}
	
	}
	
	//Try Again 눌렀을 때 (초기화)
	class TryAgainActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			selectedMenuModel.setNumRows(0);
			lblResult.setText("");
			for(int i=0; i<tableModel1.getRowCount();i++)
				table1.setValueAt(0, i, 2);
			for(int i=0; i<tableModel2.getRowCount();i++)
				table2.setValueAt(0, i, 2);
			for(int i=0; i<tableModel3.getRowCount();i++)
				table3.setValueAt(0, i, 2);
			for(int i=0; i<tableModel4.getRowCount();i++)
				table4.setValueAt(0, i, 2);
			try {
				
				Statement stmt = con.createStatement();
				String sql;
				
				sql="update menu1 set num =0;";
				stmt.executeUpdate(sql);
				
				sql="update menu2 set num =0;";
				stmt.executeUpdate(sql);

				sql="update menu3 set num =0;";
				stmt.executeUpdate(sql);
				
				sql="update mymenu set num =0;";
				stmt.executeUpdate(sql);
				
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	//swupay 눌렀을 때 
	class swupayListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(rdbtnCount1.isSelected()) person_num =1;
			if(rdbtnCount2.isSelected()) person_num =2;
			if(rdbtnCount3.isSelected()) person_num =3;
			if(rdbtnCount4.isSelected()) person_num =4;
			if(rdbtnCount5.isSelected()) person_num =5;
			
			int total =0;
			for(int i=0; i<selectedMenuModel.getRowCount(); i++)
				total += (int)selectedMenuModel.getValueAt(i, 2);
			lblResult.setText("총 합계 : "+total+"원, 인원수 : "+person_num+"명, 한 사람당 : "+total/person_num+"원");
			
		}
		
	}

	Connection makeConnection()
	{
		Connection c = null;
		
		String url = "jdbc:mysql://localhost:6269/swupay";
		String user="root";
		String password="1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c=DriverManager.getConnection(url, user, password);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	private void Init_menu1() {
		// TODO Auto-generated method stub
		try {
			Statement stmt = con.createStatement();	
			String sql;
			
			sql="update menu1 set num =0;";
			stmt.executeUpdate(sql);
			
			sql = "select * from menu1;";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int num = 0;
				
				Object rowdata[] = {name, price, num};
				
				tableModel1.addRow(rowdata);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void Init_menu2() {
		// TODO Auto-generated method stub
		try {
			Statement stmt = con.createStatement();	
			String sql;
			
			sql="update menu2 set num =0;";
			stmt.executeUpdate(sql);
			
			sql = "select * from menu2;";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int num = 0;
				
				Object rowdata[] = {name, price, num};
				
				tableModel2.addRow(rowdata);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void Init_menu3() {
		// TODO Auto-generated method stub
		try {
			Statement stmt = con.createStatement();	
			String sql;
			
			sql="update menu3 set num =0;";
			stmt.executeUpdate(sql);
								
			sql = "select * from menu3;";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int num = 0;
				
				
				Object rowdata[] = {name, price, num};
				
				tableModel3.addRow(rowdata);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void Init_mymenu() {
		// TODO Auto-generated method stub
		try {
			Statement stmt = con.createStatement();	
			String sql;
			
			sql="update mymenu set num =0;";
			stmt.executeUpdate(sql);
			
			sql = "select * from mymenu;";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int num = 0;
				
				
				Object rowdata[] = {name, price, num};
				
				tableModel4.addRow(rowdata);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
