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
import view.grabbers.MouseAxisGrabber;

public class AnaloguePane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -460658832872874347L;
	private JRadioButton nothingButton = null;
	private JRadioButton buttonsButton = null;
	private JRadioButton mouseButton = null;
	private KeyGrabber primaryGrabber = null;
	private KeyGrabber secondaryGrabber = null;
	private MouseAxisGrabber mouseAxisGrabber = null;
	public static final String nothing = "Nothing";
	public static final String mouse = "Mouse";
	public static final String buttons = "Buttons";

	public AnaloguePane() {
		this.setLayout(new GridLayout(0, 1));
		setupGui();
	}
	private void setupGui() {
		nothingButton = new JRadioButton(nothing);
		nothingButton.setMnemonic(KeyEvent.VK_N);
		nothingButton.setSelected(true);

		buttonsButton = new JRadioButton(buttons);
		buttonsButton.setMnemonic(KeyEvent.VK_B);

		mouseButton = new JRadioButton(mouse);
		mouseButton.setMnemonic(KeyEvent.VK_M);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(nothingButton);
		group.add(buttonsButton);
		group.add(mouseButton);

		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder("Analogue Options"));
		p.add(nothingButton);
		p.add(buttonsButton);
		p.add(mouseButton);
		add(p);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(BorderFactory.createTitledBorder(buttons));
		primaryGrabber = new KeyGrabber();
		secondaryGrabber = new KeyGrabber();

		buttonsPanel.add(new JLabel("Buttons for left/ up and right/ down"));
		buttonsPanel.add(primaryGrabber);
		buttonsPanel.add(secondaryGrabber);
		add(buttonsPanel);

		JPanel mousePanel = new JPanel();
		mousePanel.setBorder(BorderFactory.createTitledBorder(mouse));
		mouseAxisGrabber = new MouseAxisGrabber();
		mousePanel.add(mouseAxisGrabber);
		add(mousePanel);

	}
	public ControlMap getControlMap() {
		if(nothingButton.isSelected()) return null;
		ControlMap cm = new ControlMap();
		if(buttonsButton.isSelected()) {
			cm.setControlMode(ControlMap.ANALOGUE_KEY_CONTROL);
			cm.setPrimaryCode(primaryGrabber.getKeyCode());
			cm.setSecondaryCode(secondaryGrabber.getKeyCode());
		} else if(mouseButton.isSelected()) {
			cm.setControlMode(ControlMap.MOUSE_CONTROL);
			cm.setPrimaryCode(mouseAxisGrabber.getAxis());
			cm.setMouseSensitivity(mouseAxisGrabber.getSensitivity());
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
			case ControlMap.MOUSE_CONTROL: {
				mouseAxisGrabber.setAxis(cm.getPrimaryCode());
				mouseAxisGrabber.setSensitivity(cm.getMouseSensitivity());
				mouseButton.setSelected(true);
				break;
			}
			case ControlMap.ANALOGUE_KEY_CONTROL: {
				buttonsButton.setSelected(true);
				primaryGrabber.setKeyCode(cm.getPrimaryCode());
				secondaryGrabber.setKeyCode(cm.getSecondaryCode());
				break;
			}
		}
	}
}
