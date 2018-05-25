package dylingfone;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import javax.swing.JProgressBar;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window.Type;
import javax.swing.JPanel;
import java.awt.image.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;



public class frame extends JFrame implements ActionListener {

	private JFrame frame;
	private JPanel lockScreen;
	private JPanel homeScreen;
	private JPanel Backpanel;
	private JPanel contacts;
	private JPanel gallery;
	public String navState = "appName" ;
	private JButton galleryButton;
	private JButton contactButton;
	private JButton button;
	private JPanel blackPanel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame window = new frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setIgnoreRepaint(true);
		frame.getContentPane().setForeground(UIManager.getColor("CheckBox.darkShadow"));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setType(Type.UTILITY);
		frame.setResizable(false); // set resizable to true if you want to remove the box arround the frame
		frame.setBounds(100, 100, 368, 754);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(false);
		// frame.setBackground(new Color(0, 0, 0,0)); // Uncomment to set the background transpatrent
		frame.getContentPane().setLayout(null);

		/**
		 * Generates the image of the iPhone
		 */
		
		blackPanel = new JPanel();
		blackPanel.setBackground(Color.BLACK);
		blackPanel.setLayout(null);
		blackPanel.setBounds(23, 87, 321, 553);
		frame.getContentPane().add(blackPanel);
	
		
		JButton homeBtn = new JButton("back");
		homeBtn.setBounds(144, 638, 91, 68);
		frame.getContentPane().add(homeBtn);
		
		Image backBtnIcon = new ImageIcon(this.getClass().getResource("/Home-Button.png")).getImage();
		Image scaledBackBtnIcon = backBtnIcon.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
		homeBtn.setIcon(new ImageIcon(scaledBackBtnIcon) );
		
		homeBtn.setBorderPainted(false);
		homeBtn.setFocusPainted(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.addActionListener(this);
		homeBtn.setActionCommand("goToHome");
		
		
		
		
		JLabel label = new JLabel();
		label.setBounds(0, -35, 393, 794);
		Image Outline = new ImageIcon(this.getClass().getResource("/iPhone-frame.png")).getImage();
		label.setIcon(new ImageIcon(Outline));

		frame.getContentPane().add(label);

		/**
		 * generates the pannels
		 * 
		 */
		
		initializeHomeScreen();	

	}
	
	

	private void initializeHomeScreen() {
		
		lockScreen = new JPanel();
		lockScreen.setBounds(23, 88, 320, 553);
		frame.getContentPane().add(lockScreen);
		lockScreen.setLayout(null);
		
		
		

		JButton btnEnter = new JButton("Enter");
		
				btnEnter.setBounds(105, 515, 97, 25);
				lockScreen.add(btnEnter);
				btnEnter.addActionListener(this);
				btnEnter.setActionCommand("Unlock");

		
				
				homeScreen = new JPanel();
				homeScreen.setBackground(Color.DARK_GRAY);
				homeScreen.setBounds(23, 88, 320, 553);
				frame.getContentPane().add(homeScreen);
				GridBagLayout gbl_homeScreen = new GridBagLayout();
				gbl_homeScreen.columnWidths = new int[] {45, 45, 45, 45, 45, 45};
				gbl_homeScreen.rowHeights = new int[]{38, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				gbl_homeScreen.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
				gbl_homeScreen.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				homeScreen.setLayout(gbl_homeScreen);

			
				
				Image contactIcon = new ImageIcon(this.getClass().getResource("/contactIcon.png")).getImage();
				Image scaledContactIcon = contactIcon.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
				
	
				contactButton = new JButton();
				contactButton.setBackground(Color.BLACK);
				contactButton.setIcon(new ImageIcon(scaledContactIcon) );
				
				contactButton.setBorderPainted(false);
				contactButton.setFocusPainted(false);
				contactButton.setContentAreaFilled(false);
				contactButton.addActionListener(this);
				contactButton.setRolloverEnabled(false);
				contactButton.setActionCommand("openContacts");
				
				GridBagConstraints gbc_contactButton = new GridBagConstraints();
				gbc_contactButton.insets = new Insets(0, 0, 5, 5);
				gbc_contactButton.gridx = 1;
				gbc_contactButton.gridy = 1; 
				homeScreen.add(contactButton, gbc_contactButton);
			
				
				
				galleryButton = new JButton();
				Image galleryIcon = new ImageIcon(this.getClass().getResource("/galleryIcon.png")).getImage();
				Image scaledGalleryIcon = galleryIcon.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
				
				galleryButton.setIcon(new ImageIcon(scaledGalleryIcon) );
				GridBagConstraints gbc_galleryButton = new GridBagConstraints();
				
				galleryButton.setBorderPainted(false);
				galleryButton.setFocusPainted(false);
				galleryButton.setContentAreaFilled(false);
				galleryButton.setRolloverEnabled(false);
				galleryButton.addActionListener(this);
				galleryButton.setActionCommand("openGallery");
				
				gbc_galleryButton.insets = new Insets(0, 0, 5, 5);
				gbc_galleryButton.gridx = 2;
				gbc_galleryButton.gridy = 1;
				homeScreen.add(galleryButton, gbc_galleryButton);
		
			
		initializeBackPannel(navState);
		initializecontacts();
		initializegallery();	
	
		
	}

	private void initializeBackPannel(String args) {
		
		Backpanel = new JPanel();
		Backpanel.setBounds(23, 88, 320, 44);
		frame.getContentPane().add(Backpanel);
		Backpanel.setBackground(Color.GRAY);
		Backpanel.setLayout(null);
		
		Backpanel.setVisible(false);
		
		JButton btnBack = new JButton("<-");
		btnBack.setBounds(12, 9, 50, 26);
		Backpanel.add(btnBack);
		btnBack.addActionListener(this);
		btnBack.setActionCommand("back");
		
			JLabel label_1 = new JLabel(args);
			label_1.setBounds(130, 14, 56, 16);
			Backpanel.add(label_1);
	
		
	}
	

	private void initializecontacts() {
		
		contacts = new JPanel();
		contacts.setBounds(23, 88, 320, 553);
		frame.getContentPane().add(contacts);
		contacts.setBackground(Color.RED);
		contacts.setVisible(false);
		
		

	}
	
	
	private void initializegallery() {
		
	
		gallery = new JPanel();
		gallery.setBounds(23, 88, 320, 553);
		frame.getContentPane().add(gallery);
		gallery.setBackground(Color.CYAN);
		
		button = new JButton("");
		button.setBackground(Color.BLACK);
	
		button.setBounds(254, 3, 47, 8);
		
		button.setBorderPainted(false);
		
		
				frame.getContentPane().add(button);
		gallery.setVisible(false);
			
		
		
	}
	

	

	public void actionPerformed(ActionEvent ae) {

		String action = ae.getActionCommand();
		
		//Component[] components = frame.getContentPane().getComponents();		
		//Component[] homeScreenComponenets = homeScreen.getComponents();	
		
		
		System.out.println();

		if (action.equals("Unlock")) {

			navState = "home";
			
			
			lockScreen.setVisible(false);
			
			
		}

		if (action.equals("openContacts")) {
			
			navState = "Contacts";
			Backpanel.repaint();
			
			homeScreen.setVisible(false);
			contacts.setVisible(true);
			Backpanel.setVisible(true);
			


		}
		
		if (action.equals("openGallery")) {
			
			navState = "Gallery";
			Backpanel.repaint();
			
			homeScreen.setVisible(false);
			gallery.setVisible(true);
			Backpanel.setVisible(true);
			

		}
		
	if (action.equals("goToHome") ) {
		
			homeScreen.setVisible(true);
			contacts.setVisible(false);
			gallery.setVisible(false);
			Backpanel.setVisible(false);
			
		}
	
	if (action.equals("back") ) {
		
		homeScreen.setVisible(true);
		contacts.setVisible(false);
		gallery.setVisible(false);
		Backpanel.setVisible(false);
		
	}
		
		
		
	}

	public Color getFrameContentPaneBackground() {
		return frame.getContentPane().getBackground();
	}

	public void setFrameContentPaneBackground(Color background) {
		frame.getContentPane().setBackground(background);
	}
}
