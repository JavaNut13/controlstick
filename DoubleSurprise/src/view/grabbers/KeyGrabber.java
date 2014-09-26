package view.grabbers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class KeyGrabber extends JTextField implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -338533894238637561L;
	private int keyCode = KeyEvent.VK_UNDEFINED;

	public KeyGrabber() {
		addKeyListener(this);
		setFocusTraversalKeysEnabled(false);
		this.setPreferredSize(new Dimension(50, 20));
		setBackground(Color.WHITE);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyCode = e.getKeyCode();
		setText(Character.toString(e.getKeyChar()));
		setBackground(Color.GREEN);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyCode = e.getKeyCode();
		setText(Character.toString(e.getKeyChar()));
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public int getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(int code) {
		keyCode = code;
		setText(Character.toString((char) code));
	}
}
