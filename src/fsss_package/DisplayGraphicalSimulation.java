package fsss_package;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

public class DisplayGraphicalSimulation extends JFrame {

	public Mainpage mp;

	private JPanel contentPane;
	public JFreeChart chart;
	public JPanel panel;
	public ChartPanel chartPanel;
	public JComboBox xParameter, yParameter;
	public JSpinner startValue, endValue;
	private JTable table;
	private DefaultTableModel model;
	private JButton btnDrawChart, btnPrintGraph, btnExportToExcel, btnBack;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// DisplayGraphicalSimulation frame = new
					// DisplayGraphicalSimulation();
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
	public DisplayGraphicalSimulation(Mainpage mainpage) {
		setResizable(false);
		setTitle("FSSS");
		mp = mainpage;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mp.mvp.setVisible(true);
				mp.setVisible(true);
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 950, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		xParameter = new JComboBox();
		xParameter.setBounds(254, 19, 116, 26);
		contentPane.add(xParameter);

		AdjusterGroup[] temp = mp.adjusterGroup;

		for (int i = 0; i < temp.length; ++i) {
			xParameter.addItem(temp[i].ID);
		}

		JLabel lblXParameter = new JLabel("X Parameter(Adjuster ID)");
		lblXParameter.setBounds(71, 24, 158, 16);
		contentPane.add(lblXParameter);

		yParameter = new JComboBox();
		yParameter.setBounds(667, 19, 196, 26);
		contentPane.add(yParameter);

		yParameter.addItem("Average Machine Utilisation");
		yParameter.addItem("Average Adjuster Utilisation");

		MachineGroup[] temp1 = mp.machineGroup;

		for (int i = 0; i < temp1.length; ++i) {
			yParameter.addItem(temp1[i].mach[0].Name);
		}

		JLabel lblYParameter = new JLabel("Y Parameter");
		lblYParameter.setBounds(478, 24, 94, 16);
		contentPane.add(lblYParameter);

		JLabel lblStartValue = new JLabel("Start Value");
		lblStartValue.setBounds(71, 65, 94, 16);
		contentPane.add(lblStartValue);

		startValue = new JSpinner();
		startValue.setModel(new SpinnerNumberModel(1, 1, 475, 1));
		startValue.setBounds(264, 59, 59, 28);
		contentPane.add(startValue);

		JLabel lblEndValue = new JLabel("End Value");
		lblEndValue.setBounds(478, 71, 94, 16);
		contentPane.add(lblEndValue);

		endValue = new JSpinner();
		endValue.setModel(new SpinnerNumberModel(26, 26, 500, 1));
		endValue.setBounds(667, 65, 59, 28);
		contentPane.add(endValue);

		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
				null));
		panel.setBounds(6, 185, 669, 431);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnDrawChart = new JButton("Draw Graph");
		btnDrawChart
				.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		btnDrawChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ID = xParameter.getSelectedItem().toString();
				int MachIndex = yParameter.getSelectedIndex();
				int stValue = (Integer) startValue.getValue();
				int eValue = (Integer) endValue.getValue();

				MachIndex -= 2;
				
				//disableAllButtons();
				btnDrawChart.setEnabled(false);
				btnBack.setEnabled(false);
				btnPrintGraph.setEnabled(false);
				btnExportToExcel.setEnabled(false);

				if (MachIndex == -2)
					chart = mp.gs.getChartAvgMac(ID, stValue, eValue);

				else if (MachIndex == -1)
					chart = mp.gs.getChartAvgAdj(ID, stValue, eValue);
				else
					chart = mp.gs.getChart(ID, MachIndex, stValue, eValue);
				
				enableAllButtons();

				chartPanel = new ChartPanel(chart);
				chartPanel.setPreferredSize(new Dimension(650, 420));
				chartPanel.setBorder(BorderFactory
						.createLineBorder(Color.BLACK));

				panel.removeAll();
				panel.add(chartPanel);
				panel.revalidate();
				panel.repaint();

				/* Getting Points */
				int p[] = mp.gs.getint();
				float values[] = mp.gs.getfloat();

				/* Putting points in the Table */
				model = new DefaultTableModel(new Object[][] {}, new String[] {
						"Number of " + ID, "Percentage Utilisation" });
				table.setModel(model);
				model.setRowCount(0);

				for (int i = 0; i < 25; ++i) {
					DecimalFormat df = new DecimalFormat();
					df.setMaximumFractionDigits(2);
					String secondParameter = df.format((values[i] * 100));

					String row[] = { "" + p[i], secondParameter + "%" };
					model.addRow(row);
					}
				
				
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		btnDrawChart.setBounds(346, 140, 161, 33);
		contentPane.add(btnDrawChart);

		table = new JTable();
		model = new DefaultTableModel(new Object[][] {}, new String[] {
				"X Value", "Percentage Utilisation" });
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"X Value", "Percentage Utilisation" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.setFillsViewportHeight(true);
		JScrollPane JSP = new JScrollPane(table);
		JSP.setBounds(687, 185, 251, 431);
		contentPane.add(JSP);

		JLabel lblNoteStart = new JLabel(
				"Note : Start Value and End Value are for X Parameter. Please ensure that these 2 values differ by atleast 25 so that the Graph is proper.");
		lblNoteStart.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNoteStart.setHorizontalAlignment(SwingConstants.LEFT);
		lblNoteStart.setBounds(71, 112, 889, 16);
		contentPane.add(lblNoteStart);

		btnPrintGraph = new JButton("Print Graph");
		btnPrintGraph.setEnabled(false);
		btnPrintGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chartPanel.createChartPrintJob();
			}
		});
		btnPrintGraph.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnPrintGraph.setBounds(113, 629, 116, 28);
		contentPane.add(btnPrintGraph);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnBack.setBounds(482, 629, 90, 28);
		contentPane.add(btnBack);

		btnExportToExcel = new JButton("Export to Excel");
		btnExportToExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Note that i'm actually saving the file first
					ExcelExporter exp = new ExcelExporter();
					File file = new File("VarUtilization.xls");
					exp.exportTable(table, file);
					ExcelOpener opn = new ExcelOpener();
					opn.openTable(file);
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

		});
		btnExportToExcel.setEnabled(false);
		btnExportToExcel.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnExportToExcel.setBounds(746, 628, 161, 29);
		contentPane.add(btnExportToExcel);

		setLocationRelativeTo(null);
	}

	public void disableAllButtons() {
		// TODO Auto-generated method stub
		btnDrawChart.setEnabled(false);
		btnBack.setEnabled(false);
		btnPrintGraph.setEnabled(false);
		btnExportToExcel.setEnabled(false);		
	}
	
	public void enableAllButtons()
	{
		btnPrintGraph.setEnabled(true);
		btnExportToExcel.setEnabled(true);
		btnDrawChart.setEnabled(true);
		btnBack.setEnabled(true);		
	}

}
