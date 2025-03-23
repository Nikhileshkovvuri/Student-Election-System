/**
 * Student Dashboard page for viewing student Info for students
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
import java.awt.event.ActionEvent;
import java.awt.Label;
import javax.swing.JScrollBar;

public class Student_Dashboard extends JFrame {

	//Initializing and declaring variables
	private JPanel contentPane;
	private JTextField StudentNumber_textField;
	private static JTextField EmailID_textField;
	private static JTextField Password_textField;
	private JTextField FirstName_textField;
	private JTextField LastName_textField;
	Connection connection = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	String FirstName;
	
	
	/**
	 *  Launch the application.
	
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
	 * Create the frame.
	 */
	public Student_Dashboard(String StudentNumber) {
		setTitle("Student Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1082, 580);
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
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 674, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(desktopPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JLabel StudentNumber_Label = new JLabel("Student Number");
		
		StudentNumber_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		StudentNumber_Label.setBounds(152, 40, 181, 47);
		desktopPane.add(StudentNumber_Label);
		
		JLabel StudentEmail_Label = new JLabel("Email ID");
		StudentEmail_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		StudentEmail_Label.setBounds(152, 254, 110, 41);
		desktopPane.add(StudentEmail_Label);
		
		JLabel password_Label = new JLabel("Password");
		password_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		password_Label.setBounds(152, 322, 110, 47);
		desktopPane.add(password_Label);
		
		// button to redirect to change password page
		JButton Password_Button = new JButton("Change Password");
		Password_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Change_Password CP = new Change_Password(StudentNumber);
				CP.setVisible(true);
				dispose();
			}
		});
		Password_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		Password_Button.setBounds(22, 465, 306, 47);
		desktopPane.add(Password_Button);
		
		StudentNumber_textField = new JTextField();
		StudentNumber_textField.setBounds(386, 55, 139, 25);
		desktopPane.add(StudentNumber_textField);
		StudentNumber_textField.setColumns(10);
		
		EmailID_textField = new JTextField();
		EmailID_textField.setBounds(386, 266, 139, 25);
		desktopPane.add(EmailID_textField);
		EmailID_textField.setColumns(10);
		
		Password_textField = new JTextField();
		Password_textField.setColumns(10);
		Password_textField.setBounds(386, 337, 139, 25);
		desktopPane.add(Password_textField);
		
		// button to redirect to login page else sat on the same page
		JButton Logout_Buttons = new JButton("Logout");
		Logout_Buttons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(Logout_Buttons, "Are you sure you wish to Logout ?");
                if (a == JOptionPane.YES_OPTION) {
                    dispose();
                    Login obj = new Login();
                    obj.setTitle("Login");
                    obj.setVisible(true);
                }
                if (a == JOptionPane.NO_OPTION)
                {
                	Student_Dashboard SD= new Student_Dashboard(StudentNumber);
                	SD.setVisible(true);
                	dispose();
                }
    		}
			
		});
		Logout_Buttons.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		Logout_Buttons.setBounds(358, 465, 306, 47);
		desktopPane.add(Logout_Buttons);
		
		JLabel FirstName_Label = new JLabel("First Name");
		FirstName_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		FirstName_Label.setBounds(152, 107, 124, 47);
		desktopPane.add(FirstName_Label);
		
		JLabel LastName_Label = new JLabel("Last Name");
		LastName_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		LastName_Label.setBounds(152, 177, 124, 47);
		desktopPane.add(LastName_Label);
		
		FirstName_textField = new JTextField();
		FirstName_textField.setBounds(386, 120, 139, 29);
		desktopPane.add(FirstName_textField);
		FirstName_textField.setColumns(10);
		
		LastName_textField = new JTextField();
		LastName_textField.setColumns(10);
		LastName_textField.setBounds(386, 195, 139, 29);
		desktopPane.add(LastName_textField);
		
		// button to fetch info of the user detail into the text fileds
		JButton Getinfo_Button = new JButton("Get Info");
		Getinfo_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// try catch statement used to fetch and display using the student no
					try
					{
				    connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");
					String query = "SELECT * FROM login  WHERE Student_Id = "+StudentNumber+"";
					pst = connection.prepareStatement(query);
					 rs = pst.executeQuery();
					
					// using while statement to display the student info in the textfields
					while(rs.next())
					{
						FirstName_textField.setText(rs.getString("FirstName"));
						FirstName = FirstName_textField.getText();
						LastName_textField.setText(rs.getString("LastName"));
						StudentNumber_textField.setText(rs.getString("Student_Id"));
						Password_textField.setText(rs.getString("Password"));
						EmailID_textField.setText(rs.getString("EmailId"));
						
						
					}
				}
					
				
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
					});
		Getinfo_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		Getinfo_Button.setBounds(182, 394, 306, 47);
		desktopPane.add(Getinfo_Button);
		
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
		ClubNomination_button.addActionListener(new ActionListener()
		{
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
		
		// button to redirect to upcoming elections page
		JButton Elections_Button = new JButton("UPCOMING ELECTIONS");
		Elections_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Upcoming_Election UE = new Upcoming_Election(StudentNumber,FirstName);
				UE.setVisible(true);
				dispose();	
			}
		});
		Elections_Button.setForeground(Color.WHITE);
		Elections_Button.setBackground(Color.BLUE);
		Elections_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		Elections_Button.setBorder(null);
		
		// button to redirect to upcoming events page
		JButton Events_Button = new JButton("UPCOMING EVENTS");
		Events_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Upcoming_Events UES = new Upcoming_Events(StudentNumber,FirstName);
				UES.setVisible(true);
				dispose();
			}
		});
		Events_Button.setForeground(Color.WHITE);
		Events_Button.setBackground(Color.BLUE);
		Events_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 28));
		Events_Button.setBorder(null);
		
		// button to redirect to results page
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
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(ClubNomination_button)
						.addComponent(Profile_Button))
					.addContainerGap(99, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(15, Short.MAX_VALUE)
					.addComponent(Result_button)
					.addGap(262))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(Elections_Button)
					.addContainerGap(37, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(Events_Button)
					.addContainerGap(85, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(84)
					.addComponent(Profile_Button)
					.addGap(47)
					.addComponent(ClubNomination_button)
					.addGap(42)
					.addComponent(Events_Button)
					.addGap(40)
					.addComponent(Elections_Button)
					.addGap(58)
					.addComponent(Result_button, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(101, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		

	}
}
