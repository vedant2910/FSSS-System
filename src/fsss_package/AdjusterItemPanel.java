package fsss_package;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AdjusterItemPanel extends JPanel {
	
	public ConfigureFrame cf;
	private JTextField ID;
	private JSpinner spinner;
	public JComboBox ChoiceOfMachines;
	public JSpinner RepairTime;
	
	public ArrayList<String> Types;
	public ArrayList<Integer> repairDays; 
	public ArrayList<String> machineTypes = new ArrayList<String>();
	
	/**
	 * Create the panel.
	 */
	public AdjusterItemPanel(ConfigureFrame conf) {
		cf = conf;
		
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel lblAdjuster = new JLabel("Adjuster ID");
		lblAdjuster.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAdjuster.setBounds(28, 0, 85, 26);
		add(lblAdjuster);
		
		ID = new JTextField();
		ID.setBounds(189, 4, 132, 20);
		add(ID);
		ID.setColumns(10);
		
		JButton btnAdd = new JButton("Add Adjuster");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Check data
				if(ID.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter an Adjuster ID!!", "Error!!", JOptionPane.ERROR_MESSAGE);
				
				else
				{
					if(!(ChoiceOfMachines.getItemCount()==0))					
					{
						Types.add(ChoiceOfMachines.getSelectedItem().toString());				
						repairDays.add((Integer)RepairTime.getValue());						
					}
					
					cf.adjusterAdded();
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnAdd.setBounds(28, 221, 293, 26);
		add(btnAdd);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(28, 34, 293, 5);
		add(separator);
		
		JLabel lblQuantity = new JLabel("Quantity(1-1000)");
		lblQuantity.setFont(new Font("Dialog", Font.BOLD, 14));
		lblQuantity.setBounds(28, 40, 132, 16);
		add(lblQuantity);
		
		spinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
		spinner.setBounds(188, 39, 45, 20);
		add(spinner);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(28, 68, 293, 5);
		add(separator_3);
		
		JButton btnAddAMachine = new JButton("Add Another Machine Type");
		btnAddAMachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ChoiceOfMachines.getItemCount() == 0)
				JOptionPane.showMessageDialog(null, "No More Machine Type left!!", "Error!!", JOptionPane.ERROR_MESSAGE);
					
				else
				{
					Types.add(ChoiceOfMachines.getSelectedItem().toString());
					repairDays.add((Integer)RepairTime.getValue());			
				
					//Removing Selected item from the ComboBox
					removeSelectedItem();
				
					RepairTime.setValue(new Integer(1));
				}
								
			}
		});
		btnAddAMachine.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAddAMachine.setBounds(28, 161, 296, 26);
		add(btnAddAMachine);
		
		JLabel lblNewLabel = new JLabel("MachineType");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(28, 84, 109, 16);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Repair Time(Days)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(28, 130, 132, 16);
		add(lblNewLabel_1);
		
		//Creating array from arraylist
		//System.out.println("Size of machineTypes is "+ machineTypes.size()+"\n\n");
		ChoiceOfMachines = new JComboBox();
		ChoiceOfMachines.setBounds(189, 84, 132, 25);
		add(ChoiceOfMachines);
		
		RepairTime = new JSpinner(new SpinnerNumberModel(1, 1, 200, 1));
		RepairTime.setBounds(189, 130, 44, 20);
		add(RepairTime);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(28, 205, 293, 5);
		add(separator_1);
				
	}
		
	public void reset() {
		// TODO Auto-generated method stub
		
		//Resetting the arraylists
		Types = new ArrayList<String>();		
		repairDays = new ArrayList<Integer>();
		
		ID.setText("");		
		spinner.setValue(new Integer(1));
		
		//Changing ComboBox Contents
		String arr[] = new String[machineTypes.size()];
		arr = machineTypes.toArray(arr);
		ChoiceOfMachines.setModel(new JComboBox(arr).getModel());
		
		RepairTime.setValue(new Integer(1));
	}
	
	public void removeSelectedItem() {
		// TODO Auto-generated method stub
		
		ArrayList<String> temp = new ArrayList<String>();
		for(int i=0;i<ChoiceOfMachines.getItemCount();++i)
		{
			temp.add(ChoiceOfMachines.getItemAt(i).toString());
		}
		
		temp.remove(ChoiceOfMachines.getSelectedIndex());
		
		String arr[] = new String[temp.size()];
		arr = temp.toArray(arr);
		ChoiceOfMachines.setModel(new JComboBox(arr).getModel());
		
	}
	
	public String getID()
	{
		return ID.getText();
	}
	
	public ArrayList<String> getTypes()
	{
		return Types;
	}
	
	public ArrayList<Integer> getRepairDays()
	{
		return repairDays;
	}
	
	public int getQuantity()
	{
		return (Integer)spinner.getValue();
	}
}
