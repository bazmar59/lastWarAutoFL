package bazmar.lastwar.autofl.io;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.slf4j.LoggerFactory;

import bazmar.lastwar.autofl.LastWarMain;
import bazmar.lastwar.autofl.data.BotType;
import bazmar.lastwar.autofl.data.Constants;
import bazmar.lastwar.autofl.data.Stats;
import bazmar.lastwar.autofl.utils.Utils;
import ch.qos.logback.classic.Logger;

public class Frame {

	private static Logger logger = (Logger) LoggerFactory.getLogger("Frame");

	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 1040;

	private static JLabel status = new JLabel("", JLabel.CENTER);
	private static JLabel currentBOT = new JLabel("", JLabel.CENTER);
	private static JLabel timer = new JLabel("", JLabel.CENTER);
	private static JTextArea logsTextArea = new JTextArea(50, 50);
	private static JFrame frame = new JFrame("Bazmar FL Bot v2.0.1");
	private static JButton buttonScreenDebugFl = new JButton("FL debug screenshot");
	private static JButton buttonScreenDebug1 = new JButton("SCREEN 1 debug screenshot");
	private static JButton buttonScreenDebug2 = new JButton("SCREEN 2 debug screenshot");
	private static JButton buttonResetRecoveryCount = new JButton("RESET Recovery");
	private static JButton buttonResetStats = new JButton("RESET Stats");
	private static JButton buttonUpPauseBetweenFlRoutine = new JButton("+");
	private static JButton buttonDownPauseBetweenFlRoutine = new JButton("-");
	private static JLabel pauseBetweenFlRoutine = new JLabel("Pause FL routine:", JLabel.CENTER);
	private static JLabel pauseBetweenFlRoutineValue = new JLabel(
			String.valueOf(LastWarMain.PAUSE_BETWEEN_FL_ROUTINE) + " ms", JLabel.CENTER);

	private static JButton buttonUpPauseFL = new JButton("+");
	private static JButton buttonDownPauseFL = new JButton("-");
	private static JLabel pauseBetweenFL = new JLabel("Pause FL actions:", JLabel.CENTER);
	private static JLabel pauseBetweenFLValue = new JLabel(String.valueOf(LastWarMain.PAUSE_BETWEEN_FL_ACTION) + " ms",
			JLabel.CENTER);

	private static JLabel statsJLabel = new JLabel("", JLabel.CENTER);
	private static JButton buttonPause = new JButton("");

	public static void createAndShowGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new java.awt.Insets(5, 0, 5, 0);
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(new JLabel("Last war FL BOT ^^", JLabel.CENTER), gbc);

		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(new JLabel("Pause/Restart with 'Pause' key", JLabel.LEFT), gbc);

		gbc.gridy = 2;
		panel.add(new JLabel("Quit with 'Echap' key", JLabel.LEFT), gbc);

		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(new JLabel("CONF", JLabel.CENTER), gbc);

		JPanel panelOverrideConf = new JPanel(new GridLayout(3, 4));
		panelOverrideConf.add(pauseBetweenFlRoutine);
		panelOverrideConf.add(pauseBetweenFlRoutineValue);
		panelOverrideConf.add(buttonUpPauseBetweenFlRoutine);
		panelOverrideConf.add(buttonDownPauseBetweenFlRoutine);

		panelOverrideConf.add(new JLabel("", JLabel.CENTER));
		panelOverrideConf.add(new JLabel("", JLabel.CENTER));
		panelOverrideConf.add(new JLabel("", JLabel.CENTER));
		panelOverrideConf.add(new JLabel("", JLabel.CENTER));

		panelOverrideConf.add(pauseBetweenFL);
		panelOverrideConf.add(pauseBetweenFLValue);
		panelOverrideConf.add(buttonUpPauseFL);
		panelOverrideConf.add(buttonDownPauseFL);

		gbc.gridy = 4;
		gbc.gridwidth = 2;
		panel.add(panelOverrideConf, gbc);

		gbc.gridy = 5;
		gbc.gridwidth = 2;
		panel.add(new JLabel("DEBUG", JLabel.CENTER), gbc);

		JPanel panelDebugScreen = new JPanel(new GridLayout(2, 3));
		panelDebugScreen.add(buttonScreenDebugFl);
		panelDebugScreen.add(buttonScreenDebug1);
		panelDebugScreen.add(buttonScreenDebug2);
		panelDebugScreen.add(buttonResetRecoveryCount);
		panelDebugScreen.add(buttonResetStats);

		gbc.gridy = 6;
		panel.add(panelDebugScreen, gbc);

		gbc.gridy = 7;
		gbc.gridwidth = 2;
		panel.add(new JLabel("TIMER", JLabel.CENTER), gbc);

		gbc.gridy = 8;
		panel.add(timer, gbc);

		gbc.gridy = 9;
		gbc.gridwidth = 2;
		panel.add(new JLabel("STATUS (Started at %s)".formatted(Utils.humanReadableDate()), JLabel.CENTER), gbc);

		gbc.gridy = 10;
		panel.add(status, gbc);

		gbc.gridy = 11;
		panel.add(currentBOT, gbc);

		gbc.gridy = 12;
		panel.add(buttonPause, gbc);

		gbc.gridy = 13;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("STATS", JLabel.CENTER), gbc);
		JPanel panelStat = new JPanel(new GridLayout(1, 1));
		panelStat.add(statsJLabel);

		gbc.gridy = 14;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(panelStat, gbc);

		JPanel panelLogs = new JPanel(new BorderLayout());
		logsTextArea.setEditable(false);
		logsTextArea.setLineWrap(true);
		logsTextArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(logsTextArea);
		panelLogs.add(scrollPane, BorderLayout.CENTER);

		frame.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.1;
		c.gridy = 0;
		c.gridx = 0;
		frame.add(panel, c);

		c.weighty = 0.9;
		c.gridy = 1;
		frame.add(panelLogs, c);

		updateStatusMessage(LastWarMain.PAUSE);

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.validate();
		frame.setLocationRelativeTo(null);
		updateLocation(0, 0, 0);

		buttonScreenDebugFl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Screen.getInstance(BotType.FL).screenDebug("FL_AskedByUserInterface");
			}
		});

		buttonScreenDebug1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Screen.getInstance(Constants.DEFAULT_CONTEXT).screenDebug("SCREEN1_AskedByUserInterface");
			}
		});
		buttonScreenDebug2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Screen.getInstance(Constants.DEFAULT_CONTEXT_2).screenDebug("SCREEN2_AskedByUserInterface");
			}
		});

		buttonResetRecoveryCount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LastWarMain.resetRecoveryCount();
			}
		});

		buttonResetStats.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LastWarMain.resetStats();
				repaint();
			}
		});

		buttonUpPauseBetweenFlRoutine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LastWarMain.PAUSE_BETWEEN_FL_ROUTINE = LastWarMain.PAUSE_BETWEEN_FL_ROUTINE + 100;
				pauseBetweenFlRoutineValue.setText(String.valueOf(LastWarMain.PAUSE_BETWEEN_FL_ROUTINE) + " ms");
			}
		});

		buttonDownPauseBetweenFlRoutine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LastWarMain.PAUSE_BETWEEN_FL_ROUTINE = LastWarMain.PAUSE_BETWEEN_FL_ROUTINE - 100;
				pauseBetweenFlRoutineValue.setText(String.valueOf(LastWarMain.PAUSE_BETWEEN_FL_ROUTINE) + " ms");
			}
		});

		buttonUpPauseFL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LastWarMain.PAUSE_BETWEEN_FL_ACTION = LastWarMain.PAUSE_BETWEEN_FL_ACTION + 100;
				pauseBetweenFLValue.setText(String.valueOf(LastWarMain.PAUSE_BETWEEN_FL_ACTION) + " ms");
			}
		});

		buttonDownPauseFL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LastWarMain.PAUSE_BETWEEN_FL_ACTION = LastWarMain.PAUSE_BETWEEN_FL_ACTION - 100;
				pauseBetweenFLValue.setText(String.valueOf(LastWarMain.PAUSE_BETWEEN_FL_ACTION) + " ms");
			}
		});

		buttonPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LastWarMain.PAUSE = !LastWarMain.PAUSE;
				updateStatusMessage(LastWarMain.PAUSE);
			}
		});

		frame.setVisible(true);
	}

	public static void updateLogs(List<String> logs) {
		logger.debug("updateLogs %s".formatted(logs.size()));
		String logsArea = "";
		for (String current : logs) {
			logsArea += current + "\n";
		}

		logsTextArea.setText(logsArea);
		repaint();
	}

	public static void updateFrameStats(Stats stats) {
		statsJLabel.setText(Utils.generateHtmlStats(stats));
		repaint();
	}

	public static void updateStatusMessage(boolean pause) {
		if (!pause) {
			status.setText("RUNNING");
			status.setForeground(Color.GREEN);
			buttonPause.setText("PAUSE");
		} else {
			status.setText("PAUSED");
			status.setForeground(Color.RED);
			buttonPause.setText("PLAY");
		}

		repaint();
	}

	public static void updateCurrentBot(BotType botType) {
		currentBOT.setText(botType.name());
		repaint();
	}

	public static void updateLocation(int x, int y, int screenIndex) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] screens = ge.getScreenDevices();
		GraphicsDevice screen = screens[screenIndex];
		GraphicsConfiguration config = screen.getDefaultConfiguration();
		Rectangle bounds = config.getBounds();

		logger.info("x={} y={} screenIndex={} bounds.x={} bounds.y={}", x, y, screenIndex, bounds.x, bounds.y);
		frame.setLocation(x + bounds.x, y + bounds.y);
		frame.setVisible(true);
		repaint();
	}

	public static void repaint() {
		frame.revalidate();
		frame.repaint();
	}
}
