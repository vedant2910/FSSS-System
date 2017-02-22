package fsss_package;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.io.File;

import javax.swing.SwingConstants;

public class InstructionsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InstructionsDialog dialog = new InstructionsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InstructionsDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Instructions");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 240, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextPane txtpnFirstConfigure = new JTextPane();
			txtpnFirstConfigure.setEditable(false);
			txtpnFirstConfigure.setFont(new Font("Times New Roman", Font.BOLD, 14));
			txtpnFirstConfigure.setText("1. First configure the details.\r\n\r\n2. Now you can run Normal Simulation or Graphical Simulation.\r\n\r\n3. You can get corresponding Excel files or you can print the graph \r\nobtained in Graphical Simulation.\r\n\t\r\n\tHave A Nice Day!!\r\n\r\nFor more Instructions, refer to the User Manual.");
			txtpnFirstConfigure.setBackground(new Color(240,240,240));
			txtpnFirstConfigure.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			txtpnFirstConfigure.setBackground(new Color(255, 255, 255));
			txtpnFirstConfigure.setCaretColor(new Color(0, 0, 0));
			txtpnFirstConfigure.setBounds(10, 11, 424, 210);
			contentPanel.add(txtpnFirstConfigure);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					JButton btnUserManual = new JButton("User Manual");
					btnUserManual.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							try {
								 
								if ((new File("F:\\thenewboston\\FSSS\\src\\Manual\\FSSS_UserManual.pdf")).exists()) {
						 
									Process p = Runtime
									   .getRuntime()
									   .exec("rundll32 url.dll,FileProtocolHandler F:\\thenewboston\\FSSS\\src\\Manual\\FSSS_UserManual.pdf");
									p.waitFor();
						 
								} else {						 
									System.out.println("File does not exists");
						 
								}
						 
								//System.out.println("Done");
						 
						  	  } catch (Exception ex) {
								ex.printStackTrace();
							  }
						}
					});
					btnUserManual.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
					btnUserManual.setHorizontalAlignment(SwingConstants.LEFT);
					buttonPane.add(btnUserManual);
				}
				okButton.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setLocationRelativeTo(null);
	}
}
