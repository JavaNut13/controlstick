package view;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import reader.ControlMap;
import view.grabbers.KeyGrabber;
import view.grabbers.MouseGrabber;
import view.grabbers.MouseScrollGrabber;

public class ButtonPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6388913201178374294L;
	public JRadioButton nothingButton = null;
	public JRadioButton keyPressButton = null;
	public JRadioButton keyToggleButton = null;
	public JRadioButton mouseButtonButton = null;
	public JRadioButton mouseWheelButton = null;
	public KeyGrabber keyGrabber = null;
	public MouseGrabber mouseGrabber = null;
	public MouseScrollGrabber scrollGrabber = null;

	public static final String nothing = "Nothing";
	public static final String keyPress = "Press Key";
	public static final String keyToggle = "Toggle Key";
	public static final String mouseButton = "Mouse Click";
	public static final String mouseWheel = "Mouse Wheel";

	public ButtonPane() {
		this.setLayout(new GridLayout(0, 1));
		setupGui();
	}
	private void setupGui() {

		nothingButton = new JRadioButton(nothing);
		nothingButton.setMnemonic(KeyEvent.VK_N);
		nothingButton.setSelected(true);

		keyPressButton = new JRadioButton(keyPress);
		keyPressButton.setMnemonic(KeyEvent.VK_P);

		keyToggleButton = new JRadioButton(keyToggle);
		keyToggleButton.setMnemonic(KeyEvent.VK_T);

		mouseButtonButton = new JRadioButton(mouseButton);
		mouseButtonButton.setMnemonic(KeyEvent.VK_M);

		mouseWheelButton = new JRadioButton(mouseWheel);
		mouseWheelButton.setMnemonic(KeyEvent.VK_M);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(nothingButton);
		group.add(keyPressButton);
		group.add(keyToggleButton);
		group.add(mouseButtonButton);
		group.add(mouseWheelButton);

		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder("Button Options"));
		p.add(nothingButton);
		p.add(keyPressButton);
		p.add(keyToggleButton);
		p.add(mouseButtonButton);
		p.add(mouseWheelButton);
		add(p);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 1));
		buttonsPanel.setBorder(BorderFactory.createTitledBorder(keyPress + " or " + keyToggle));
		buttonsPanel.add(new JLabel("Key to press/ toggle"));
		keyGrabber = new KeyGrabber();
		buttonsPanel.add(keyGrabber);
		add(buttonsPanel);

		JPanel mousePanel = new JPanel();
		mousePanel.setBorder(BorderFactory.createTitledBorder(mouseButton));
		mouseGrabber = new MouseGrabber("Click to set button");
		mousePanel.add(mouseGrabber);
		add(mousePanel);

		JPanel wheelPanel = new JPanel();
		wheelPanel.setBorder(BorderFactory.createTitledBorder(mouseWheel));
		scrollGrabber = new MouseScrollGrabber();
		wheelPanel.add(scrollGrabber);
		add(wheelPanel);

	}

	public ControlMap getControlMap() {
		if(nothingButton.isSelected()) return null;
		ControlMap cm = new ControlMap();
		if(keyPressButton.isSelected()) {
			cm.setControlMode(ControlMap.BUTTON_KEY_CONTROL);
			cm.setPrimaryCode(keyGrabber.getKeyCode());
		} else if(keyToggleButton.isSelected()) {
			cm.setControlMode(ControlMap.BUTTON_TOGGLE_KEY_CONTROL);
			cm.setPrimaryCode(keyGrabber.getKeyCode());
		} else if(mouseButtonButton.isSelected()) {
			cm.setControlMode(ControlMap.BUTTON_MOUSE_CLICK_CONTROL);
			cm.setPrimaryCode(mouseGrabber.getButton());
		} else if(mouseWheelButton.isSelected()) {
			cm.setControlMode(ControlMap.BUTTON_MOUSE_WHEEL_CONTROL);
			cm.setPrimaryCode(scrollGrabber.getDirection());
		}
		return cm;
	}

	public void setFromControlMap(ControlMap cm) {
		if(cm == null) {
			removeAll();
			setupGui();
			return;
		}
		switch(cm.getControlMode()){
			case ControlMap.BUTTON_KEY_CONTROL: {
				keyPressButton.setSelected(true);
				keyGrabber.setKeyCode(cm.getPrimaryCode());
				break;
			}
			case ControlMap.BUTTON_TOGGLE_KEY_CONTROL: {
				keyToggleButton.setSelected(true);
				keyGrabber.setKeyCode(cm.getPrimaryCode());
				break;
			}
			case ControlMap.BUTTON_MOUSE_CLICK_CONTROL: {
				mouseButtonButton.setSelected(true);
				mouseGrabber.setButton(cm.getPrimaryCode());
				break;
			}
			case ControlMap.BUTTON_MOUSE_WHEEL_CONTROL: {
				mouseWheelButton.setSelected(true);
				scrollGrabber.setDirection(cm.getPrimaryCode());
				break;
			}
		}
	}
}
