package controller;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	public static final String APP_NAME = "ControlStick";

	public static void main(String[] args) {
		// TODO load config from command line?
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ConfigController();
	}
}
