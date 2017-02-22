package fsss_package;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class Openmanual extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Openmanual frame = new Openmanual();
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
	public Openmanual() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOpenUserManual = new JButton("Open User Manual");
		btnOpenUserManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					 
					if ((new File("F:\\thenewboston\\FSSS\\src\\Manual\\FSSS_UserManual.pdf")).exists()) {
			 
						Process p = Runtime
						   .getRuntime()
						   .exec("rundll32 url.dll,FileProtocolHandler F:\\thenewboston\\FSSS\\src\\Manual\\FSSS_UserManual.pdf");
						p.waitFor();
			 
					} else {
			 
						System.out.println("File is not exists");
			 
					}
			 
					System.out.println("Done");
			 
			  	  } catch (Exception ex) {
					ex.printStackTrace();
				  }
			}
		});
		btnOpenUserManual.setBounds(144, 133, 169, 23);
		contentPane.add(btnOpenUserManual);
	}
}
