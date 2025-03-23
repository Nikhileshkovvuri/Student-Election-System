/**
 * Announce Result page for announce their president and vicepresident of each candidate for their club.
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

public class Anounce_Result extends JFrame {

	// Initializing and declaring variables
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection Connection = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	private JTextField Votes_textField;
	private JTextField Winner_textField;
	private JComboBox Role_comboBox;
	private JComboBox Club_comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Anounce_Result frame = new Anounce_Result();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	 * @param constructor for displaying club names in the dropdown 
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
	public Anounce_Result() {
		setTitle("Announce Result");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 0, 255));
		
		JLabel ClubName_label = new JLabel("Club Name");
		ClubName_label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		JLabel NominatedRole_label = new JLabel("Nominated Role");
		NominatedRole_label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		JLabel ClubStatus_Label = new JLabel("Total Votes");
		ClubStatus_Label.setFont(new Font("SansSerif", Font.BOLD, 16));
		Role_comboBox = new JComboBox();
		Role_comboBox.setForeground(new Color(51, 0, 255));
		Role_comboBox.setBackground(Color.WHITE);
		Role_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select From List", "President ", "Vice President"}));
		Role_comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		// button for announcing result which get update in db
		JButton Anounce_Button = new JButton("Announce");
		Anounce_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// storing combobox and text field values in db
				String ClubName = Club_comboBox.getSelectedItem().toString();
				String Role = Role_comboBox.getSelectedItem().toString();
				String Winner = Winner_textField.getText();
				String Votes =  Votes_textField.getText();
				
				/*
				 *  if else condition to make sure that user fills all the info without leaving blank
				 */
				  if (ClubName.equals("Select From List"))
				   {
					   JOptionPane.showMessageDialog(null, "Please select the Club Name");
				   }
				else if (Role.equals("Select From List"))
				{
					JOptionPane.showMessageDialog(null, "Please select the Role");
				}
				
				else if(Winner.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the Winner Name");
				}
				
				else if(Votes.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the total Votes");
				}
				
				  /*
				   * try catch statement to insert the stored values in database
				   */
					try{
					
					String query = "INSERT INTO `club_result`(`Club_Name`, `Nominated_Role`, `Student_Name`, `Votes`) VALUES(?,?,?,?) ";
					Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");	
					pst=Connection.prepareStatement(query);
					pst.setString(1, ClubName);
					pst.setString(2, Role);	
					pst.setString(3, Winner);
					pst.setString(4, Votes);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Anounced Sucessfully");
					}
				
					catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
					}
			}
		});
		Anounce_Button.setForeground(Color.WHITE);
		Anounce_Button.setBackground(new Color(51, 0, 255));
		Anounce_Button.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		JLabel Winner_Label = new JLabel("Winner");
		Winner_Label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		Votes_textField = new JTextField();
		Votes_textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		Votes_textField.setColumns(10);
		
		Winner_textField = new JTextField();
		Winner_textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		Winner_textField.setColumns(10);
		
		Club_comboBox = new JComboBox();
		Club_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select From List"}));
		Club_comboBox.setForeground(new Color(51, 0, 255));
		Club_comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		Club_comboBox.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(ClubStatus_Label, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addComponent(NominatedRole_label, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
								.addComponent(Winner_Label, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addComponent(ClubName_label, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(Club_comboBox, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(Votes_textField, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
									.addComponent(Winner_textField, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
									.addComponent(Role_comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(82)
							.addComponent(Anounce_Button, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(ClubName_label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(Club_comboBox, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(NominatedRole_label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(Role_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Winner_textField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(Winner_Label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Votes_textField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(ClubStatus_Label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addComponent(Anounce_Button, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(59, Short.MAX_VALUE))
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		
		// button for redirecting user to create club page
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
		
		//Button for logout of portal
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
                	Anounce_Result SD= new Anounce_Result();
                	SD.setVisible(true);
                	dispose();
                }
			}
		});
		Logout_Button.setForeground(Color.WHITE);
		Logout_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		Logout_Button.setBorder(null);
		Logout_Button.setBackground(new Color(51, 0, 255));
		
		JButton Back_Button = new JButton("Back");
		Back_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		Back_Button.setForeground(Color.WHITE);
		Back_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		Back_Button.setBorder(null);
		Back_Button.setBackground(new Color(51, 0, 255));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(CreateElection_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(ElectionResult_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(AnounceResult_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(Logout_Button, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
						.addComponent(CreateEvent_Button, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
						.addComponent(CreateClub_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(Back_Button, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(23, Short.MAX_VALUE)
					.addComponent(Back_Button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(CreateClub_Button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(CreateEvent_Button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(CreateElection_Button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(ElectionResult_Button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(AnounceResult_Button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(Logout_Button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(36))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	
		// calling constructor to display club names
		Club_data();
	}
}