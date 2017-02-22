package fsss_package;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class MachineItemPanel extends JPanel {
	
	private ConfigureFrame cf;
	private JTextField txtType;
	private JTextField txtMttf;
	private JSpinner spinner;
	private JLabel lblMachineType;
	private JLabel lblMttfdays;
	private JLabel lblQuantity;
	private JButton btnAdd;

	/**
	 * Create the panel.
	 */
	public MachineItemPanel(ConfigureFrame conf) {
		cf = conf;
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		txtType = new JTextField();
		txtType.setBounds(177, 22, 114, 20);
		add(txtType);
		txtType.setColumns(10);
		
		txtMttf = new JTextField();
		txtMttf.setBounds(177, 62, 50, 20);
		add(txtMttf);
		txtMttf.setColumns(10);
		
		spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
		spinner.setBounds(177, 101, 50, 20);
		add(spinner);
		
		lblMachineType = new JLabel("Machine Type");
		lblMachineType.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMachineType.setBounds(39, 22, 107, 16);
		add(lblMachineType);
		
		lblMttfdays = new JLabel("MTTF(Days)");
		lblMttfdays.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMttfdays.setBounds(39, 62, 85, 16);
		add(lblMttfdays);
		
		lblQuantity = new JLabel("Quantity(1-10000)");
		lblQuantity.setFont(new Font("Dialog", Font.BOLD, 14));
		lblQuantity.setBounds(39, 101, 126, 16);
		add(lblQuantity);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Check data
				if(txtType.getText().isEmpty() || txtMttf.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Empty Fields!!", "Error!!", JOptionPane.ERROR_MESSAGE);
				
				else
				{				
					try{
						int number = Integer.parseInt(txtMttf.getText());
						cf.machineAdded();
					}
					catch(NumberFormatException ex)
					{
						JOptionPane.showMessageDialog(null, "Invalid MTTF value!!", "Error!!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}			
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnAdd.setBounds(101, 148, 98, 26);
		add(btnAdd);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(39, 52, 239, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(39, 92, 239, 2);
		add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(39, 132, 239, 2);
		add(separator_2);
	}	

	public void reset() {
		// TODO Auto-generated method stub
		txtType.setText("");
		txtMttf.setText("");
		spinner.setValue(new Integer(1));
	}
	
	public String getType()
	{
		return txtType.getText();
	}
	
	public int getMTTF()
	{
		return Integer.parseInt(txtMttf.getText());
	}
	
	public int getQuantity()
	{
		return (Integer)spinner.getValue();
	}
}
