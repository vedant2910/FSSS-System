package fsss_package;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainViewPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public Mainpage mp; 
	public MainViewPanel(Mainpage mainpage) {		
		mp = mainpage;
		
		ImageIcon kgp = new ImageIcon(getClass().getResource("/Images/machine1.png"));
		setLayout(null);
		JLabel imageLabel = new JLabel(kgp);
		imageLabel.setBounds(10, 35, 300, 329);
		add(imageLabel);
		
		JButton btnConfigure = new JButton("Configure");
		btnConfigure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				mp.configure();
			}
		});
		btnConfigure.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18));
		btnConfigure.setBounds(10, 376, 300, 45);
		add(btnConfigure);
		
		JButton btnNormalSimulation = new JButton("Normal Simulation");
		btnNormalSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				mp.normalSimulation();
			}
		});
		btnNormalSimulation.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 18));
		btnNormalSimulation.setBounds(10, 433, 300, 45);
		add(btnNormalSimulation);
		
		JButton btnGraphicalSimulation = new JButton("Graphical Simulation");
		btnGraphicalSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			mp.graphicalSimulation();
			}
		});
		btnGraphicalSimulation.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		btnGraphicalSimulation.setBounds(10, 490, 300, 45);
		add(btnGraphicalSimulation);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1001, 23);
		add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmConfigure = new JMenuItem("Configure");
		mntmConfigure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.configure();
			}
		});
		mnMenu.add(mntmConfigure);
		
		JMenuItem mntmNormalSimulation = new JMenuItem("Normal Simulation");
		mntmNormalSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.normalSimulation();
			}
		});
		mnMenu.add(mntmNormalSimulation);
		
		JMenuItem mntmGraphicalSimulation = new JMenuItem("Graphical Simulation");
		mntmGraphicalSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.graphicalSimulation();
			}
		});
		mnMenu.add(mntmGraphicalSimulation);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmInstructions = new JMenuItem("Instructions");
		mntmInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InstructionsDialog idg = new InstructionsDialog();
				idg.setVisible(true);
			}
		});
		mnHelp.add(mntmInstructions);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAboutUs = new JMenuItem("About Me");
		mntmAboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutUs au = new AboutUs();
				au.setVisible(true);
			}
		});
		mnAbout.add(mntmAboutUs);		

	}
}
