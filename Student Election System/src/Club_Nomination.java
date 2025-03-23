/**
 * Club nomination page for nominating ourself for election
 * @author Nikhilesh Kovvuri
 * @version V1.0
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import java.awt.SystemColor;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Label;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Club_Nomination extends JFrame {

	// Initializing and declaring variables
	private JPanel contentPane;
	private JComboBox ClubcomboBox;
	Connection Connection = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
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
	 * @param constructor for displaying club names in combo box
	 */
	
	public void Club_data()
	{
		try
		{
			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");
			String query = "select * from club";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				ClubcomboBox.addItem(rs.getString("Club_Name"));
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Club_Nomination(String StudentNumber,String FirstName) {
		setTitle("Club Nomination");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1241, 589);
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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, 0, 0, Short.MAX_VALUE)
						.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		
		JLabel Club_Label = new JLabel("SELECT CLUB");
		Club_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		Club_Label.setBounds(27, 110, 159, 75);
		desktopPane.add(Club_Label);
		
		JLabel Role_Label = new JLabel("SELECT ROLE");
		Role_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		Role_Label.setBounds(27, 225, 159, 75);
		desktopPane.add(Role_Label);
		
		JComboBox ListcomboBox = new JComboBox();
		ListcomboBox.setForeground(Color.BLUE);
		ListcomboBox.setBounds(247, 233, 321, 60);
		desktopPane.add(ListcomboBox);
		ListcomboBox.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		ListcomboBox.setModel(new DefaultComboBoxModel(new String[] {"Select from List", "PRESIDENT", "VICE PRESIDENT", "MEMBER"}));
		
		//button for adding values in nominate table to display student nomination
		JButton Nominate_Button = new JButton("NOMINATE");
		Nominate_Button.setBackground(Color.BLUE);
		Nominate_Button.setForeground(Color.WHITE);
		Nominate_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// try and catch statement in action event to add nomination values in nomination table
				try{
					
					String query = "INSERT INTO `nomination`(`Student_Id`,`Student_Name`, `Club_Name`, `Nominated_Role`) VALUES (?,?,?,?)";
					Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");	
					pst=Connection.prepareStatement(query);
					
					pst.setString(1, StudentNumber);
					pst.setString(2, FirstName);
					pst.setString(3, ClubcomboBox.getSelectedItem().toString());
					pst.setString(4, ListcomboBox.getSelectedItem().toString());
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Sucessfully Nominated");
					}
				
					catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Already Nominated!");
					}

			}
		});
		Nominate_Button.setBounds(269, 375, 240, 60);
		desktopPane.add(Nominate_Button);
		
		ClubcomboBox = new JComboBox();
		ClubcomboBox.setForeground(Color.BLUE);
		ClubcomboBox.setModel(new DefaultComboBoxModel(new String[] {"Select from List"}));
		ClubcomboBox.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		ClubcomboBox.setBounds(247, 118, 321, 60);
		desktopPane.add(ClubcomboBox);
		
		// button to redirect to student dashboard page
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
		
		// button to redirect to  Club nomination page
		JButton ClubNomination_Button = new JButton("CLUB NOMINATION");
		ClubNomination_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Club_Nomination CN = new Club_Nomination(StudentNumber,FirstName);
				CN.setVisible(true);
				dispose();
			}
		});
		ClubNomination_Button.setForeground(Color.WHITE);
		ClubNomination_Button.setBackground(Color.BLUE);
		ClubNomination_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		ClubNomination_Button.setBorder(null);
		
		// button to redirect to Upcoming election page
		JButton UpcomingElections_Button = new JButton("UPCOMING ELECTIONS");
		UpcomingElections_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Upcoming_Election UE = new Upcoming_Election(StudentNumber,FirstName);
				UE.setVisible(true);
				dispose();
			}
		});
		UpcomingElections_Button.setForeground(Color.WHITE);
		UpcomingElections_Button.setBackground(Color.BLUE);
		UpcomingElections_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		UpcomingElections_Button.setBorder(null);
		
		// button to redirect to upcoming events page		
		JButton UpcomingEvents_Button = new JButton("UPCOMING EVENTS");
		UpcomingEvents_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Upcoming_Events UES = new Upcoming_Events(StudentNumber,FirstName);
				UES.setVisible(true);
				dispose();
			}
		});
		UpcomingEvents_Button.setForeground(Color.WHITE);
		UpcomingEvents_Button.setBackground(Color.BLUE);
		UpcomingEvents_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		UpcomingEvents_Button.setBorder(null);
		
		// button to redirect to button to redirect back to dashboard
		JButton Back_Button = new JButton("Back");
		Back_Button.setForeground(Color.WHITE);
		Back_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student_Dashboard SP = new Student_Dashboard(StudentNumber);
				SP.setVisible(true);
				dispose();
			}
		});
		Back_Button.setBackground(Color.BLUE);
		Back_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		Back_Button.setBorder(null);
		
		// button to redirect to results page
		JButton Result_Button = new JButton("Results");
		Result_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Result r = new Result(StudentNumber,FirstName);
				r.setVisible(true);
				dispose();
			}
			
		});
		Result_Button.setForeground(Color.WHITE);
		Result_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		Result_Button.setBorder(null);
		Result_Button.setBackground(Color.BLUE);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(ClubNomination_Button)
					.addGap(83))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(Back_Button, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(279, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(UpcomingEvents_Button, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(59, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(UpcomingElections_Button)
					.addContainerGap(19, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(Result_Button, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(230, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(Profile_Button)
					.addContainerGap(225, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(63)
					.addComponent(Back_Button, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(Profile_Button)
					.addGap(56)
					.addComponent(ClubNomination_Button)
					.addGap(44)
					.addComponent(UpcomingEvents_Button)
					.addGap(39)
					.addComponent(UpcomingElections_Button)
					.addGap(34)
					.addComponent(Result_Button, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(67, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		//calling function which is create in constructor
		Club_data();
	}


	public Club_Nomination(String studentNumber) {
		// TODO Auto-generated constructor stub
	}

	
}
