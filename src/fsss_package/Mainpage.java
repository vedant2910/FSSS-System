package fsss_package;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Mainpage extends JFrame {

	public MachineGroup machineGroup[];
	public AdjusterGroup adjusterGroup[];
	public int simulationTime;
	public int ConfigurationFlag = 0;
	
	private JPanel contentPane;
	public MainViewPanel mvp;
	public ConfigureFrame cf;
	public NormalSimulation ns;
	public GraphicalSimulation gs;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	    
		
		try {
            // Set System L&F
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
    } 
    catch (Exception e) {
       // handle exception
    }
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainpage frame = new Mainpage();
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
	public Mainpage() {		
		
		setTitle("FSSS");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(324, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mvp = new MainViewPanel(this);
		mvp.setBounds(0, 0, 994, 571);
		contentPane.add(mvp);		
				
		setLocationRelativeTo(null);
	}
	
	public void configure() {
		// TODO Auto-generated method stub
		setVisible(false);
		mvp.setVisible(false);
		cf = new ConfigureFrame(this);
		setVisible(false);
		cf.setVisible(true);
	}
	public void normalSimulation() {
		// TODO Auto-generated method stub
		//Checking if Configured
		if(ConfigurationFlag == 0)
			JOptionPane.showMessageDialog(null, "Please Configure the Details before Simulating!!", "Error!!", JOptionPane.ERROR_MESSAGE);
		else
		{		
			ns = new NormalSimulation(machineGroup, adjusterGroup, simulationTime);
			float a = ns.getAverageMachineUtilization();
			float b = ns.getAverageAdjusterUtilization();
			ArrayList<Float> c = new ArrayList<Float>(ns.getMachineUtilization());
			DisplayNormalSimulation dns = new DisplayNormalSimulation(this,a,b,c,machineGroup);
			dns.setVisible(true);
			setVisible(false);
			
			//setVisible(false);
		}
	}
	public void graphicalSimulation() {
		// TODO Auto-generated method stub
		if(ConfigurationFlag == 0)
			JOptionPane.showMessageDialog(null, "Please Configure the Details before Simulating!!", "Error!!", JOptionPane.ERROR_MESSAGE);
		else
		{		
			//setVisible(false);
			gs = new GraphicalSimulation(machineGroup, adjusterGroup, simulationTime);
			DisplayGraphicalSimulation dgs = new DisplayGraphicalSimulation(this);
			dgs.setVisible(true);
			
			setVisible(false);
		}
		
	}
}
