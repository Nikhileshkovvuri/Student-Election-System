/**
 * Create Event page for creating election for students
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

public class Create_Events extends JFrame {

	//Initializing and declaring variables
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField StartDate_textField;
	Connection Connection = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	private JTextField EndDate_textField;
	public JComboBox Club_comboBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Create_Events frame = new Create_Events();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * @param constructor to fetch and display club names in combo box
	 */
	public void Club_data()
	{
		try
		{
			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");
			String query = "select * from Club";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				Club_comboBox.addItem(rs.getString("Club_Name"));
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
	public Create_Events() {
		setTitle("Create Event");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 464);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 0, 255));
		
		JLabel ClubName_label = new JLabel("Club Name");
		ClubName_label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		JLabel ClubDescription_label = new JLabel("Club Description");
		ClubDescription_label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		JLabel Startdate_Label = new JLabel("Start Date");
		Startdate_Label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		StartDate_textField = new JTextField();
		StartDate_textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		StartDate_textField.setColumns(10);
		
		JTextArea Description_textArea = new JTextArea();
		Description_textArea.setTabSize(2);
		Description_textArea.setBackground(Color.WHITE);
		Description_textArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
		Description_textArea.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// button to create a entry of new club in to db and diplay in student pages
		JButton Create_Button = new JButton("CREATE");
		Create_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Declaring values in string variables to insert into db
				String Club = Club_comboBox.getSelectedItem().toString();	
				String Description = Description_textArea.getText();
				String StartDate= StartDate_textField.getText();
				String EndDate= EndDate_textField.getText();
				
				/*
				 * If else statement use used to make sure the user fill every field
				 */
				   if (Club.equals("Select From List"))
				   {
					   JOptionPane.showMessageDialog(null, "Please Select the Club");
				   }
				else if (Description.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the Description");
				}
				
				else if(StartDate.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the Start Date with the format YYYY-MM-DD");
				}
				
				else if(EndDate.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the End Date with the format YYYY-MM-DD");
				}
				
				   // try catch statement used to insert the stored stirng values in db
					try{
					
					String query = "INSERT INTO `events`(`Club_Name`, `Description`, `StartDate`, `EndDate`) VALUES(?,?,?,?) ";
					Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");	
					pst=Connection.prepareStatement(query);
					pst.setString(1, Club);
					pst.setString(2, Description);	
					pst.setString(3, StartDate);
					pst.setString(4, EndDate);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Event Created Sucessfully");
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
		
		JLabel ENDdate_Label = new JLabel("End Date");
		ENDdate_Label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		EndDate_textField = new JTextField();
		EndDate_textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		EndDate_textField.setColumns(10);
		
	 Club_comboBox = new JComboBox();
		Club_comboBox.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		Club_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select From List"}));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(ClubName_label, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(ClubDescription_label, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addComponent(Startdate_Label, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addComponent(ENDdate_Label, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(Club_comboBox, 0, 222, Short.MAX_VALUE)
								.addComponent(EndDate_textField, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
								.addComponent(Description_textArea, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
								.addComponent(StartDate_textField, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
							.addGap(28))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Create_Button, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addGap(68))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(Club_comboBox)
						.addComponent(ClubName_label, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addComponent(Description_textArea, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(62)
							.addComponent(ClubDescription_label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(Startdate_Label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(ENDdate_Label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(EndDate_textField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
						.addComponent(StartDate_textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(Create_Button, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		
		// button to redirect to admin dashboard
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
				Create_Election CE = new Create_Election();
				CE.setVisible(true);
				dispose();
			}
		});
		CreateElection_Button.setForeground(Color.WHITE);
		CreateElection_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		CreateElection_Button.setBackground(new Color(51, 0, 255));
		CreateElection_Button.setBorder(null);
		
		// button to redirect to election result page
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
		
		// button to redirect to announce result page
		JButton AnounceResult_Button = new JButton("Anounce Result");
		AnounceResult_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		AnounceResult_Button.setForeground(Color.WHITE);
		AnounceResult_Button.setBackground(new Color(51, 0, 255));
		AnounceResult_Button.setBorder(null);
		AnounceResult_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Anounce_Result AR = new Anounce_Result();
				AR.setVisible(true);
				dispose();
			}
		});
		
		// button to redirect to create events page
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
		
		// button to redirect to  admin dashboard
		JButton Back_Button = new JButton("BACK");
		Back_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create_Club AD = new Create_Club();
				AD.setVisible(true);
				dispose();
			}
		});
		Back_Button.setForeground(Color.WHITE);
		Back_Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		Back_Button.setBorder(null);
		Back_Button.setBackground(new Color(51, 0, 255));
		
		//Nutton for log out of the portal
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
                	Create_Events SD= new Create_Events();
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
					.addComponent(CreateClub_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
					.addGap(22))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(24)
					.addComponent(Back_Button, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(38, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(CreateEvent_Button, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(Logout_Button, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(2)
					.addComponent(CreateElection_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(ElectionResult_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(AnounceResult_Button, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(Back_Button, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(CreateClub_Button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(CreateEvent_Button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(CreateElection_Button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(ElectionResult_Button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(AnounceResult_Button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(Logout_Button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		//calling constructor 
		Club_data();
	}
}