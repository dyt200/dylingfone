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



public class frame {

	private JFrame frame;
	private JTextField txtHomecreen;

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
		frame.getContentPane().setBackground(new Color (255, 255, 255));
		frame.setType(Type.UTILITY);
		frame.setResizable(false); // set resizable to true if you want to remove the box arround the frame
		frame.setBounds(100, 100, 368, 754);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(false);
		//frame.setBackground(new Color(0, 0, 0,0));  // Uncomment to set the background transpatrent
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
	 */
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 88, 313, 553);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnEnter = new JButton("Enter");
		
		btnEnter.setBounds(105, 515, 97, 25);
		panel.add(btnEnter);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(23, 88, 313, 553);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtHomecreen = new JTextField();
		txtHomecreen.setText("HOMECREEN");
		txtHomecreen.setBounds(56, 31, 178, 40);
		panel_1.add(txtHomecreen);
		txtHomecreen.setColumns(10);
		
		
		
	}
	
	/*
	public void actionPerformed(ActionEvent ae) {

		String actionCommand = ae.getActionCommand();
		Component[] components = getContentPane().getComponents();

		if (actionCommand.compareTo("Initial") == 0) {
			for (int i = 0; i < components.length; i++) {
				Component nextComponent = components[i];
				if (nextComponent.equals(secondPanel))
					getContentPane().remove(i);
				if (nextComponent.equals(thirdPanel))
					getContentPane().remove(i);
			}
			getContentPane().add(initialPanel);
		} else if (actionCommand.compareTo("Second") == 0) {
			for (int i = 0; i < components.length; i++) {
				Component nextComponent = components[i];
				if (nextComponent.equals(initialPanel))
					getContentPane().remove(i);
				if (nextComponent.equals(thirdPanel))
					getContentPane().remove(i);
			}
			getContentPane().add(secondPanel);
		} else if (actionCommand.compareTo("Third") == 0) {
			for (int i = 0; i < components.length; i++) {
				Component nextComponent = components[i];
				if (nextComponent.equals(initialPanel))
					getContentPane().remove(i);
				if (nextComponent.equals(secondPanel))
					getContentPane().remove(i);
			}
			getContentPane().add(thirdPanel);
		}
		validate();
		pack();
	}*/
	
	public Color getFrameContentPaneBackground() {
		return frame.getContentPane().getBackground();
	}
	public void setFrameContentPaneBackground(Color background) {
		frame.getContentPane().setBackground(background);
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
	}
}
