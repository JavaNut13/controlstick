package view.grabbers;

import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import reader.ControlMap;

public class MouseAxisGrabber extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3666533301551401256L;
	public static final String xAxis = "X Axis";
	public static final String yAxis = "Y Axis";
	JRadioButton xButton = null;
	JRadioButton yButton = null;
	JTextField sensitivity = null;
	JCheckBox inverted = null;

	public MouseAxisGrabber() {
		xButton = new JRadioButton(xAxis);
		xButton.setMnemonic(KeyEvent.VK_X);
		xButton.setActionCommand(xAxis);
		xButton.setSelected(true);

		yButton = new JRadioButton(yAxis);
		yButton.setMnemonic(KeyEvent.VK_Y);
		yButton.setActionCommand(yAxis);

		sensitivity = new JTextField();
		sensitivity.setText(Integer.toString(ControlMap.DEFAULT_MOUSE_SENSITIVITY));

		inverted = new JCheckBox("Invert");

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(xButton);
		group.add(yButton);

		add(xButton);
		add(yButton);
		add(new JLabel("Mouse sensitivity"));
		add(sensitivity);
		add(inverted);
	}

	public int getAxis() {
		if(xButton.isSelected()) {
			return ControlMap.X_AXIS;
		} else {
			return ControlMap.Y_AXIS;
		}
	}
	public void setAxis(int axis) {
		if(axis == ControlMap.X_AXIS) {
			xButton.setSelected(true);
		} else {
			yButton.setSelected(true);
		}
	}

	public void setSensitivity(int s) {
		if(s < 0) {
			inverted.setSelected(true);
			s *= -1;
		}
		this.sensitivity.setText(Integer.toString(s));
	}
	public int getSensitivity() {
		int mod = 1;
		if(inverted.isSelected()) {
			mod = -1;
		}
		try {
			int s = Integer.parseInt(sensitivity.getText());
			if(s < 0) {
				s *= -1;
			}
			if(s == 0) {
				return ControlMap.DEFAULT_MOUSE_SENSITIVITY;
			}
			return mod * s;
		} catch(NumberFormatException nfe) {
			return mod * ControlMap.DEFAULT_MOUSE_SENSITIVITY;
		}
	}

}
