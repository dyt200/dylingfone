package dylingfone;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class scroll extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					scroll frame = new scroll();
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
	public scroll() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JPanel pan = new JPanel();
		
		for (int i = 0 ; i < 800 ; i++) {
			
			pan.add(new JButton("aodfnv"));
			
			
		}
		
		
	
		//supposons que ta mis 20 bouton
		 
		pan.setPreferredSize(new Dimension(0 , 20*200));
		 
		JScrollPane scrollPane = new JScrollPane(pan);
		
		JPanel panel = new JPanel();
		pan.add(panel);
		contentPane.add(scrollPane);
		
		
	}

}
