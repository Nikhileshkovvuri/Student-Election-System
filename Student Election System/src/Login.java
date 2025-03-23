/**
 * Login page for Logining into portal for admin and for students
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

public class Login extends JFrame {
	// Initializing and declaring variable and components
	private JPanel contentPane;
	private JTextField StudentNumber_textfield;
	private int attempt = 1;
	private JPasswordField passwordField;
	private JComboBox UserType_comboBox;
	Connection connection = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1192, 604);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel StudentNumber_Label = new JLabel("Admin/Student Number");
		StudentNumber_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		
		JLabel Password_Label = new JLabel("PASSWORD");
		Password_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		
		StudentNumber_textfield = new JTextField();
		StudentNumber_textfield.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 22));
		StudentNumber_textfield.setColumns(10);
		
		// button for redirecting user to Register Page
		JButton Register_Button = new JButton("REGISTER");
		Register_Button.setForeground(SystemColor.text);
		Register_Button.setBackground(Color.BLUE);
		Register_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 16));
		Register_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Student_Registration second = new Student_Registration();
				second.setVisible(true);
				dispose();
			}
		});
		
		// button for redirecting user to Student Dashboard Page
		JButton Login_button = new JButton("LOGIN");
		Login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Initializing and declaring variables inside a action event
				String StudentNumber =StudentNumber_textfield.getText();
				String Passwords =passwordField.getText();
				
				// try catch state to connect to database and validate Student and admin number  and password
				try 
				{
					// query for validating given Number and password from database
					String query = "Select * from Login where Student_Id = ? and Password= ? and User_Type=?";
					// connection is a variable which is used for connecting to database
					connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");
					// prepared statement used for executing the given query which connect to database 
					pst = connection.prepareStatement(query);
					// stores the enter field value and validate in database if the entered values exist
					pst.setString(1,StudentNumber);
					pst.setString(2,Passwords);
					pst.setString(3,String.valueOf(UserType_comboBox.getSelectedItem()));
					rs= pst.executeQuery();
				
					//if else condition to validate from the selected combo box if the user selected admin or password
					if(rs.next())
						
					{
						// redirects to admin page if user entered credentials is validated to admin credentials
						
						if (UserType_comboBox.getSelectedIndex()==1)
						{
							Create_Club AD = new Create_Club();
							AD.setVisible(true);
							dispose();
							
						}
						
						// redirects to student dashboard page if enter credentials is validates with student credentials
						else
						{
							Student_Dashboard SD = new Student_Dashboard(StudentNumber);
							SD.setVisible(true);
							dispose();	
						}
						
					}
					
					// displaying error message if the credentials are wrong
					else
					{
                        JOptionPane.showMessageDialog(null, "Wrong Email or Password");
					}
				}
				
				catch(SQLException ex)
				{
					JOptionPane.showMessageDialog(null,ex.getMessage());
				}
			}
		});
		
		
		Login_button.setForeground(SystemColor.text);
		Login_button.setBackground(Color.BLUE);
		Login_button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 16));
	
		
		JLabel UserType_label = new JLabel("USER TYPE");
		UserType_label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 22));
		
		//displaying combo box for asking the user to select if he/she is either a admin or student.
		UserType_comboBox = new JComboBox();
		UserType_comboBox.setBackground(SystemColor.control);
		UserType_comboBox.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 22));
		UserType_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select From List", "ADMIN", "STUDENT"}));
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.BLUE);
		
		// creating a password field for entering password		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 22));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 630, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(UserType_label, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
								.addComponent(Password_Label)
								.addComponent(StudentNumber_Label, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Register_Button, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Login_button, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addComponent(StudentNumber_textfield, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(passwordField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(UserType_comboBox, 0, 222, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(196)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(UserType_label, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(UserType_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(StudentNumber_textfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(StudentNumber_Label))
							.addGap(40)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(Password_Label)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(53)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(Login_button, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addComponent(Register_Button, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)))
						.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 559, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("SELS");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 99));
		lblNewLabel.setBounds(137, 234, 306, 125);
		desktopPane.add(lblNewLabel);
		contentPane.setLayout(gl_contentPane);
	}
}
