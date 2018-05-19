package dylingfone;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelPlay extends JFrame implements ActionListener {

	JPanel initialPanel;
	JPanel secondPanel;
	JPanel thirdPanel;

	public PanelPlay() {
		init();
	}

	public static void main(String[] args) {
		PanelPlay panelPlay = new PanelPlay();
	}

	public void init() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		JMenu selectionMenu = new JMenu("Selection");
		JMenuItem initialItem = new JMenuItem("Initial");
		JMenuItem secondItem = new JMenuItem("Second");
		JMenuItem thirdItem = new JMenuItem("Third");
		menuBar.add(selectionMenu);
		selectionMenu.add(initialItem);
		selectionMenu.add(secondItem);
		selectionMenu.add(thirdItem);
		initialItem.addActionListener(this);
		secondItem.addActionListener(this);
		thirdItem.addActionListener(this);

		initialPanel = new JPanel();
		initialPanel.add(new JLabel("You should see the initial panel"));
		secondPanel = new JPanel();
		secondPanel.add(new JLabel("You should see the second panel"));
		thirdPanel = new JPanel();
		thirdPanel.add(new JLabel("You should see the third panel"));

		JPanel menuPanel = new JPanel();
		menuPanel.add(menuBar);
		getContentPane().add(menuPanel, BorderLayout.NORTH);
		getContentPane().add(initialPanel, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);
		show();

	}

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
	}

}