package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import reader.ControlMap;

public class ControlMapIO {
	private static final String SAVE_FOLDER = System.getProperty("user.home") + File.separatorChar + "." + Main.APP_NAME;;

	private static void createFolder() {
		File folder = new File(SAVE_FOLDER);
		if(!folder.exists()) {
			folder.mkdirs();
		}
	}

	public static File getOpenPath() {
		createFolder();
		JFileChooser chooser = new JFileChooser(SAVE_FOLDER);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Config files", "ser");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}
		return null;
	}

	public static File getSavePath() {
		createFolder();
		JFileChooser chooser = new JFileChooser(SAVE_FOLDER);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Config files", "ser");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}
		return null;
	}

	public static void write(ControlMap[] cm) throws IOException {
		File f = getSavePath();
		if(f == null) {
			return;
		}
		write(cm, f);
	}
	public static ControlMap[] read() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = getOpenPath();
		if(f == null) {
			return null;
		}
		return read(f);
	}

	public static void write(ControlMap[] cm, File file) throws IOException {
		FileOutputStream out = new FileOutputStream(file);
		ObjectOutputStream oout = new ObjectOutputStream(out);
		oout.writeObject(cm);
		oout.close();

	}

	public static ControlMap[] read(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		ControlMap[] cm = (ControlMap[]) ois.readObject();
		ois.close();
		return cm;
	}

}
