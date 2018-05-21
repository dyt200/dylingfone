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

public class frame extends JFrame implements ActionListener {

	private JFrame frame;
	private JPanel lockScreen;
	private JPanel homeScreen;
	private JPanel Backpanel;
	private JPanel contacts;
	private JPanel gallery;
	

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
		homeScreen.setBounds(23, 88, 320, 553);
		frame.getContentPane().add(homeScreen);
		homeScreen.setLayout(null);
		
		
		JButton btnContacts = new JButton("c");
		btnContacts.setBounds(38, 38, 45, 25);
		homeScreen.add(btnContacts);
		btnContacts.addActionListener(this);
		btnContacts.setActionCommand("openContacts");
		
		JButton btnGallery = new JButton("g");
		btnGallery.setBounds(84, 38, 45, 25);
		homeScreen.add(btnGallery);
		btnGallery.addActionListener(this);
		btnGallery.setActionCommand("openGallery");
		
		initializeBackPannel();
		initializecontacts();
		initializegallery();	
	
		
	}
	
	private void initializeBackPannel() {
		
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
		
			JLabel label_1 = new JLabel("appName");
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
		gallery.setVisible(false);
			
		
		
	}
	

	

	public void actionPerformed(ActionEvent ae) {

		String action = ae.getActionCommand();
		
		//Component[] components = frame.getContentPane().getComponents();		
		//Component[] homeScreenComponenets = homeScreen.getComponents();	
		
		
		System.out.println();

		if (action.equals("Unlock")) {

			lockScreen.setVisible(false);
			
		}

		if (action.equals("openContacts")) {
			

			homeScreen.setVisible(false);
			contacts.setVisible(true);
			Backpanel.setVisible(true);


		}
		
		if (action.equals("openGallery")) {
			
			homeScreen.setVisible(false);
			gallery.setVisible(true);
			Backpanel.setVisible(true);

		}
		
	if (action.equals("back")) {
		
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
