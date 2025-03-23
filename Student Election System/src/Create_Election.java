/**
 * Create Election page for creating new election for admin
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
import java.awt.Window.Type;

public class Create_Election extends JFrame {
	
	// Initializing and declaring variables
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Electiondate_textField;
	Connection Connection = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	public JComboBox Club_comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Create_Election frame = new Create_Election();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * @param constructor for displaying club list in combo box
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
	public Create_Election() {
		setTitle("Create Election");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 0, 255));
		
		JLabel ClubId_Label = new JLabel("Club Name");
		ClubId_Label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		JLabel Election_Label = new JLabel("Election date");
		Election_Label.setFont(new Font("SansSerif", Font.BOLD, 16));
		
		JLabel Vote_label = new JLabel(" Nominee For");
		Vote_label.setFont(new Font("SansSerif", Font.BOLD, 16));
		JComboBox Nominee_comboBox = new JComboBox();
		Nominee_comboBox.setForeground(new Color(51, 0, 255));
		Nominee_comboBox.setBackground(Color.WHITE);
		Nominee_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select From List", "President", "VicePresident"}));
		Nominee_comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		// button to redirect to add entry of new election in db
		JButton Create_Button = new JButton("CREATE");
		Create_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Storing selected/entered values in string values 
				String Club = Club_comboBox.getSelectedItem().toString();	
				String Electiondate= Electiondate_textField.getText();
				String Nominee= Nominee_comboBox.getSelectedItem().toString();
				
				/*
				 * if else state to make sure user fill every data without fail
				 */
				   if (Club.equals("Select From List"))
				   {
					   JOptionPane.showMessageDialog(null, "Please Select the Club");
				   }
				else if (Electiondate.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the Date in this format YYYY-MM-DD");
				}
				
				else if(Nominee.equals("Select From List"))
				{
					JOptionPane.showMessageDialog(null, "Please Enter the Last Name");
				}
				
				/*
				 * try catch statement to insert stored string values in db
				 */
				try{
					
					String query = "INSERT INTO `election`( `Club_Name`, `Election_Date`, `Roles`) VALUES (?,?,?) ";
					Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ses","root", "");	
					pst=Connection.prepareStatement(query);
					pst.setString(1, Club);	
					pst.setString(2, Electiondate);
					pst.setString(3, Nominee);
					
					 if (pst.executeUpdate()>0)
					 {
						 JOptionPane.showMessageDialog(null, "Created Sucessfully");
					 }
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
		
		Electiondate_textField = new JTextField();
		Electiondate_textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		Electiondate_textField.setColumns(10);
		
		Club_comboBox = new JComboBox();
		Club_comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select From List"}));
		Club_comboBox.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(Election_Label, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
								.addComponent(ClubId_Label, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(Vote_label, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
							.addGap(47)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(Club_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(81, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(Nominee_comboBox, Alignment.LEADING, 0, 180, Short.MAX_VALUE)
										.addComponent(Electiondate_textField, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
									.addGap(81))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(101)
							.addComponent(Create_Button, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ClubId_Label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(Club_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(66)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Election_Label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(Electiondate_textField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(78)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(Vote_label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(Nominee_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addComponent(Create_Button, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(48))
		);
		
		// button to redirect to Create club
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
		
		// button to redirect to  create election page
		JButton CreateElection_Button = new JButton("Create Elections");
		CreateElection_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create_Election CEs = new Create_Election();
				CEs.setVisible(true);
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
		JButton CreateEvents_Button = new JButton("Create Events");
		CreateEvents_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create_Events CE = new Create_Events();
				CE.setVisible(true);
				dispose();
			}
		});
		CreateEvents_Button.setForeground(Color.WHITE);
		CreateEvents_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		CreateEvents_Button.setBorder(null);
		CreateEvents_Button.setBackground(new Color(51, 0, 255));
		
		//Button for log out of the portal
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
                	Create_Election CE= new Create_Election();
                	CE.setVisible(true);
                	dispose();
                }
			}
		});
		Logout_Button.setForeground(Color.WHITE);
		Logout_Button.setFont(new Font("SansSerif", Font.BOLD, 16));
		Logout_Button.setBorder(null);
		Logout_Button.setBackground(new Color(51, 0, 255));
		
		JButton Back_Button = new JButton("Back");
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
						.addComponent(Logout_Button, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
						.addComponent(Back_Button, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
						.addComponent(CreateClub_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(ElectionResult_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(AnounceResult_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(CreateElection_Button, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
						.addComponent(CreateEvents_Button, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(Back_Button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(CreateClub_Button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(CreateEvents_Button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(CreateElection_Button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(ElectionResult_Button, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(AnounceResult_Button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(Logout_Button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		//calls contrctuctor which contains club names
		Club_data();
	}
}
