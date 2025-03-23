/**
 * Student Registration page for register to get access in to the application for students
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
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;

public class Student_Registration extends JFrame {
	
	//Initializing and declaring variable
	private JPanel contentPane;
	private int attempt = 1;
	private JPasswordField passwordField;
	private JComboBox UserType_comboBox;
	Connection Connection = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	private JTextField FirstName_textField;
	private JTextField LastName_textField;
	private JTextField EmailID_textField;
	private JTextField StudentID_textField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_Registration frame = new Student_Registration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Student_Registration() {
		setTitle("Student Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1113, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel EmailId_Label = new JLabel("EMAIL ID");
		EmailId_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		
		JLabel Password_Label = new JLabel("PASSWORD");
		Password_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		
		// button for creating data for user to register
		JButton Register_Button = new JButton("REGISTER");
		Register_Button.setForeground(SystemColor.text);
		Register_Button.setBackground(Color.BLUE);
		Register_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 16));
		Register_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{		// declaring and initializing internal variables to enter the details in to db
				   String FirstName =  FirstName_textField.getText();
				   String LastName = LastName_textField.getText();	
				   String StudentID = StudentID_textField.getText();
				   String EmailID =EmailID_textField.getText();
				   String password= passwordField.getText();
				   String UserType = UserType_comboBox.getSelectedItem().toString();
				
				   // if else condition to make to enter every given filed in the registration
				   if (UserType.equals("Select User Type"))
				   {
					   JOptionPane.showMessageDialog(null, "Please Select the User Type");
				   }
				   //First Name
				else if (FirstName.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the First Name");
				}
				// last Name
				else if(LastName.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the Last Name");
				}
				//enter student id filed
				else if(StudentID.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the Student ID");
				}
				//email Field
				else if(EmailID.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the Email ID");
				}
				   //Password field		   
				else if(password.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the password");
				}
				
				   // try catch statement to connect to DB and insert and store values for verifying while logging in student page
				try{
					//query for insert to db fields
					String query = "INSERT INTO `login`(`FirstName`, `LastName`, `Student_Id`, `EmailId`, `Password`, `User_Type`) VALUES (?,?,?,?,?,?) ";
					
					//Connection and verification of DB to connect
					Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");	
					pst=Connection.prepareStatement(query);
					// inserting values into DB by storing and converting into string format
					pst.setString(1,FirstName );
					pst.setString(2, LastName);	
					pst.setString(3, StudentID);
					pst.setString(4, EmailID);
					pst.setString(5, password);
					pst.setString(6, UserType);
					
					//Create a loop in data record insert else failed
					 if (pst.executeUpdate() > 0)
					 {
					
					JOptionPane.showMessageDialog(null, "Welcome your Account Created Sucessfully."	+ "Login to your Account");
					}
				}
		
					catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "This account already Exist! Please Login ");
					}
			}
		});
		
		// button to redirect to into student login page
		JButton Login_button = new JButton("LOGIN");
		Login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// button for redirecting user to Login Page
			Login LP = new Login();
			LP.setVisible(true);
			dispose();
			}
		});
		Login_button.setForeground(SystemColor.text);
		Login_button.setBackground(Color.BLUE);
		Login_button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 16));
	
		
		JLabel UserType_label = new JLabel("USER TYPE");
		UserType_label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		
		UserType_comboBox = new JComboBox();
		UserType_comboBox.setBackground(SystemColor.control);
		UserType_comboBox.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 22));
		UserType_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select User Type", "Student"}));
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.BLUE);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 22));
		
		JLabel FirstName_Label = new JLabel("FIRST NAME");
		FirstName_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		
		JLabel LastName_Label = new JLabel("LAST NAME");
		LastName_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		
		FirstName_textField = new JTextField();
		FirstName_textField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 22));
		FirstName_textField.setColumns(10);
		
		LastName_textField = new JTextField();
		LastName_textField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 22));
		LastName_textField.setColumns(10);
		
		JLabel StudentID_Label = new JLabel("STUDENT ID");
		StudentID_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		
		EmailID_textField = new JTextField();
		EmailID_textField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 22));
		EmailID_textField.setColumns(10);
		
		StudentID_textField = new JTextField();
		StudentID_textField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 22));
		StudentID_textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 630, GroupLayout.PREFERRED_SIZE)
					.addGap(83)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(Login_button, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(27))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(UserType_label)
							.addComponent(FirstName_Label, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addComponent(LastName_Label, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addComponent(StudentID_Label, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addComponent(EmailId_Label, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
							.addComponent(Password_Label)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
										.addComponent(EmailID_textField, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
										.addComponent(StudentID_textField, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
										.addComponent(LastName_textField, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(FirstName_textField, Alignment.LEADING)
									.addComponent(UserType_comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(20))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(40)
							.addComponent(Register_Button)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(79, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(UserType_label, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(UserType_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(FirstName_Label, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(FirstName_textField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(LastName_Label, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(LastName_textField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(StudentID_Label, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(StudentID_textField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(EmailId_Label)
						.addComponent(EmailID_textField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Password_Label)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(Login_button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(Register_Button, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
					.addGap(55))
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
		);
		
		JLabel lblNewLabel = new JLabel("SELS");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 99));
		lblNewLabel.setBounds(137, 234, 306, 125);
		desktopPane.add(lblNewLabel);
		contentPane.setLayout(gl_contentPane);
	}
}
