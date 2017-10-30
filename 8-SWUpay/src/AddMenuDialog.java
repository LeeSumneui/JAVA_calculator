import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.swing.JTextArea;


public class AddMenuDialog extends JDialog{

	private final JPanel contentPanel = new JPanel();
	String[] tableTitle = {"메뉴이름", "가격"};
	DefaultTableModel tablemodel, myTableModel;
	JPanel myPanel;
	private JTextField textField_2;
	private JTextField textField_3;
	int count;
	String menu_name;
	Connection con = makeConnection();
	public AddMenuDialog(DefaultTableModel selectedtablemodel, String menu) {
		setVisible(true);
		setResizable(false);
		setTitle("\uBA54\uB274\uCD94\uAC00\uD558\uAE30");
		setBounds(100, 100, 385, 132);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		myTableModel = selectedtablemodel;
		menu_name=menu;
		
		
		//가격 텍스트 필드
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(133, 56, 106, 20);
		contentPanel.add(textField_2);
		
		//메뉴 이름 텍스트 필드 
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(133, 22, 106, 20);
		contentPanel.add(textField_3);
		
		JLabel lblMenuName = new JLabel("\uBA54\uB274\uC774\uB984");
		lblMenuName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMenuName.setBounds(38, 21, 78, 23);
		contentPanel.add(lblMenuName);
		
		JLabel lblPrice = new JLabel("\uAC00\uACA9");
		lblPrice.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrice.setBounds(38, 56, 62, 21);
		contentPanel.add(lblPrice);
		
		JButton btnAddmenu = new JButton("add");
		btnAddmenu.addActionListener(new btnAddmenuActionListener());
		btnAddmenu.setBounds(261, 24, 85, 52);
		contentPanel.add(btnAddmenu);
		
	}
	
	//Add버튼 리스너
	class btnAddmenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			String strMenuName, strPrice;
			int price, num;
			strMenuName = textField_3.getText(); //메뉴 이름 텍스트 필드의 텍스트를 strMenuName에 저장
			strPrice = textField_2.getText(); //가격 텍스트 필드의 텍스트를 strPrice에 저장
			
			price = Integer.parseInt(strPrice);
			num=0;
			
			
			try {
				Statement stmt = con.createStatement();
				String sql;
				sql = "insert into "+menu_name+" (name,price,num) values('"+strMenuName+"',"
						+price+","+num+");";
				stmt.executeUpdate(sql);		
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Object rowdata[] = {strMenuName, price, num};
			myTableModel.addRow(rowdata);
			
			dispose();
			
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
	
	
}
