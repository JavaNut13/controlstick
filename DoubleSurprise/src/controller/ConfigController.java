package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import reader.ControlMap;
import reader.EventReader;
import view.SettingsFrame;

public class ConfigController {
	SettingsFrame view = null;

	private Controller currentController = null;
	private Component currentComponent = null;
	private ControlGroup maps = null;

	private Thread testThread = null;
	private Thread runThread = null;

	public ConfigController() {
		view = new SettingsFrame();
		view.setVisible(true);
		ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
		Controller[] cs = ce.getControllers();
		view.controllers.setContents(cs);
		maps = new ControlGroup();

		addMenuListeners();

		view.controllers.addClickListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Object selected = view.controllers.getSelectedItem();
				if(selected == null) {
					return;
				}
				saveCurrent();
				currentController = (Controller) selected;
				// Update components list
				view.controls.setContents(currentController.getComponents());
			}
		});
		view.controls.addClickListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Object selected = view.controls.getSelectedItem();
				if(selected == null) {
					return;
				}
				saveCurrent();
				currentComponent = (Component) selected;
				ControlMap cm = getCurrentControlMap();
				if(currentComponent.isAnalog()) {
					view.analogueControls.setFromControlMap(cm);
					view.splitPane.setRightComponent(view.analogueControls);
				} else {
					view.buttonControls.setFromControlMap(cm);
					view.splitPane.setRightComponent(view.buttonControls);
				}
			}
		});
	}

	private void addMenuListeners() {
		view.runMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveCurrent();
				if(runThread == null) {
					ControlMap[] map = maps.toList();

					ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
					Controller[] cs = ce.getControllers();
					runThread = new Thread(new EventReader(map, maps.getControllers(cs)));
					runThread.start();
					view.runMenuItem.setText("Stop");
				} else {
					runThread.interrupt();
					runThread = null;
					view.runMenuItem.setText("Run");
				}
			}
		});
		view.testMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(testThread == null) {
					if(currentController != null) {
						testThread = new Thread(new EventListSelector(currentController, view.controls));
						testThread.start();
						view.testMenuItem.setText("Stop test");
					}
				} else {
					testThread.interrupt();
					testThread = null;
					view.testMenuItem.setText("Start test");
				}
			}
		});
		view.openMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					maps = ControlGroup.fromList(ControlMapIO.read());
					System.out.println(maps);
				} catch(FileNotFoundException e) {
					e.printStackTrace();
				} catch(IOException e) {
					e.printStackTrace();
				} catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		view.saveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveCurrent();
				try {
					ControlMapIO.write(maps.toList());
				} catch(IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	private void saveCurrent() {
		if(currentComponent == null || currentController == null) return;
		ControlMap cm = null;
		if(currentComponent.isAnalog()) {
			cm = view.analogueControls.getControlMap();
		} else {
			cm = view.buttonControls.getControlMap();
		}
		if(cm == null) return;
		cm.setController(currentController);
		cm.setIdentifier(currentComponent.getIdentifier());
		maps.addOrUpdate(cm);
	}
	private ControlMap getCurrentControlMap() {
		return maps.get(currentController, currentComponent.getIdentifier());
	}
}
