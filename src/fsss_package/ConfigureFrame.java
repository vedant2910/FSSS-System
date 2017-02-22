package fsss_package;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConfigureFrame extends JFrame {
	
	private JPanel contentPane;
	private Mainpage mp;
	private JTable MachineTable;
	private JTable AdjusterTable;
	private MachineItemPanel mip;
	private AdjusterItemPanel aip;
	private JSpinner simulationTime;
	private JButton btnAddMachine, btnAddAdjuster, btnDone;
	public DefaultTableModel MachineModel,AdjusterModel;
	
	public ArrayList<MachineGroup> machineGroup = new ArrayList<MachineGroup>();//The array of machine groups
	public ArrayList<AdjusterGroup> adjusterGroup = new ArrayList<AdjusterGroup>();//The array of adjuster groups
	public ArrayList<String> machineTypes = new ArrayList<String>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigureFrame frame = new ConfigureFrame(new Mainpage());
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
	public ConfigureFrame(Mainpage mainpage) {
		setResizable(false);
		mp = mainpage;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mp.mvp.setVisible(true);
				mp.setVisible(true);
			}
		});			
		
		setTitle("FSSS");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(827, 689);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mip = new MachineItemPanel(this);
		mip.setBounds(10, 293, 323, 187);		
		contentPane.add(mip);
		mip.setVisible(false);
						
		MachineTable = new JTable();
		MachineTable.setFillsViewportHeight(true);
		MachineModel = new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"Machine Type", "MTTF(Days)", "Quantity"
				}
			);
		MachineTable.setModel(MachineModel);
		MachineTable.getColumnModel().getColumn(0).setPreferredWidth(146);
		MachineTable.getColumnModel().getColumn(1).setPreferredWidth(92);
		//MachineTable.setBounds(10, 46, 292, 166);
		JScrollPane machineJSP = new JScrollPane(MachineTable);	
		machineJSP.setBounds(10, 46, 323, 175);
		contentPane.add(machineJSP);
		
		JLabel lblMachineDetails = new JLabel("Machine Details");
		lblMachineDetails.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblMachineDetails.setBounds(75, 9, 158, 26);
		contentPane.add(lblMachineDetails);
		
		btnAddMachine = new JButton("Add Machine");
		btnAddMachine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Check data			
				mip.reset();
				mip.setVisible(true);
			}
		});
		btnAddMachine.setBounds(91, 233, 142, 33);
		contentPane.add(btnAddMachine);
		
		btnDone = new JButton("Done");
		btnDone.setEnabled(false);
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//Check for empty fields
			MachineGroup tempMachineGroup[] = new MachineGroup[machineGroup.size()];
			tempMachineGroup = machineGroup.toArray(tempMachineGroup);
			
			AdjusterGroup tempAdjusterGroup[] = new AdjusterGroup[adjusterGroup.size()];
			tempAdjusterGroup = adjusterGroup.toArray(tempAdjusterGroup);
				
			mp.machineGroup = tempMachineGroup;
			mp.adjusterGroup = tempAdjusterGroup;
			mp.simulationTime = 365 * ((Integer)simulationTime.getValue());
				
			setVisible(false);
			mp.mvp.setVisible(true);
			mp.setVisible(true);
			
			//Now Simulation can be done
			mp.ConfigurationFlag =1;
			}
		});
		btnDone.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnDone.setBounds(10, 605, 800, 33);
		contentPane.add(btnDone);
		
		JLabel lblAdjusterDetails = new JLabel("Adjuster Details");
		lblAdjusterDetails.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
		lblAdjusterDetails.setBounds(482, 10, 219, 22);
		contentPane.add(lblAdjusterDetails);
		
		aip = new AdjusterItemPanel(this);
		aip.setBounds(402, 280, 360, 269);		
		contentPane.add(aip);
		aip.setVisible(false);
				
		AdjusterTable = new JTable();
		AdjusterTable.setFillsViewportHeight(true);
		AdjusterModel = new DefaultTableModel(
				new Object[][] {},
					new String[] {
						"Adjuster ID", "Types of Machine(s)", "Repair Times", "Quantity"
					}
				);
		AdjusterTable.setModel(AdjusterModel);
		AdjusterTable.getColumnModel().getColumn(0).setPreferredWidth(98);
		AdjusterTable.getColumnModel().getColumn(1).setPreferredWidth(122);
		AdjusterTable.getColumnModel().getColumn(2).setPreferredWidth(82);
		AdjusterTable.getColumnModel().getColumn(3).setPreferredWidth(58);
		JScrollPane adjusterJSP = new JScrollPane(AdjusterTable);	
		adjusterJSP.setBounds(365, 46, 445, 175);
		contentPane.add(adjusterJSP);
		
		
		btnAddAdjuster = new JButton("Add Adjuster");
		btnAddAdjuster.setEnabled(false);
		btnAddAdjuster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aip.machineTypes = machineTypes;
				
				aip.reset();
				aip.setVisible(true);
			}
		});
		btnAddAdjuster.setBounds(499, 233, 180, 33);
		contentPane.add(btnAddAdjuster);
		
		JLabel lblSimlationTime = new JLabel("Simulation Time(Years)");
		lblSimlationTime.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
		lblSimlationTime.setBounds(171, 578, 243, 15);
		contentPane.add(lblSimlationTime);
				
		simulationTime = new JSpinner(new SpinnerNumberModel(1,1,1000,1));
		simulationTime.setBounds(448, 573, 66, 20);
		contentPane.add(simulationTime);
				
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 277, 323, 5);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 561, 800, 5);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(365, 277, 445, 5);
		contentPane.add(separator_2);
		
		setLocationRelativeTo(null);
	}

	public void machineAdded() {
		// TODO Auto-generated method stub		
		mip.setVisible(false);
		MachineGroup temp = new MachineGroup(mip.getType(),mip.getMTTF(), mip.getQuantity());
		machineGroup.add(temp);
		machineTypes.add(mip.getType());
		
		//Adding to table
		String row[] = {temp.mach[0].getName(),""+temp.mach[0].getMTTF(),""+temp.mach.length}; 
		MachineModel.addRow(row);
		
		//Enabling Adjuster buttons
		btnAddAdjuster.setEnabled(true);
	}
	
	public void adjusterAdded()
	{
		aip.setVisible(false);
		AdjusterGroup temp = new AdjusterGroup(aip.getID(), aip.getQuantity(), aip.getTypes(), aip.getRepairDays());			
		adjusterGroup.add(temp);
				
		//Adding to table
		ArrayList<String> TemporaryTypes = new ArrayList<String>(aip.getTypes());
		ArrayList<Integer> TemporaryRepairDays = new ArrayList<Integer>(aip.getRepairDays());		
		
		String TypesParameter =TemporaryTypes.get(0);
		for(int i=1;i<TemporaryTypes.size();++i)
		{
			TypesParameter = TypesParameter+ ","+ TemporaryTypes.get(i);
		}
		
		String RepairParameter =""+TemporaryRepairDays.get(0);				
		for(int i=1;i<TemporaryRepairDays.size();++i)
		{
			RepairParameter = RepairParameter+ ","+ TemporaryRepairDays.get(i);
		}
		
		String row[] = {temp.ID, TypesParameter, RepairParameter, ""+temp.numOfAdj};
		AdjusterModel.addRow(row);
		
		//Enable Done Button
		btnDone.setEnabled(true);
	}
	
}
