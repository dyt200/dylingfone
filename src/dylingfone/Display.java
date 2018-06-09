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
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



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
	private JPanel EditContact;
	private JPanel contactDetails;
	private JPanel AddContact;
	private JLabel hourLabel;
	private String timeStamp;
	private boolean isOff = true;
	private boolean isLocked = true;
	private String detailHead ; 
	private int contactId;

	private JPanel activePanel;
	

	
	
	private ImageObserver observer;
	
	
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

		// transpatrentx
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

	 

	private void generatecontacts() {
		
		Contacts contactsObj = new Contacts();	
		Contact[] array = contactsObj.getContactListAlpha();
		String lastLetter = "0";
		String currentLetter;
		int countLetters = 0;

		contacts = new JPanel();
		contacts.setBounds(23, 88, 315, 553);
		contacts.setLayout(getLayout());
		frame.getContentPane().add(contacts);
		contacts.setBackground(Color.RED);
		
		JPanel addAndSearch = new JPanel();
		contacts.setBounds(23, 88, 315, 553);
		contacts.setLayout(getLayout());
		contacts.add(addAndSearch);
		contacts.setBackground(Color.RED);
		
		/*JButton BtnAddContact = new JButton("+"); 
		BtnAddContact.setBounds(20, 150, 315, 100);
		frame.add(BtnAddContact); 
		BtnAddContact.addActionListener(this);*/
		
		JPanel contactList = new JPanel();
		contactList.setBounds(23, 88, 315, 553);
		
		Image contactIcon = new ImageIcon(this.getClass().getResource("/addContact.png")).getImage();
		Image scaledContactIcon = contactIcon.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);

		JButton AddButton = new JButton();
		AddButton.setBackground(Color.BLACK);
		AddButton.setIcon(new ImageIcon(scaledContactIcon));
		AddButton.setBorderPainted(false);
		AddButton.setFocusPainted(false);
		AddButton.setContentAreaFilled(false);
		AddButton.setRolloverEnabled(false);
		AddButton.addActionListener(this);
		AddButton.setActionCommand("AddContact");
		
		contactList.add(AddButton);

		
		
		for (Contact contact : array) {
			
			currentLetter = String.valueOf(contact.getLastName().charAt(0));
			
			//if first letter of lastName != last letter then adds letter header
			if(currentLetter.equals(lastLetter) == false) {
				lastLetter = currentLetter;
				countLetters++;
				
				JPanel letterHead = new JPanel();
				letterHead.setPreferredSize(new Dimension(280, 20));
				letterHead.setLayout(new FlowLayout(FlowLayout.LEFT));
				
				JLabel jlabel = new JLabel(currentLetter);
				letterHead.add(jlabel);
				contactList.add(letterHead);
			}
			
			//creation of each contact panel + label
			JPanel panTest = new JPanel();
			panTest.setPreferredSize(new Dimension(280, 30));
			panTest.setBackground(Color.LIGHT_GRAY);
			panTest.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			JLabel jlabel = new JLabel(contact.getLastName()+", "+contact.getFirstName());
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
			
			contactList.setPreferredSize(new Dimension(280, (20*countLetters) + (countLetters*6) + (30*array.length)+ (array.length*5) + 5));
		
			JScrollPane scrollPane = new JScrollPane(contactList);
			//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setBounds(23, 150, 315, 553);
			
			contacts.add(scrollPane);
			
			activePanel = contacts;

	}
	
	
	private void generateContactDetails(int id) {
		
		Contacts contactsObj = new Contacts();	
		Contact[] array = contactsObj.getContactList();
		Gallery galleryLol = new Gallery();

		String imagePath = "";
	
		for (Contact contact : array) {
			
			if (contact.getId() == id) {
				
				detailHead = contact.getFirstName();
				contactId = contact.getId();
				imagePath = galleryLol.getPathFromId(contact.getPic());
				System.out.println("path = "+imagePath);
				
				generateBackPannel(detailHead, id, 1);
				
				contactDetails = new JPanel();
				contactDetails.setBounds(23, 88, 315, 553);
				contactDetails.setLayout(getLayout());
				contactDetails.setBackground(new Color(250,250, 255));
				
				frame.getContentPane().add(contactDetails);
				
				GridBagLayout gbl_blackPanel = new GridBagLayout();
				gbl_blackPanel.columnWidths = new int[]{35, 250, 35};
				gbl_blackPanel.rowHeights = new int[]{261, 22, 22, 22, 22, 22, 22,22};
				gbl_blackPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				gbl_blackPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				contactDetails.setLayout(gbl_blackPanel);
				
				JLabel lblContactPic = new JLabel();
				
				Image contactImage = new ImageIcon(this.getClass().getResource(imagePath)).getImage();
				Image scaledContactImage = contactImage.getScaledInstance(225,aspectRatioCalculator(contactImage.getHeight(lblContactPic),contactImage.getWidth(lblContactPic),225), java.awt.Image.SCALE_SMOOTH);
			
				lblContactPic.setIcon(new ImageIcon(scaledContactImage));
				
				lblContactPic.setOpaque(true);
				GridBagConstraints gbc_lblContactPic = new GridBagConstraints();
				gbc_lblContactPic.insets = new Insets(44, 0, 5, 0);
				gbc_lblContactPic.gridx = 1;
				gbc_lblContactPic.gridy = 0;
				contactDetails.add(lblContactPic, gbc_lblContactPic);
				
				JLabel lblFirstName = new JLabel(contact.getFirstName());
				GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
				gbc_lblFirstName.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblFirstName.insets = new Insets(22, 0, 5, 0);
				gbc_lblFirstName.gridx = 1;
				gbc_lblFirstName.gridy = 1;
				contactDetails.add(lblFirstName, gbc_lblFirstName);
				
				JLabel lblLastName = new JLabel(contact.getLastName());
				GridBagConstraints gbc_lblLastName = new GridBagConstraints();
				gbc_lblLastName.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblLastName.insets = new Insets(22, 0, 5, 0);
				gbc_lblLastName.gridx = 1;
				gbc_lblLastName.gridy = 2;
				contactDetails.add(lblLastName, gbc_lblLastName);
				
				JLabel lblMobileTel = new JLabel(contact.getTelMobile());
				GridBagConstraints gbc_lblMobileTel = new GridBagConstraints();
				gbc_lblMobileTel.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblMobileTel.insets = new Insets(22, 0, 5, 0);
				gbc_lblMobileTel.gridx = 1;
				gbc_lblMobileTel.gridy = 3;
				contactDetails.add(lblMobileTel, gbc_lblMobileTel);
				
				JLabel lblHomeTel = new JLabel(contact.getTelHome());
				GridBagConstraints gbc_lblHomeTel = new GridBagConstraints();
				gbc_lblHomeTel.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblHomeTel.insets = new Insets(22, 0, 5, 0);
				gbc_lblHomeTel.gridx = 1;
				gbc_lblHomeTel.gridy = 4;
				contactDetails.add(lblHomeTel, gbc_lblHomeTel);
				
				JLabel lblEmail = new JLabel(contact.getEmail());
				GridBagConstraints gbc_lblEmail = new GridBagConstraints();
				gbc_lblEmail.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblEmail.insets = new Insets(22, 0, 5, 0);
				gbc_lblEmail.gridx = 1;
				gbc_lblEmail.gridy = 5;
				contactDetails.add(lblEmail, gbc_lblEmail);
				
				JLabel lblBirthdate = new JLabel("birthdate");
				GridBagConstraints gbc_lblBirthdate = new GridBagConstraints();
				gbc_lblBirthdate.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblBirthdate.insets = new Insets(22, 0, 5, 0);
				gbc_lblBirthdate.gridx = 1;
				gbc_lblBirthdate.gridy = 6;
				contactDetails.add(lblBirthdate, gbc_lblBirthdate);
				
				activePanel = contactDetails;
				
			}
		}		
	}
	
	private void generateEditContact(int id) {
		
		Contacts contactsObj = new Contacts();	
		Contact[] array = contactsObj.getContactList();
		
		Gallery galleryLol = new Gallery();
		Pictures[] pictures = galleryLol.getImages();
		String imagePath = "";
		int imageId;
		
		for (Contact contact : array) {
			
			if (contact.getId() == id) {
				detailHead = contact.getFirstName();
				contactId = contact.getId();
				
				imagePath = galleryLol.getPathFromId(contact.getPic());
				System.out.println("IMAGE PATH = "+imagePath);
			}
		}
		
		frame.remove(Backpanel);
		
		EditContact = new JPanel();
		EditContact.setBounds(23, 88, 315, 553);
		EditContact.setLayout(getLayout());
		frame.getContentPane().add(EditContact);
		EditContact.setBackground(new Color(250,250, 255));
		
		for (Contact contact : array) {
			
			if (contact.getId() == id) {
				
				detailHead = contact.getFirstName();
				
				GridBagLayout gbl_blackPanel = new GridBagLayout();
				gbl_blackPanel.columnWidths = new int[]{35, 250, 35};
				gbl_blackPanel.rowHeights = new int[]{261, 22, 22, 22, 22, 22, 22,22};
				gbl_blackPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
				gbl_blackPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				EditContact.setLayout(gbl_blackPanel);
				
				JLabel lblContactPic = new JLabel();
				Image contactImage = new ImageIcon(this.getClass().getResource(imagePath)).getImage();
				Image scaledContactImage = contactImage.getScaledInstance(225,aspectRatioCalculator(contactImage.getHeight(lblContactPic),contactImage.getWidth(lblContactPic),225), java.awt.Image.SCALE_SMOOTH);
				lblContactPic.setIcon(new ImageIcon(scaledContactImage));
				lblContactPic.setOpaque(true);
				
				GridBagConstraints gbc_lblContactPic = new GridBagConstraints();
				gbc_lblContactPic.insets = new Insets(44, 0, 5, 0);
				gbc_lblContactPic.gridx = 1;
				gbc_lblContactPic.gridy = 0;
				EditContact.add(lblContactPic, gbc_lblContactPic);
				
				JTextField lblFirstName = new JTextField(contact.getFirstName());
				lblFirstName.setSize(new Dimension(250, 10));
				GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
				gbc_lblFirstName.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblFirstName.fill=GridBagConstraints.HORIZONTAL;
				gbc_lblFirstName.insets = new Insets(22, 0, 5, 0);
				gbc_lblFirstName.gridx = 1;
				gbc_lblFirstName.gridy = 1;
				EditContact.add(lblFirstName, gbc_lblFirstName);
				
				JTextField lblLastName = new JTextField(contact.getLastName());
				GridBagConstraints gbc_lblLastName = new GridBagConstraints();
				gbc_lblLastName.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblLastName.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblLastName.insets = new Insets(22, 0, 5, 0);
				gbc_lblLastName.gridx = 1;
				gbc_lblLastName.gridy = 2;
				EditContact.add(lblLastName, gbc_lblLastName);
				
				JTextField lblMobileTel = new JTextField(contact.getTelMobile());
				GridBagConstraints gbc_lblMobileTel = new GridBagConstraints();
				gbc_lblMobileTel.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblMobileTel.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblMobileTel.insets = new Insets(22, 0, 5, 0);
				gbc_lblMobileTel.gridx = 1;
				gbc_lblMobileTel.gridy = 3;
				EditContact.add(lblMobileTel, gbc_lblMobileTel);
				
				JTextField lblHomeTel = new JTextField(contact.getTelHome());
				GridBagConstraints gbc_lblHomeTel = new GridBagConstraints();
				gbc_lblHomeTel.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblHomeTel.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblHomeTel.insets = new Insets(22, 0, 5, 0);
				gbc_lblHomeTel.gridx = 1;
				gbc_lblHomeTel.gridy = 4;
				EditContact.add(lblHomeTel, gbc_lblHomeTel);
				
				JTextField lblEmail = new JTextField(contact.getEmail());
				GridBagConstraints gbc_lblEmail = new GridBagConstraints();
				gbc_lblEmail.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblEmail.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblEmail.insets = new Insets(22, 0, 5, 0);
				gbc_lblEmail.gridx = 1;
				gbc_lblEmail.gridy = 5;
				EditContact.add(lblEmail, gbc_lblEmail);
				
				JTextField lblBirthdate = new JTextField("birthdate");
				GridBagConstraints gbc_lblBirthdate = new GridBagConstraints();
				gbc_lblBirthdate.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblBirthdate.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblBirthdate.insets = new Insets(22, 0, 5, 0);
				gbc_lblBirthdate.gridx = 1;
				gbc_lblBirthdate.gridy = 6;
				EditContact.add(lblBirthdate, gbc_lblBirthdate);
				
				
				JButton btnSave = new JButton("save"); 
				GridBagConstraints gbc_btnSave = new GridBagConstraints();
				gbc_btnSave.anchor = GridBagConstraints.NORTHWEST;
			//	gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
				gbc_btnSave.insets = new Insets(10, 0, 5, 0);
				gbc_btnSave.gridx = 1;
				gbc_btnSave.gridy = 0;
				EditContact.add(btnSave, gbc_btnSave);
				
				  
				  btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
						
		                public void mouseClicked(java.awt.event.MouseEvent evt) {
		                	
		                    System.out.println("mouseClicked edit");
		                    System.out.println(id);
		                    
		                	frame.remove(activePanel);
		                	
		                	contactsObj.editContact(id, "firstName", lblFirstName.getText() );
		                	contactsObj.editContact(id, "lastName", lblLastName.getText() );
		                	contactsObj.editContact(id, "telMobile", lblMobileTel.getText() );
		                	contactsObj.editContact(id, "telHome", lblHomeTel.getText() );
		                	contactsObj.editContact(id, "email", lblEmail.getText() );
		                	//contactsObj.editContact(id, "birthDate", lblBirthdate.getText() );
		                	
		                    generateContactDetails(id);
		                	frame.validate();
		            		frame.repaint();
		            
		                    
		                }});
				  
					JButton btnDelete = new JButton("delete"); 
					GridBagConstraints gbc_btnDelete = new GridBagConstraints();
					gbc_btnDelete.anchor = GridBagConstraints.NORTHEAST;
					//gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
					gbc_btnDelete.insets = new Insets(10, 0, 5, 0);
					gbc_btnDelete.gridx = 1;
					gbc_btnDelete.gridy = 0;
					EditContact.add(btnDelete, gbc_btnDelete);
					
					  
					btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
							
	                public void mouseClicked(java.awt.event.MouseEvent evt) {
	                	
	                    System.out.println("mouseClicked edit");
	                    System.out.println(id);
	                    
	                	frame.remove(activePanel);
	                	
	                	contactsObj.deleteContact(id);
	                	System.out.println("Delete contact sucess");
	                
	                    generatecontacts();
	                	frame.validate();
	            		frame.repaint();
	            
	                    
	                }});
			
			}
			
		}
		
	
		
		activePanel = EditContact;
		
		
	}
	
	public void generateAddContact() {
		
		Contacts contactsObj = new Contacts();	
		Contact[] array = contactsObj.getContactList();
		
		AddContact = new JPanel();
		AddContact.setBounds(23, 88, 315, 553);
		AddContact.setLayout(getLayout());
		frame.getContentPane().add(AddContact);
		AddContact.setBackground(new Color(250,250, 255));
		
		GridBagLayout gbl_blackPanel = new GridBagLayout();
		gbl_blackPanel.columnWidths = new int[]{35, 250 , 35};
		gbl_blackPanel.rowHeights = new int[]{261, 22, 22, 22, 22, 22, 22,22};
		gbl_blackPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_blackPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		AddContact.setLayout(gbl_blackPanel);
		
		JLabel lblContactPic = new JLabel();
		
		Image contactImage = new ImageIcon(this.getClass().getResource("/placeholder.jpg")).getImage();
		Image scaledContactImage = contactImage.getScaledInstance(225,aspectRatioCalculator(contactImage.getHeight(lblContactPic),contactImage.getWidth(lblContactPic),225), java.awt.Image.SCALE_SMOOTH);
	
		lblContactPic.setIcon(new ImageIcon(scaledContactImage));
		lblContactPic.setOpaque(true);
		

		Image ChosePicIcon = new ImageIcon(this.getClass().getResource("/pencilIcon.png")).getImage();
		Image scaledChosePicIcon = ChosePicIcon.getScaledInstance(32,32, java.awt.Image.SCALE_SMOOTH);

		JButton btnChosePic = new JButton(); 
		btnChosePic.setIcon(new ImageIcon(scaledChosePicIcon));
		btnChosePic.setBorderPainted(false);
		btnChosePic.setContentAreaFilled(false);
		btnChosePic.setRolloverEnabled(false);
		GridBagConstraints gbc_btnChosePic = new GridBagConstraints();
		gbc_btnChosePic.anchor = GridBagConstraints.NORTHEAST;
	//	gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnChosePic.insets = new Insets(40, 0, 5, 0);
		gbc_btnChosePic.gridx = 1;
		gbc_btnChosePic.gridy = 0;
		
		//btnChosePic.addActionListener(this);
		//btnChosePic.setActionCommand("openChoiceGallery");
		
		AddContact.add(btnChosePic, gbc_btnChosePic);
		
		GridBagConstraints gbc_lblContactPic = new GridBagConstraints();
		gbc_lblContactPic.insets = new Insets(44, 0, 5, 0);
		gbc_lblContactPic.gridx = 1;
		gbc_lblContactPic.gridy = 0;
		AddContact.add(lblContactPic, gbc_lblContactPic);
		
		
		JLabel FirstName = new JLabel("First name :");
		GridBagConstraints gbc_FirstName = new GridBagConstraints();
		//gbc_lblFirstName.anchor = GridBagConstraints.NORTHWEST;
		gbc_FirstName.fill=GridBagConstraints.HORIZONTAL;
		gbc_FirstName.insets = new Insets(22, 0, 5, 0);
		gbc_FirstName.gridx = 1;
		gbc_FirstName.gridy = 1;
		AddContact.add(FirstName, gbc_FirstName);
		
		
		JTextField lblFirstName = new JTextField();
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		//gbc_lblFirstName.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblFirstName.fill=GridBagConstraints.HORIZONTAL;
		gbc_lblFirstName.insets = new Insets(22, 94, 5, 0);
		gbc_lblFirstName.gridx = 1;
		gbc_lblFirstName.gridy = 1;
		AddContact.add(lblFirstName, gbc_lblFirstName);
		

		JLabel LastName = new JLabel("Last name :");
		GridBagConstraints gbc_LastName = new GridBagConstraints();
		gbc_LastName.anchor = GridBagConstraints.NORTHWEST;
		gbc_LastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_LastName.insets = new Insets(11, 0, 5, 0);
		gbc_LastName.gridx = 1;
		gbc_LastName.gridy = 2;
		AddContact.add(LastName, gbc_LastName);
		
		
		JTextField lblLastName = new JTextField();
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLastName.insets = new Insets(11, 94, 5, 0);
		gbc_lblLastName.gridx = 1;
		gbc_lblLastName.gridy = 2;
		AddContact.add(lblLastName, gbc_lblLastName);
		
		JLabel MobileTel = new JLabel("Mobile : ");
		GridBagConstraints gbc_MobileTel = new GridBagConstraints();
		gbc_MobileTel.anchor = GridBagConstraints.NORTHWEST;
		gbc_MobileTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_MobileTel.insets = new Insets(11, 0, 5, 0);
		gbc_MobileTel.gridx = 1;
		gbc_MobileTel.gridy = 3;
		AddContact.add(MobileTel, gbc_MobileTel);
		
		JTextField lblMobileTel = new JTextField();
		GridBagConstraints gbc_lblMobileTel = new GridBagConstraints();
		gbc_lblMobileTel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblMobileTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMobileTel.insets = new Insets(11, 94, 5, 0);
		gbc_lblMobileTel.gridx = 1;
		gbc_lblMobileTel.gridy = 3;
		AddContact.add(lblMobileTel, gbc_lblMobileTel);
		
		JLabel HomeTel = new JLabel("Home :");
		GridBagConstraints gbc_HomeTel = new GridBagConstraints();
		gbc_HomeTel.anchor = GridBagConstraints.NORTHWEST;
		gbc_HomeTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_HomeTel.insets = new Insets(11, 0, 5, 0);
		gbc_HomeTel.gridx = 1;
		gbc_HomeTel.gridy = 4;
		AddContact.add(HomeTel, gbc_HomeTel);
		
		JTextField lblHomeTel = new JTextField();
		GridBagConstraints gbc_lblHomeTel = new GridBagConstraints();
		gbc_lblHomeTel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblHomeTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHomeTel.insets = new Insets(11, 94, 5, 0);
		gbc_lblHomeTel.gridx = 1;
		gbc_lblHomeTel.gridy = 4;
		AddContact.add(lblHomeTel, gbc_lblHomeTel);
		
		JLabel Email = new JLabel("E-Mail :");
		GridBagConstraints gbc_Email = new GridBagConstraints();
		gbc_Email.anchor = GridBagConstraints.NORTHWEST;
		gbc_Email.fill = GridBagConstraints.HORIZONTAL;
		gbc_Email.insets = new Insets(11, 0, 5, 0);
		gbc_Email.gridx = 1;
		gbc_Email.gridy = 5;
		AddContact.add(Email, gbc_Email);
		
		JTextField lblEmail = new JTextField();
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmail.insets = new Insets(11,94 , 5, 0);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 5;
		AddContact.add(lblEmail, gbc_lblEmail);
		
		JLabel Birthdate = new JLabel("Birthdate :");
		GridBagConstraints gbc_Birthdate = new GridBagConstraints();
		gbc_Birthdate.anchor = GridBagConstraints.NORTHWEST;
		gbc_Birthdate.fill = GridBagConstraints.HORIZONTAL;
		gbc_Birthdate.insets = new Insets(11, 0, 5, 0);
		gbc_Birthdate.gridx = 1;
		gbc_Birthdate.gridy = 6;
		AddContact.add(Birthdate, gbc_Birthdate);
		
		JTextField lblBirthdate = new JTextField();
		GridBagConstraints gbc_lblBirthdate = new GridBagConstraints();
		gbc_lblBirthdate.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblBirthdate.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBirthdate.insets = new Insets(11, 94, 5, 0);
		gbc_lblBirthdate.gridx = 1;
		gbc_lblBirthdate.gridy = 6;
		AddContact.add(lblBirthdate, gbc_lblBirthdate);
		
		
		JButton btnSave = new JButton("save"); 
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.CENTER;
	//	gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.insets = new Insets(10, 0, 5, 0);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 7;
		AddContact.add(btnSave, gbc_btnSave);
		
		   btnChosePic.addMouseListener(new java.awt.event.MouseAdapter() {
				
              public void mouseClicked(java.awt.event.MouseEvent evt) {
              	
                  System.out.println("mouseClicked edit");
                  System.out.println();
                  
              	frame.remove(activePanel);
           
                generategallery();
                
              	frame.validate();
          		frame.repaint();
          
                  
              }});
		
		

		
		  
		  btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
				
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                	
                    System.out.println("mouseClicked edit");
                    System.out.println();
                    
                	frame.remove(activePanel);
                	// SAVE LOGIC HERE
                	/**
                	 * 
                	 * 
                	 * Ben,  you realy need to remove that fucking date type, let us simplify our lives
                	 * See Ya :D
                	 * 
                	 */
                	
                	contactsObj.addContact(lblFirstName.getText(), lblLastName.getText(), lblBirthdate.getText(), lblEmail.getText(), lblMobileTel.getText(), lblHomeTel.getText());
           
                    generatecontacts();
                	frame.validate();
            		frame.repaint();
            
                    
                }});
		  
		
		activePanel = AddContact;
	};
	
	  private void generateBackPannel(String head,int id, int type) {
		  
		  /*	TYPE 1 = contacts
		   * 	TYPE 2 = images
		   */
		  
		  Backpanel = new JPanel(); Backpanel.setBounds(23, 88, 320, 44);
		  frame.getContentPane().add(Backpanel); Backpanel.setBackground(Color.GRAY);
		  Backpanel.setLayout(null);
		  
		  
		  JButton btnBack = new JButton("b"); 
		  btnBack.setBounds(12, 9, 50, 26);
		  Backpanel.add(btnBack); 
		  btnBack.addActionListener(this);


		  btnBack.setActionCommand("back"+type);
		  
		  JButton btnEditContact = new JButton("e"); 
		  btnEditContact.setBounds(260, 9, 50, 26);
		  Backpanel.add(btnEditContact); 
		  
		  btnEditContact.addMouseListener(new java.awt.event.MouseAdapter() {
				
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                	
                    System.out.println("mouseClicked edit");
                    System.out.println(id);
                    
                	frame.remove(activePanel);
                	
                	if(type == 1) {
                		generateEditContact(id);
                	} else {
                		generateEditImage(id);
                	}
                	frame.validate();
            		frame.repaint();
            
                    
                }});
//		  btnEditContact.addActionListener(this);
//		  btnEditContact.setActionCommand("EditContact");
		
		  
		  
		  JLabel label_1 = new JLabel(head);
		  label_1.setBounds(130, 14, 56, 16);
		  Backpanel.add(label_1);
		  
		 // activePanel = Backpanel;
		  
		  }

	private void generategallery() {
		
		Gallery galleryObj = new Gallery();
		Pictures[] pictures = galleryObj.getImages();
		
		gallery = new JPanel();
		gallery.setBounds(23, 88, 315, 553);
		gallery.setLayout(getLayout());
		frame.getContentPane().add(gallery);
		
		JPanel contactList = new JPanel();
		contactList.setBounds(23, 88, 315, 553);
		
		
		for (int i = 0 ; i < pictures.length ; i++) {
			final int id = i;
			try {
				
				BufferedImage currImage = ImageIO.read(new File(pictures[i].getPath()));
				Image scaledImage = currImage.getScaledInstance(65,65,Image.SCALE_SMOOTH);
				
				JLabel picLabel = new JLabel(new ImageIcon(scaledImage), SwingConstants.LEFT);
				picLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
				
				// mouse listener for clicking on an image to go into imageDetails.
				// 1988 THIS SEEMS TO WORK WELL, go to generateImageDetails (below)
				picLabel.addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseClicked(java.awt.event.MouseEvent evt) {
		            	frame.remove(activePanel);
		                generateImageDetails(id);
		            	frame.validate();
		        		frame.repaint();
		            }
	            });
				
				contactList.add(picLabel);
				
			} catch (IOException e) {
				System.out.println("ERROR IN generateGallery() : "+e);
			}
			
			
		}
		
		contactList.setBackground(Color.BLUE);
		contactList.setPreferredSize(new Dimension(280, ((65*200) + (5*200))/4 + 5 ));
	
		JScrollPane scrollPane = new JScrollPane(contactList);
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		gallery.add(scrollPane);
		
	
		activePanel = gallery;

	}
	
	public void generateImageDetails(int id) {
		Gallery gallery = new Gallery();
		Pictures[] imageList = gallery.getImages();
		
		String title = imageList[id].getTitle();
		String imagePath = gallery.getPathFromId(id);
		
		generateBackPannel(title, id, 2);
		
		JPanel imageDetails = new JPanel();
		imageDetails.setBounds(23, 88, 315, 553);
		imageDetails.setLayout(getLayout());
		imageDetails.setBackground(Color.GRAY);
		
		//1988 THIS IS WHERE IM STUCK
		//I can type the path of the image a la main in place of imagePath and it doesnt find it, ive tried every possible way
		JLabel lblContactPic = new JLabel();
		System.out.println("path = "+imagePath);
		Image contactImage = new ImageIcon(this.getClass().getResource(imagePath)).getImage();
		Image scaledContactImage = contactImage.getScaledInstance(225,aspectRatioCalculator(contactImage.getHeight(lblContactPic),contactImage.getWidth(lblContactPic),225), java.awt.Image.SCALE_SMOOTH);
	
		lblContactPic.setIcon(new ImageIcon(scaledContactImage));
		lblContactPic.setOpaque(true);
		
		GridBagConstraints gbc_lblContactPic = new GridBagConstraints();
		gbc_lblContactPic.insets = new Insets(44, 0, 5, 0);
		gbc_lblContactPic.gridx = 1;
		gbc_lblContactPic.gridy = 0;
		imageDetails.add(lblContactPic, gbc_lblContactPic);
		
		activePanel = imageDetails;
		
	}
	
	public void generateEditImage(int id) {
		System.out.println("IMAGINE THAT YOU ARE NOW EDITING AN IMAGE... WOW IT WORKS SO WELL!");
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
			
		//back for contacts
		case "back1": 
			
			if (activePanel == contactDetails) {
				
				frame.remove(Backpanel);
				generatecontacts();
				
			}
			break;
			
		//back for images
		case "back2":
			frame.remove(Backpanel);
			generategallery();
			break;
			
		case "AddContact":
			
			generateAddContact();
			
		case "openChoiceGallery":
			
		
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
	
	public int aspectRatioCalculator(double originHeight, double originWidth, double newWidth) {
		
		double res = (originHeight/originWidth) * newWidth;
		
		int resInt = (int) Math.round(res);

		
		return resInt;
		
		
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
