package bugbattle.core;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * The main entry point and user interface frame for the BugBattle application.
 * Initializes the World, then waits for the user to press the start button.
 * Once start has been pressed, causes a simulation turn to execute every
 * PAUSE_TIME milliseconds.
 * 
 * @author [your names here]
 * @version [version number here]
 */
public class BugBattle {

	/**
	 * The main application window.
	 */
	private JFrame frmBugBattle;

	/**
	 * The graphical depiction of the World.
	 */
	private WorldView worldView;

	/**
	 * The start button for the simulation.
	 */
	private JButton btnStart;

	/**
	 * The time between successive simulation steps, in milliseconds.
	 */
	public static final int PAUSE_TIME = 250;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BugBattle window = new BugBattle();
					window.frmBugBattle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BugBattle() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBugBattle = new JFrame();
		frmBugBattle.setTitle("Bug Battle");
		frmBugBattle.setResizable(false);
		frmBugBattle.setBounds(100, 100, 535, 586);
		frmBugBattle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBugBattle.getContentPane().setLayout(null);

		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnStart.setEnabled(false);
				new Timer(PAUSE_TIME, new SimulationTurn()).start();
			}
		});
		btnStart.setBounds(230, 6, 75, 29);
		frmBugBattle.getContentPane().add(btnStart);

		worldView = new WorldView();
		worldView.setBackground(Color.LIGHT_GRAY);
		worldView.setBounds(17, 40, 500, 500);
		frmBugBattle.getContentPane().add(worldView);
	}

	/**
	 * The listener called by the simulation Timer. Calls <code>doTurn</code> on
	 * the World, then calls <code>repaint</code> on the WorldView.
	 * 
	 * @author Dr. Greg Phillips and Major Gary Wolfman <- replace with your
	 *         names!
	 * @version 0.2 <- update!
	 */
	private class SimulationTurn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			World.getWorld().doTurn();
			worldView.repaint();
		}

	}
}
