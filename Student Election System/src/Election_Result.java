/**
 * Election page for finding out the total votes of each candidate for admin
 * @author Nikhilesh Kovvuri
 * @version V1.0
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.Window.Type;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Election_Result extends JFrame {
	
	// initializing and declaring variables 
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection connection = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	private JTable Votes_table ;
	DefaultTableModel	tblmodel;
	private JComboBox Club_comboBox;
	private JComboBox Role_comboBox;
	private JComboBox Nominee_comboBox;
	String ClubName;
	String NominatedRole;
	String NomineeName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Election_Result frame = new Election_Result();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/*
	 * @param  Constructor for displaying Clubs in drop downs
	 */
	public void club_name()
	 
	{
		try
		{
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");
			String query = "select * from Club ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			Club_comboBox.removeAllItems();
			
			while (rs.next())
			{
				
				Club_comboBox.addItem(rs.getString("Club_Name"));
				
			}
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}
	
	
	/*
	 * @param constructor for getting candidate name by sorting values of culb name and position
	 */
	
	private void Nominee_data() {
		// TODO Auto-generated method stub
		// drop down vales stored in string values
		ClubName = (String) Club_comboBox.getSelectedItem();
		NominatedRole = (String) Role_comboBox.getSelectedItem();
		try
		{
			
			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");
			String query = "select distinct Candidate_Name from Votes where Club_Name= '"+ClubName+"'  and Candidate_Nomination= '"+NominatedRole+"' ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			Nominee_comboBox.removeAllItems();
			
			while (rs.next())
			{
				
				Nominee_comboBox.addItem(rs.getString("Candidate_Name"));
				
			}
		} 
			catch (Exception e1)
		{
			
			e1.printStackTrace();
		}
	}
	

	/*
	 * @param constructor for displaying the election results in table
	 */
	
	public void clubdata()
	{
		NomineeName= (String) Nominee_comboBox.getSelectedItem();
		try
		{
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");
			String query = "select * from votes  ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tblmodel =  (DefaultTableModel)Votes_table.getModel();
			tblmodel.setRowCount(0);
			
			while (rs.next())
			{
				String Club_Name = rs.getString("Club_Name");
				String Nominated_Role = rs.getString ("Candidate_Nomination");
				String Candidate_Name = rs.getString ("Candidate_Name");
				String Votes = rs.getString("Votes");
				
				String [] tbData = {Club_Name,Nominated_Role,Candidate_Name,Votes};
				tblmodel.addRow(tbData);	
			}
			connection.close();
		} 
			catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Create the frame.
	 */
	public Election_Result() {
		setTitle("Result");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 931, 580);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 259, 528);
		panel.setBackground(new Color(51, 0, 255));
		
		// button to fetch and display data in result table
		JButton Create_Button = new JButton("Get Result");
		Create_Button.setBounds(310, 73, 149, 35);
		Create_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				clubdata();
				}
		});
		Create_Button.setForeground(Color.WHITE);
		Create_Button.setBackground(new Color(51, 0, 255));
		Create_Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		// button to redirect to admin dashboard
		JButton Back_Button = new JButton("BACK");
		Back_Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		Back_Button.setForeground(Color.WHITE);
		Back_Button.setBackground(new Color(51, 0, 255));
		Back_Button.setBorder(null);
		Back_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create_Club AD = new Create_Club();
				AD.setVisible(true);
				dispose();
			}
		});
		
		// button to redirect to create club page
		JButton CreateClub_Button = new JButton("Create Club");
		CreateClub_Button.setForeground(Color.WHITE);
		CreateClub_Button.setBackground(new Color(51, 0, 255));
		CreateClub_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		CreateClub_Button.setBorder(null);
		CreateClub_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create_Club AD = new Create_Club();
				AD.setVisible(true);
				dispose();
				
			}
		});
		
		// button to redirect to create election page
		JButton CreateElection_Button = new JButton("Create Elections");
		CreateElection_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create_Election CEs = new Create_Election();
				CEs.setVisible(true);
				dispose();
				
			}
		});
		CreateElection_Button.setForeground(Color.WHITE);
		CreateElection_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		CreateElection_Button.setBackground(new Color(51, 0, 255));
		CreateElection_Button.setBorder(null);
		
		// button to redirect to election result page
		JButton ElectionResult_Button = new JButton("Election Result");
		ElectionResult_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Election_Result ER = new Election_Result();
				ER.setVisible(true);
				dispose();
			}
		});
		ElectionResult_Button.setForeground(Color.WHITE);
		ElectionResult_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		ElectionResult_Button.setBackground(new Color(51, 0, 255));
		ElectionResult_Button.setBorder(null);
		
		// button to redirect to announce result page
		JButton Settings_Button = new JButton("Anounce Result");
		Settings_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		Settings_Button.setForeground(Color.WHITE);
		Settings_Button.setBackground(new Color(51, 0, 255));
		Settings_Button.setBorder(null);
		Settings_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Anounce_Result AR = new Anounce_Result();
				AR.setVisible(true);
				dispose();
			}
		});
		
		// button to redirect to create events page
		JButton CreateEvents_Button = new JButton("Create Events");
		CreateEvents_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create_Events CE = new Create_Events();
				CE.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		CreateEvents_Button.setForeground(Color.WHITE);
		CreateEvents_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		CreateEvents_Button.setBorder(null);
		CreateEvents_Button.setBackground(new Color(51, 0, 255));
		
		//Button for log out of the portal
		JButton Logout_Button = new JButton("Logout");
		Logout_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(Logout_Button, "Are you sure you wish to Logout ?");
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    Login obj = new Login();
                    obj.setTitle("Login");
                    obj.setVisible(true);
                }
                if (a == JOptionPane.NO_OPTION)
                {
                	Election_Result ER= new Election_Result();
                	ER.setVisible(true);
                	dispose();
                }
			}
		});
		Logout_Button.setForeground(Color.WHITE);
		Logout_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		Logout_Button.setBorder(null);
		Logout_Button.setBackground(new Color(51, 0, 255));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(Back_Button, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(Logout_Button, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
						.addComponent(CreateClub_Button, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(CreateEvents_Button, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
						.addComponent(Settings_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(ElectionResult_Button, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(CreateElection_Button, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(Back_Button, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(CreateClub_Button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(CreateEvents_Button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(CreateElection_Button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(ElectionResult_Button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(Settings_Button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(Logout_Button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(51, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.add(panel);
		contentPane.add(Create_Button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(310, 118, 545, 383);
		contentPane.add(scrollPane);
		
		Votes_table = new JTable();
		scrollPane.setViewportView(Votes_table);
		Votes_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Club Name", "Nominated Role", "Student Name", "Votes"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		Votes_table.getColumnModel().getColumn(1).setPreferredWidth(156);
		Votes_table.getColumnModel().getColumn(2).setPreferredWidth(142);
		
		Club_comboBox = new JComboBox();
		Club_comboBox.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		Club_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Club"}));
		Club_comboBox.setBounds(310, 25, 149, 32);
		contentPane.add(Club_comboBox);
		
		Role_comboBox = new JComboBox();
		Role_comboBox.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		Role_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Role", "PRESIDENT", "VICE PRESIDENT"}));
		Role_comboBox.setBounds(469, 25, 195, 32);
		contentPane.add(Role_comboBox);
		
		Nominee_comboBox = new JComboBox();
		Nominee_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Nominee"}));
		Nominee_comboBox.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		Nominee_comboBox.setBounds(685, 25, 170, 32);
		contentPane.add(Nominee_comboBox);
		
		// button to fetch nominee name in combobox
		JButton nominee_Button = new JButton("Get Nominee");
		nominee_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Nominee_data();
			}
		});
		nominee_Button.setForeground(Color.WHITE);
		nominee_Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		nominee_Button.setBackground(new Color(51, 0, 255));
		nominee_Button.setBounds(695, 73, 160, 35);
		contentPane.add(nominee_Button);
		
		//filter button for filtering out the data from the table
		JButton Filter_Button = new JButton("Filter");
		Filter_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NomineeName= (String) Nominee_comboBox.getSelectedItem();
				try
				{
					connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");
					String query = "select * from votes where Club_Name = '"+ClubName+"' and Candidate_Nomination = '"+NominatedRole+"' and Candidate_Name = '"+NomineeName+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tblmodel =  (DefaultTableModel)Votes_table.getModel();
					tblmodel.setRowCount(0);
						
					while (rs.next())	
					{
						String Club_Name = rs.getString("Club_Name");
						String Nominated_Role = rs.getString ("Candidate_Nomination");
						String Candidate_Name = rs.getString ("Candidate_Name");
						String Votes = rs.getString("Votes");
						
						String [] tbData = {Club_Name,Nominated_Role,Candidate_Name,Votes};
					    tblmodel.addRow(tbData);	
					}
					connection.close();
				} 
					catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		Filter_Button.setForeground(Color.WHITE);
		Filter_Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		Filter_Button.setBackground(new Color(51, 0, 255));
		Filter_Button.setBounds(501, 73, 149, 35);
		contentPane.add(Filter_Button);
		
		//calling the constructor and displaying the name of the club
		club_name();
		
	}
}
