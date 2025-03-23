/**
 * Change Password for changing the password of each student in their own profile
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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Change_Password extends JFrame {

	// initializing and declaring variables
	private JPanel contentPane;
	private JPasswordField passwordField;
	Connection Connection = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	public String Email;
	public String Password;
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
	 * Create the frame.
	 */
	public Change_Password(String StudentNumber) {
		setTitle("Change Password");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 189);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel Pass_Label = new JLabel("Enter New Password");
		Pass_Label.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		// button for updating the password in the db
		JButton Submit_Button = new JButton("Submit");
		Submit_Button.setForeground(Color.WHITE);
		Submit_Button.setBackground(Color.BLUE);
		Submit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					//storing the entered text field value in in a variable
					String pstr = passwordField.getText();
					
					// try catch statement to update the value of password based on entered email
	                try {
	                    
	                    String query = "Update login set Password=? where Student_Id= '"+StudentNumber+"'";
						Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");
						pst = connection.prepareStatement(query);
	                    pst.setString(1, pstr);
	                    
	                    if (pstr.equals(""))
	                    {
	                    	JOptionPane.showMessageDialog(null, "Please enter the new password!");
	                    }
	                    
	                    else
	                    {
	                    pst.executeUpdate();
	                    JOptionPane.showMessageDialog(Submit_Button, "Password has been successfully changed");

	                    }
	                }
	                catch (SQLException e1) 
	                {
	                    e1.printStackTrace();
	                }
			}
		});
		Submit_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		
		passwordField = new JPasswordField();
		
		//button to redirect student dashboard
		JButton Back_Button = new JButton("Back");
		Back_Button.setForeground(Color.WHITE);
		Back_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Student_Dashboard SD = new Student_Dashboard(StudentNumber);
				SD.setVisible(true);
				dispose();
			}
		});
		Back_Button.setBackground(Color.BLUE);
		Back_Button.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 17));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(Pass_Label, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(59)
					.addComponent(Back_Button, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
					.addComponent(Submit_Button, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(56))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(51)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Pass_Label)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Submit_Button, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(Back_Button, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
