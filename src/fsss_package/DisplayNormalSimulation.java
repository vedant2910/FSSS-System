package fsss_package;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.border.BevelBorder;

public class DisplayNormalSimulation extends JFrame {

	public Mainpage mp;

	private JPanel contentPane;
	private JTable table;

	public DefaultTableModel model;
	private JButton btnExportToExcel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Grid frame = new DisplayNormalSimulation(new
					// Mainpage(),90.2, 80.45, {2.3,3.4},);
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DisplayNormalSimulation(Mainpage mainpage,
			float AverageMachineUtilisation, float AverageAdjusterUtilistaion,
			ArrayList<Float> MachineUtilisation, MachineGroup machineGroup[]) {
		setResizable(false);
		setTitle("FSSS");
		mp = mainpage;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mp.setVisible(true);
			}
		});

		int NMachines = MachineUtilisation.size();

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 440, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		model = new DefaultTableModel(new Object[][] {}, new String[] {
				"Field", "Percentage Utilisation" });
		table.setModel(model);

		JScrollPane JSP = new JScrollPane(table);
		JSP.setBounds(12, 16, 410, 294);
		contentPane.add(JSP);
		
		/*Adding data to table*/
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		String secondParameter = df.format((AverageMachineUtilisation * 100));

		String row[] = { "Average Machine Utilisation",
				secondParameter +"%" };
		model.addRow(row);

		secondParameter = df.format((AverageAdjusterUtilistaion * 100));
		String row1[] = { "Average Adjuster Utilisation",
				secondParameter+ "%" };
		model.addRow(row1);

		String row2[] = { "", "" };
		model.addRow(row2);

		for (int i = 0; i < NMachines; ++i) {
			secondParameter = df.format((MachineUtilisation.get(i)) * 100);			
			String rows[] = {
					"Machine" + (i + 1) + " : " + machineGroup[i].mach[0].Name,
					secondParameter + "%" };
			model.addRow(rows);
		}

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mp.setVisible(true);
			}
		});
		btnBack.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnBack.setBounds(267, 322, 98, 28);
		contentPane.add(btnBack);

		btnExportToExcel = new JButton("Export to Excel");
		btnExportToExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Note that i'm actually saving the file first
					ExcelExporter exp = new ExcelExporter();
					File file = new File("Utilization.xls");
					exp.exportTable(table, file);
					ExcelOpener opn = new ExcelOpener();
					opn.openTable(file);
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		btnExportToExcel.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnExportToExcel.setBounds(75, 322, 144, 28);
		contentPane.add(btnExportToExcel);

		setLocationRelativeTo(null);

	}
}
