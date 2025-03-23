/**
 * Admin Dashboard for creating clubs
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

public class Create_Club extends JFrame {
	

	// Initializing and declaring variables 
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField ClubId_textField;
	private JTextField ClubName_textField;
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
					Create_Club frame = new Create_Club();
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
	public Create_Club() {
		setTitle("Create Club");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 0, 255));
		
		JLabel ClubId_Label = new JLabel("Club ID");
		ClubId_Label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		JLabel ClubName_label = new JLabel("Club Name");
		ClubName_label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		JLabel ClubDescription_label = new JLabel("Club Description");
		ClubDescription_label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		JLabel ClubStatus_Label = new JLabel("Club Status");
		ClubStatus_Label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		ClubId_textField = new JTextField();
		ClubId_textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		ClubId_textField.setColumns(10);
		
		ClubName_textField = new JTextField();
		ClubName_textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		ClubName_textField.setColumns(10);
		
		JTextArea Description_textArea = new JTextArea();
		Description_textArea.setTabSize(2);
		Description_textArea.setBackground(Color.WHITE);
		Description_textArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
		Description_textArea.setBorder(BorderFactory.createLineBorder(Color.black));
		JComboBox Status_comboBox = new JComboBox();
		Status_comboBox.setForeground(new Color(51, 0, 255));
		Status_comboBox.setBackground(Color.WHITE);
		Status_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select From List", "Active", "Inactive"}));
		Status_comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		//Create button for creating entered club details
		JButton Create_Button = new JButton("CREATE");
		Create_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// creating variable to store the entered text field values in them
				  String ClubName =  ClubName_textField.getText();
				   String ClubID = ClubId_textField.getText();	
				   String Description = Description_textArea.getText();
				   String Status = Status_comboBox.getSelectedItem().toString();
				
				  /*
				   * if else condition use to make the user fill every details compulsory
				   */
				   if (ClubName.equals(""))
				   {
					   JOptionPane.showMessageDialog(null, "Please Enter the Club Name");
				   }
				else if (ClubID.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the Club ID");
				}
				
				else if(Description.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the Description");
				}
				
				else if(Status.equals("Select From List"))
				{
					JOptionPane.showMessageDialog(null, "Please Select the Status");
				}
				
				   /*
				    * try catch statement to insert the stored values in to the database
				    */
					try{
					
					String query = "INSERT INTO `club`(`Club_Name`, `Club_Id`, `Status`, `Description`) VALUES(?,?,?,?) ";
					Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");	
					pst=Connection.prepareStatement(query);
					pst.setString(1, ClubName);
					pst.setString(2, ClubID);	
					pst.setString(3, Status);
					pst.setString(4, Description);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Club Created Sucessfully");
					}
				
					catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
					}
			}
		});
		Create_Button.setForeground(Color.WHITE);
		Create_Button.setBackground(new Color(51, 0, 255));
		Create_Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(ClubName_label, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(ClubDescription_label, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
								.addComponent(ClubId_Label, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(ClubStatus_Label, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(Description_textArea, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
										.addComponent(ClubName_textField)
										.addComponent(ClubId_textField))
									.addGap(14))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(Status_comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addContainerGap())))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(Create_Button, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addGap(30))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ClubId_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ClubId_Label))
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ClubName_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ClubName_label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addComponent(Description_textArea, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(59)
							.addComponent(ClubDescription_label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ClubStatus_Label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(Status_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(Create_Button, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
		);
		
		// button for redirecting to create club page
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
		
		// button for redirecting user to create election page
		JButton CreateElection_Button = new JButton("Create Elections");
		CreateElection_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create_Election CE = new Create_Election();
				CE.setVisible(true);
				dispose();
			}
		});
		CreateElection_Button.setForeground(Color.WHITE);
		CreateElection_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		CreateElection_Button.setBackground(new Color(51, 0, 255));
		CreateElection_Button.setBorder(null);
		
		// button for redirecting user to election result page
		JButton ElectionResult_Button = new JButton("Election Results");
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
		
		// button for redirecting user to announce result page
		JButton AnnounceResult_Button = new JButton("Anounce Result");
		AnnounceResult_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		AnnounceResult_Button.setForeground(Color.WHITE);
		AnnounceResult_Button.setBackground(new Color(51, 0, 255));
		AnnounceResult_Button.setBorder(null);
		AnnounceResult_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Anounce_Result AR = new Anounce_Result();
				AR.setVisible(true);
				dispose();
			}
		});
		
		// button for redirecting user to create events page
		JButton CreateEvent_Button = new JButton("Create Events");
		CreateEvent_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create_Events CE = new Create_Events();
				CE.setVisible(true);
				dispose();
			}
		});
		CreateEvent_Button.setForeground(Color.WHITE);
		CreateEvent_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		CreateEvent_Button.setBorder(null);
		CreateEvent_Button.setBackground(new Color(51, 0, 255));
		
		//Button for log out to the portal
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
                	Create_Club SD= new Create_Club();
                	SD.setVisible(true);
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
						.addComponent(CreateClub_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(CreateEvent_Button, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
						.addComponent(CreateElection_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(ElectionResult_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(AnnounceResult_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(Logout_Button, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(24)
					.addComponent(CreateClub_Button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(CreateEvent_Button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(CreateElection_Button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(ElectionResult_Button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(AnnounceResult_Button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(Logout_Button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

	}
}