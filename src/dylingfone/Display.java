package dylingfone;

import javax.swing.*;
import javafx.*;
import javax.swing.event.ChangeEvent;

import javafx.scene.control.ScrollPane;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.awt.event.*;



public class Display extends JFrame implements ActionListener {
	
   
	private JFrame frame;
	private JPanel lockScreen;
	private JPanel homeScreen;
	private JPanel Backpanel;
	private JPanel contacts;
	private JPanel gallery;
	public String precNavState;
	private JButton galleryButton;
	private JButton contactButton;
	private JButton lockButton;
	private JPanel blackPanel;
	private JPanel contactDetails;
	private JLabel hourLabel;
	private String timeStamp;
	private boolean isOff = true;
	private boolean isLocked = true;

	private JPanel activePanel;
	
	Contacts contactsObj = new Contacts();	
	Contact[] array = contactsObj.getContactList();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display window = new Display();
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
	public Display() {

		generateSkeleton();

	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void generateSkeleton() {

		frame = new JFrame();
		frame.getContentPane().setIgnoreRepaint(true);
		//frame.getContentPane().setForeground(UIManager.getColor("CheckBox.darkShadow"));
		frame.getContentPane().setBackground(new Color(0, 0, 0, 0));
		frame.setType(Type.UTILITY);
		frame.setResizable(false); // set resizable to true if you want to remove the box arround the frame
		frame.setBounds(100, 100, 368, 754);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0, 0,0,0)); // Uncomment to set the background

		// transpatrent
		 frame.getContentPane().setLayout(null);

		/**
		 * Generates the image of the iPhone
		 */

		JButton homeBtn = new JButton();
		homeBtn.setBounds(139, 638, 91, 68);
		frame.getContentPane().add(homeBtn);
		Image backBtnIcon = new ImageIcon(this.getClass().getResource("/Home-Button.png")).getImage();
		Image scaledBackBtnIcon = backBtnIcon.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		homeBtn.setIcon(new ImageIcon(scaledBackBtnIcon));
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

		lockButton = new JButton();
		lockButton.setBackground(Color.BLACK);
		lockButton.setBounds(254, 3, 47, 8);
		frame.getContentPane().add(lockButton);

		generateBlackPannel();

		lockButton.addActionListener(this);
		lockButton.setActionCommand("Unlock");
		
		  FrameDragListener frameDragListener = new FrameDragListener(frame);
          frame.addMouseListener(frameDragListener);
          frame.addMouseMotionListener(frameDragListener);

	}
	



	private void generateBlackPannel() {

		blackPanel = new JPanel();
		blackPanel.setBackground(Color.BLACK);
		blackPanel.setLayout(null);
		blackPanel.setBounds(23, 87, 321, 553);
		frame.getContentPane().add(blackPanel);

		activePanel = blackPanel;

	}

	private void generatelockScreen() {
		
	
		lockScreen = new JPanel();
		lockScreen.setBounds(23, 88, 320, 553);
		lockScreen.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(lockScreen);
		lockScreen.setLayout(null);
		
		generateClock();
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0,100,0);
		slider.setBounds(56, 485, 190, 29);
		slider.setBackground(new Color(0, 0, 0,0));
		//slider.
		lockScreen.add(slider);
		

		slider.addChangeListener(new javax.swing.event.ChangeListener() {
			
			int unlockValue = 100;
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				 JSlider source = (JSlider)e.getSource();
				 
				 
				 if (unlockValue == source.getValue()) {
					 
					 frame.remove(activePanel);
					 
						generateHomeScreen();
						isLocked = false;

						frame.validate();
						frame.repaint();

					 
				 }
				
			}
		});
	
		
		activePanel = lockScreen;
		

	}
	
	private void generateClock() {
		

		hourLabel = new JLabel();
		setdate();
		hourLabel.setForeground(Color.WHITE);
		hourLabel.setBackground(Color.WHITE);
		hourLabel.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 78));
		hourLabel.setBounds(84, 58, 200, 69);
		lockScreen.add(hourLabel);

	}
	public void setdate() {
		
		ActionListener actionDate = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				java.util.Date myDate = new Date();
				hourLabel.setText(myDate.getHours() + ":" + myDate.getMinutes());
				
			}
		};
		
		new javax.swing.Timer(10, actionDate).start();
		
	}
	

	private void generateHomeScreen() {

		homeScreen = new JPanel();
		homeScreen.setBackground(Color.DARK_GRAY);
		homeScreen.setBounds(23, 88, 320, 553);
		frame.getContentPane().add(homeScreen);
		GridBagLayout gbl_homeScreen = new GridBagLayout();
		gbl_homeScreen.columnWidths = new int[] { 32, 64, 64, 64, 64, 32 };
		gbl_homeScreen.rowHeights = new int[] { 20, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_homeScreen.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_homeScreen.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		homeScreen.setLayout(gbl_homeScreen);

		Image contactIcon = new ImageIcon(this.getClass().getResource("/contactIcon.png")).getImage();
		Image scaledContactIcon = contactIcon.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);

		contactButton = new JButton();
		contactButton.setBackground(Color.BLACK);
		contactButton.setIcon(new ImageIcon(scaledContactIcon));

		contactButton.setBorderPainted(false);
		contactButton.setContentAreaFilled(false);
		contactButton.addActionListener(this);
		contactButton.setRolloverEnabled(false);
		contactButton.setActionCommand("openContacts");

		GridBagConstraints gbc_contactButton = new GridBagConstraints();
		gbc_contactButton.insets = new Insets(0, 0, 0, 0);
		gbc_contactButton.gridx = 1;
		gbc_contactButton.gridy = 1;
		homeScreen.add(contactButton, gbc_contactButton);

		galleryButton = new JButton();
		Image galleryIcon = new ImageIcon(this.getClass().getResource("/galleryIcon.png")).getImage();
		Image scaledGalleryIcon = galleryIcon.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);

		galleryButton.setIcon(new ImageIcon(scaledGalleryIcon));
		GridBagConstraints gbc_galleryButton = new GridBagConstraints();

		galleryButton.setBorderPainted(false);
		galleryButton.setFocusPainted(false);
		galleryButton.setContentAreaFilled(false);
		galleryButton.setRolloverEnabled(false);
		galleryButton.addActionListener(this);
		galleryButton.setActionCommand("openGallery");

		gbc_galleryButton.insets = new Insets(0, 0, 0, 0);
		gbc_galleryButton.gridx = 2;
		gbc_galleryButton.gridy = 1;
		homeScreen.add(galleryButton, gbc_galleryButton);

		activePanel = homeScreen;

	}

	/*
	 * private void generateBackPannel() {
	 * 
	 * Backpanel = new JPanel(); Backpanel.setBounds(23, 88, 320, 44);
	 * frame.getContentPane().add(Backpanel); Backpanel.setBackground(Color.GRAY);
	 * Backpanel.setLayout(null);
	 * 
	 * Backpanel.setVisible(false);
	 * 
	 * JButton btnBack = new JButton("<-"); btnBack.setBounds(12, 9, 50, 26);
	 * Backpanel.add(btnBack); btnBack.addActionListener(this);
	 * btnBack.setActionCommand("back");
	 * 
	 * JLabel label_1 = new JLabel(); label_1.setBounds(130, 14, 56, 16);
	 * Backpanel.add(label_1);
	 * 
	 * 
	 * }
	 */

	private void generatecontacts() {

		contacts = new JPanel();
		contacts.setBounds(23, 88, 315, 553);
		contacts.setLayout(getLayout());
		frame.getContentPane().add(contacts);
		contacts.setBackground(Color.RED);
		
		JPanel contactList = new JPanel();
		contactList.setBounds(23, 88, 315, 553);
		
		
		
	/*	for (int i = 0 ; i < 200 ; i++) {
			
			JPanel panTest = new JPanel();
			panTest.setPreferredSize(new Dimension(280, 75));
			panTest.setBackground(Color.RED);
			JLabel jlabel = new JLabel(Integer.toString(i));
			//jlabel.setFont(new Font("Verdana",1,20));
			panTest.add(jlabel);
			contactList.add(panTest);
			
			
		} */
		
		for (Contact contact : array) {
			
			
			JPanel panTest = new JPanel();
			panTest.setPreferredSize(new Dimension(280, 75));
			panTest.setBackground(Color.RED);
			JLabel jlabel = new JLabel(Integer.toString(contact.getId()) + contact.getFirstName() + contact.getLastName() );
			panTest.add(jlabel);
			
			panTest.addMouseListener(new java.awt.event.MouseAdapter() {
				
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                	
                    System.out.println("mouseClicked");
                    System.out.println(contact.getId());
                    
                	frame.remove(activePanel);
                    generateContactDetails(contact.getId());
                	frame.validate();
            		frame.repaint();
            
                    
                }});
			
			contactList.add(panTest);
			
			
		}
			
			contactList.setPreferredSize(new Dimension(280, (75*array.length)+ (array.length*5) + 5));
		
			JScrollPane scrollPane = new JScrollPane(contactList);
			//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);;
			
			contacts.add(scrollPane);

			
			
		activePanel = contacts;

	}
	
	private void generateContactDetails(int id) {
		
		contactDetails = new JPanel();
		contactDetails.setBounds(23, 88, 315, 553);
		contactDetails.setLayout(getLayout());
		frame.getContentPane().add(contactDetails);
		contactDetails.setBackground(Color.RED);
		
		for (Contact contact : array) {
			
			if (contact.getId() == id) {
				
				JLabel jlabel = new JLabel(Integer.toString(contact.getId()) + contact.getFirstName() + contact.getLastName() );
				contactDetails.add(jlabel);
				
				contact.getId();
				
						contact.getFirstName();
						contact.getLastName();
						contact.getBirthDate();
						contact.getEmail();
						contact.getTelMobile();
						contact.getTelHome();
						contact.getPic();
						System.out.println(contact.getPic());
				
			
			}
			
		}
		
		activePanel = contactDetails;
		
		
	}

	private void generategallery() {

		gallery = new JPanel();
		gallery.setBounds(23, 88, 315, 553);
		gallery.setLayout(getLayout());
		frame.getContentPane().add(gallery);
		gallery.setBackground(Color.CYAN);
		
		JPanel contactList = new JPanel();
		contactList.setBounds(23, 88, 315, 553);
		
		
		for (int i = 0 ; i < 200 ; i++) {
			
			JPanel panTest = new JPanel();
			panTest.setPreferredSize(new Dimension(65, 65));
			panTest.setBackground(Color.BLUE);
			contactList.add(panTest);
			
			
		}
			
			contactList.setPreferredSize(new Dimension(280, ((65*200) + (5*200))/4 + 5 ));
		
			JScrollPane scrollPane = new JScrollPane(contactList);
			//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			gallery.add(scrollPane);
		
	
		activePanel = gallery;

	}

	public void actionPerformed(ActionEvent ae) {

		String action = ae.getActionCommand();

		// Component[] components = frame.getContentPane().getComponents();
		// Component[] homeScreenComponenets = homeScreen.getComponents();

		System.out.println(action);

		frame.remove(activePanel);

		switch (action) {

		case "Unlock":

			if (isOff == true) {

				generatelockScreen();
				isOff = false;

			} else if (isOff == false) {

				generateBlackPannel();
				isOff = true;
				isLocked = true;

			}

			System.out.println(isOff);

			break;

		case "openContacts":

			generatecontacts();

			break;

		case "openGallery":

			generategallery();

			break;

		case "goToHome":

			if (isLocked == true && isOff == true) {

				generatelockScreen();

			} else if (isLocked == true) {

				generatelockScreen();

			} else if (isOff == false) {

				generateHomeScreen();

			}

			isOff = false;

			break;

		default:

			System.out.println("no action");
			break;

		}
		frame.validate();
		frame.repaint();

		/*
		 * if (action.equals("back") ) {
		 * 
		 * homeScreen.setVisible(true); contacts.setVisible(false);
		 * gallery.setVisible(false); Backpanel.setVisible(false);
		 * 
		 * }
		 */

	}
	
	

	public Color getFrameContentPaneBackground() {
		return frame.getContentPane().getBackground();
	}

	public void setFrameContentPaneBackground(Color background) {
		frame.getContentPane().setBackground(background);
	}
	
    public static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }
    
}
