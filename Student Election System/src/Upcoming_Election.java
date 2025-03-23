/**
 * Upcoming Election page for nominating election candidate
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
import java.awt.event.ActionEvent;
import java.awt.Label;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Upcoming_Election extends JFrame {

	// Initialing and declaring variables
	private JPanel contentPane;
	private JComboBox Role_comboBox;
	private JComboBox Nomine_comboBox;
	String Club=null;
	String Role=null;
	String ID=null;
	Connection connection;
	PreparedStatement pst;
	ResultSet rs;
	private JComboBox Club_comboBox;
	int vote =1;
	/**
	 * Launch the application.
	 */
	public static void main() {
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
	 * @param constructor to display club name into combo box
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
	 * @param Constructor to fetch and display candidate name into combo box
	 */
	private void Nominee_data() {
		// TODO Auto-generated method stub
		Club = (String) Club_comboBox.getSelectedItem();
		Role = (String) Role_comboBox.getSelectedItem();
		try
		{
			
			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");
			String query = "select Student_Name from nomination where Club_Name= '"+Club+"'  and Nominated_Role= '"+Role+"' ";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			Nomine_comboBox.removeAllItems();
			
			while (rs.next())
			{
				
				Nomine_comboBox.addItem(rs.getString("Student_Name"));
				
			}
		} 
			catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}
	


	/**
	 * Create the frame.
	 */
	public Upcoming_Election(String StudentNumber,String FirstName) {
		setTitle("Election");
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
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(desktopPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JLabel SelectClub_Label = new JLabel("SELECT CLUB");
		SelectClub_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		SelectClub_Label.setBounds(27, 97, 159, 62);
		desktopPane.add(SelectClub_Label);
		
		Role_comboBox = new JComboBox();
		Role_comboBox.setBounds(232, 200, 213, 48);
		desktopPane.add(Role_comboBox);
		Role_comboBox.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		Role_comboBox.setModel(new DefaultComboBoxModel(new String[] {"SELECT ROLE"," PRESIDENT", "VICE PRESIDENT", "Member"}));
		
		Nomine_comboBox = new JComboBox();
		Nomine_comboBox.setBounds(232, 292, 213, 48);
		desktopPane.add(Nomine_comboBox);
		Nomine_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Nominee"}));
		Nomine_comboBox.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		
		// button to insert the vote recored into the db
		JButton Vote_Button = new JButton("Vote");
		Vote_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// storing combo box value in string variable
			String	Nominate = Nomine_comboBox.getSelectedItem().toString();
			
			// try catch statement used in action performed to insert into db
				try
				{
					String query = "INSERT INTO `votes`(`Student_Id`, `Club_Name`, `Candidate_Name`, `Candidate_Nomination`, `Votes`) VALUES (?,?,?,?,?)";
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");	
					pst=connection.prepareStatement(query);
				    pst.setString(1,StudentNumber);	
					pst.setString(2, Club);
					pst.setString(3,Nominate);
					pst.setString(4, Role);
					pst.setInt(5,vote);	
				pst.executeUpdate();
				{
					JOptionPane.showMessageDialog(null, "Voted Sucessfully!");
				}			
			}
				catch (SQLException e1)
				{
					JOptionPane.showMessageDialog(null, "Already Voted! ");
				}
			
			}
		});
		Vote_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		Vote_Button.setBounds(232, 411, 222, 48);
		desktopPane.add(Vote_Button);
		
		JLabel SelectRole_Label = new JLabel("SELECT ROLE");
		SelectRole_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		SelectRole_Label.setBounds(27, 185, 159, 75);
		desktopPane.add(SelectRole_Label);
		
		JLabel SelectNominee_Label = new JLabel("SELECT NOMINEE");
		SelectNominee_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		SelectNominee_Label.setBounds(27, 278, 183, 75);
		desktopPane.add(SelectNominee_Label);
		
		// button to get candidate names
		JButton Nominee_Button = new JButton("Get Nominee");
		Nominee_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				 if (e.getSource() == Nominee_Button) {
					 Nominee_data();
				  }
				
				
			}
		});
		Nominee_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		Nominee_Button.setBounds(528, 296, 174, 40);
		desktopPane.add(Nominee_Button);
		
		Club_comboBox = new JComboBox();
		Club_comboBox.setModel(new DefaultComboBoxModel(new String[] {"SELECT CLUB"}));
		Club_comboBox.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		Club_comboBox.setBounds(230, 111, 215, 48);
		desktopPane.add(Club_comboBox);
		
		// button to redirect to  to student dashboard
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
		
		// button to redirect to club nomination page
		JButton ClubNomination_button = new JButton("CLUB NOMINATION");
		ClubNomination_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Club_Nomination CN = new Club_Nomination(StudentNumber,FirstName);
				CN.setVisible(true);
				dispose();
			}
		});
		ClubNomination_button.setForeground(Color.WHITE);
		ClubNomination_button.setBackground(Color.BLUE);
		ClubNomination_button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		ClubNomination_button.setBorder(null);
		
		// button to redirect to upcoming election page
		JButton UpcomingElections_button = new JButton("UPCOMING ELECTIONS");
		UpcomingElections_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Upcoming_Election UE = new Upcoming_Election(StudentNumber,FirstName);
				UE.setVisible(true);
				dispose();
			}
		});
		UpcomingElections_button.setForeground(Color.WHITE);
		UpcomingElections_button.setBackground(Color.BLUE);
		UpcomingElections_button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		UpcomingElections_button.setBorder(null);
		
		// button to redirect to upcoming events page
		JButton UpcomingEvents_button = new JButton("UPCOMING EVENTS");
		UpcomingEvents_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Upcoming_Events UE = new Upcoming_Events(StudentNumber,FirstName);
				UE.setVisible(true);
				dispose();
			}
		});
		UpcomingEvents_button.setForeground(Color.WHITE);
		UpcomingEvents_button.setBackground(Color.BLUE);
		UpcomingEvents_button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		UpcomingEvents_button.setBorder(null);
		
		// button to redirect to student dashboard page
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
		
		// button to redirect to result page
		JButton Result_button = new JButton("Results");
		Result_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result r = new Result(StudentNumber,FirstName);
				r.setVisible(true);
				dispose();
				
			}
		});
		Result_button.setForeground(Color.WHITE);
		Result_button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		Result_button.setBorder(null);
		Result_button.setBackground(Color.BLUE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(Result_button, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
						.addComponent(ClubNomination_button)
						.addComponent(Profile_Button)
						.addComponent(Back_Button))
					.addContainerGap(73, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(33, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(UpcomingEvents_button)
						.addComponent(UpcomingElections_button))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(39)
					.addComponent(Back_Button)
					.addGap(43)
					.addComponent(Profile_Button)
					.addGap(43)
					.addComponent(ClubNomination_button)
					.addGap(45)
					.addComponent(UpcomingEvents_button)
					.addGap(50)
					.addComponent(UpcomingElections_button)
					.addGap(46)
					.addComponent(Result_button, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
	// calling constructor to fetch club names
	club_name();
	
		
	}
}
