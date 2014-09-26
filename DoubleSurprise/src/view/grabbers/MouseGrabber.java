package view.grabbers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public class MouseGrabber extends JLabel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4549790852581297038L;
	private int button = 0;

	public MouseGrabber(String title) {
		// setPreferredSize(new Dimension(50, 20));
		setBackground(Color.GRAY);
		setText(title);
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(e.getButton()){
			case MouseEvent.BUTTON1: {
				setText("Button 1");
				button = MouseEvent.BUTTON1_MASK;
				break;
			}
			case MouseEvent.BUTTON2: {
				setText("Button 2");
				button = MouseEvent.BUTTON2_MASK;
				break;
			}
			case MouseEvent.BUTTON3: {
				setText("Button 3");
				button = MouseEvent.BUTTON3_MASK;
				break;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public int getButton() {
		return button;
	}
	public void setButton(int button) {
		this.button = button;
		switch(button){
			case MouseEvent.BUTTON1_MASK: {
				setText("Button 1");
				break;
			}
			case MouseEvent.BUTTON2_MASK: {
				setText("Button 2");
				break;
			}
			case MouseEvent.BUTTON3_MASK: {
				setText("Button 3");
				break;
			}
		}
	}

}
