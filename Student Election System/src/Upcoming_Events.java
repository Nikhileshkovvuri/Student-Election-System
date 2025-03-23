/**
 * Upcoming Event page for viewing news/ new events for students
 * @author Nikhilesh Kovvuri
 * @version V1.0
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Label;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Upcoming_Events extends JFrame {

	//Initializing and declaring variables
	private JPanel contentPane;
	 String[] columnNames = {"Club Name", "Description", "Start Date", "End Date"};
	 Connection Connection = null;
		Statement pst = null;
		ResultSet rs=null;
		private JTable Event_table;
		DefaultTableModel tblmodel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * @param constructor for displaying new event in table which is present in database
	 */
	public void clubdata()
	{
		try
		{
			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");
			String query = "select * from events";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tblmodel = (DefaultTableModel)Event_table.getModel();
			tblmodel.setRowCount(0);
			
			while (rs.next())
				
			{
				String Club_Name = rs.getString("Club_Name");
				String Description = rs.getString ("Description");
				String Start_Date= rs.getString("StartDate");
				String End_Date = rs.getString("EndDate");
				
				String [] tbData = {Club_Name,Description,Start_Date,End_Date};
				
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
	 * Create the frame.
	 */
	public Upcoming_Events(String StudentNumber,String FirstName) {
		setTitle("Events");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 713);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
		);
		  
		// button to fetch events data in table
		JButton GetEvent_Button = new JButton("Get Events");
		GetEvent_Button.setBounds(319, 29, 166, 43);
		GetEvent_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clubdata();
				  
			}
		});
		GetEvent_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		desktopPane.setLayout(null);
		desktopPane.add(GetEvent_Button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 96, 799, 559);
		desktopPane.add(scrollPane);
		
		Event_table = new JTable();
		Event_table.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane.setViewportView(Event_table);
		Event_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Club Name", "Description", "Start Date", "End Date"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				

				return columnTypes[columnIndex];
			}
		
		});
	//	Event_table.getColumnModel().getColumn(0).setPreferredWidth(85);
		//Event_table.getColumnModel().getColumn(1).setPreferredWidth(375);
		Event_table.setRowHeight(30);
		//Event_table.getColumnModel().getColumn(1).setCellRenderer(null);
		Event_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Event_table.getColumnModel().getColumn(0).setPreferredWidth(65);
		Event_table.getColumnModel().getColumn(1).setPreferredWidth(650);
		Event_table.getColumnModel().getColumn(2).setPreferredWidth(65);
		Event_table.getColumnModel().getColumn(3).setPreferredWidth(65);
		
		// button to redirect to to student dashboard
		JButton Profile_Button = new JButton("PROFILE");
		Profile_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student_Dashboard SP = new Student_Dashboard(StudentNumber);
				SP.setVisible(true);
				dispose();
			}
		});
		Profile_Button.setForeground(Color.WHITE);
		Profile_Button.setBackground(Color.BLUE);
		Profile_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		Profile_Button.setBorder(null);
		
		// button to redirect to to club nomination page
		JButton btnClubNomination = new JButton("CLUB NOMINATION");
		btnClubNomination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Club_Nomination CN = new Club_Nomination(StudentNumber);
				CN.setVisible(true);
				dispose();
			}
		});
		btnClubNomination.setForeground(Color.WHITE);
		btnClubNomination.setBackground(Color.BLUE);
		btnClubNomination.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		btnClubNomination.setBorder(null);
		
		// button to redirect to upcoming election page
		JButton btnUpcomingElections = new JButton("UPCOMING ELECTIONS");
		btnUpcomingElections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Upcoming_Election UE = new Upcoming_Election(StudentNumber,FirstName);
				UE.setVisible(true);
				dispose();
			}
		});
		btnUpcomingElections.setForeground(Color.WHITE);
		btnUpcomingElections.setBackground(Color.BLUE);
		btnUpcomingElections.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		btnUpcomingElections.setBorder(null);
		
		// button to redirect to upcoming events
		JButton btnUpcomingEvents = new JButton("UPCOMING EVENTS");
		btnUpcomingEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Upcoming_Events UE = new Upcoming_Events(StudentNumber,FirstName);
				UE.setVisible(true);
				dispose();
			}
		});
		btnUpcomingEvents.setForeground(Color.WHITE);
		btnUpcomingEvents.setBackground(Color.BLUE);
		btnUpcomingEvents.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		btnUpcomingEvents.setBorder(null);
		
		// button to redirect to student dashboard
		JButton back_Button = new JButton("Back");
		back_Button.setForeground(Color.WHITE);
		back_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student_Dashboard SP = new Student_Dashboard(StudentNumber);
				SP.setVisible(true);
				dispose();
			}
		});
		back_Button.setBackground(Color.BLUE);
		back_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		back_Button.setBorder(null);
		
		// button to redirect to result page
		JButton btnResults = new JButton("Results");
		btnResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result r = new Result(StudentNumber,FirstName);
				r.setVisible(true);
				dispose();
			}
		});
		btnResults.setForeground(Color.WHITE);
		btnResults.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		btnResults.setBorder(null);
		btnResults.setBackground(Color.BLUE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUpcomingEvents, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpcomingElections)
						.addComponent(btnResults, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClubNomination)
						.addComponent(Profile_Button)
						.addComponent(back_Button))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(70)
					.addComponent(back_Button)
					.addGap(74)
					.addComponent(Profile_Button)
					.addGap(74)
					.addComponent(btnClubNomination)
					.addGap(70)
					.addComponent(btnUpcomingEvents)
					.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
					.addComponent(btnUpcomingElections)
					.addGap(53)
					.addComponent(btnResults, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(58))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
