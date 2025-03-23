/**
 * Result page for viewing the election result for students
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class Result extends JFrame {

	private JPanel contentPane;
	private JTable Nomination_table;
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
	
	/**
	 *@param
	 * A constructor for displaying results of election in table which is fetched from database
	 */
	public void getres()
	{
		// try catch statement for executing and displaying DB values in table
		try
		{
			
			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");
			String query = "select * from club_result";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			//creating a table model for creating row and columns
			tblmodel = (DefaultTableModel)Nomination_table.getModel();
			tblmodel.setRowCount(0);
			
			// While statement for retrieving the data from DB and storing it in table
			while (rs.next())
				
			{
				// declaring variable in string format and calling the table data into them.
				String Club_Name = rs.getString("Club_Name");
				String Nominated_Role = rs.getString ("Nominated_Role");
				String Student_Name= rs.getString("Student_Name");
				String votes = String.valueOf(rs.getInt("Votes"));
				
				//Creating a string values in array format to displaying table
				String [] tbData = {Club_Name,Nominated_Role,Student_Name,votes};
				
				// Storing the string values in tbl model table which is created
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
	 * declaring the the string values which is called other classes
	 */
	public Result(String StudentNumber,String FirstName) {
		setTitle("Election Result");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1163, 598);
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
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 756, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(desktopPane))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JButton Result_Button = new JButton("Get Result");
		Result_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				getres();
			}
		});
		Result_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		Result_Button.setBounds(261, 29, 174, 40);
		desktopPane.add(Result_Button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 79, 709, 439);
		desktopPane.add(scrollPane);
		
		Nomination_table = new JTable();
		Nomination_table.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 18));
		scrollPane.setViewportView(Nomination_table);
		Nomination_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			// creating array list to diplay the column names
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
		Nomination_table.getColumnModel().getColumn(0).setPreferredWidth(117);
		Nomination_table.getColumnModel().getColumn(1).setPreferredWidth(132);
		Nomination_table.getColumnModel().getColumn(2).setPreferredWidth(115);
		
		// button to redirect to student dashboard
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
				Club_Nomination CN = new Club_Nomination(StudentNumber);
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
		
		//Back Button command for going to back to Dashboard 
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
		
		//Result button for redirecting user to result Page
		JButton Result_button = new JButton("Results");
		Result_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Result r = new Result(StudentNumber ,FirstName);
				
			}
		});
		Result_button.setForeground(Color.WHITE);
		Result_button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		Result_button.setBorder(null);
		Result_button.setBackground(Color.BLUE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(Result_button)
						.addComponent(UpcomingElections_button)
						.addComponent(UpcomingEvents_button)
						.addComponent(ClubNomination_button)
						.addComponent(Profile_Button)
						.addComponent(Back_Button))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(56)
					.addComponent(Back_Button)
					.addGap(48)
					.addComponent(Profile_Button)
					.addGap(54)
					.addComponent(ClubNomination_button)
					.addGap(49)
					.addComponent(UpcomingEvents_button)
					.addGap(59)
					.addComponent(UpcomingElections_button)
					.addGap(36)
					.addComponent(Result_button, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		
	}

}
