package view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

public class SettingsFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -636150873336889383L;
	public ListPane controls = null;
	public JSplitPane splitPane = null;
	public JPanel sidePane = null;
	public ListPane controllers = null;
	public AnaloguePane analogueControls = null;
	public ButtonPane buttonControls = null;
	public JButton runMenuItem = null;
	public JButton testMenuItem = null;
	public JButton saveMenuItem = null;
	public JButton openMenuItem = null;

	public SettingsFrame() {
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controllers = new ListPane();
		controls = new ListPane();
		analogueControls = new AnaloguePane();
		buttonControls = new ButtonPane();

		JToolBar toolBar = new JToolBar();
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(toolBar, BorderLayout.PAGE_START);

		JSplitPane leftPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, controllers, controls);
		leftPane.setDividerLocation(200);

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, new JPanel());
		pane.add(splitPane, BorderLayout.CENTER);
		setContentPane(pane);
		splitPane.setDividerLocation(300);

		addTools(toolBar);
	}

	private void addTools(JToolBar toolBar) {

		runMenuItem = new JButton("Run");
		toolBar.add(runMenuItem);

		testMenuItem = new JButton("Start test");
		toolBar.add(testMenuItem);

		saveMenuItem = new JButton("Save");
		toolBar.add(saveMenuItem);

		openMenuItem = new JButton("Open");
		toolBar.add(openMenuItem);
	}
}
