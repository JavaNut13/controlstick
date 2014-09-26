package view.grabbers;

import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MouseScrollGrabber extends JPanel {
	private static final long serialVersionUID = 8595139339957151602L;
	public static final String up = "Up";
	public static final String down = "Down";
	JRadioButton upButton = null;
	JRadioButton downButton = null;

	public MouseScrollGrabber() {
		upButton = new JRadioButton(up);
		upButton.setMnemonic(KeyEvent.VK_U);
		upButton.setSelected(true);

		downButton = new JRadioButton(down);
		downButton.setMnemonic(KeyEvent.VK_D);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(upButton);
		group.add(downButton);

		add(upButton);
		add(downButton);
	}

	public int getDirection() {
		if(upButton.isSelected()) {
			return -1;
		} else {
			return 1;
		}
	}
	public void setDirection(int axis) {
		if(axis == -1) {
			upButton.setSelected(true);
		} else {
			downButton.setSelected(true);
		}
	}

}
