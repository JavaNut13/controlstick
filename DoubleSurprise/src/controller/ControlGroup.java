package controller;

import java.util.ArrayList;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import reader.ControlMap;

public class ControlGroup extends ArrayList<ControlMap> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8910357118620177366L;

	public boolean addOrUpdate(ControlMap cm) {
		for(int i = 0; i < size(); i++) {
			if(get(i).isControl(cm.getControllerType(), cm.getIdentifier())) {
				this.set(i, cm);
				return true;
			}
		}
		add(cm);
		return false;
	}
	public ControlMap get(Controller cont, Component.Identifier iden) {
		for(ControlMap cm : this) {
			if(cm.isControl(cont, iden)) {
				return cm;
			}
		}
		return null;
	}
	@Override
	public String toString() {
		String res = "";
		for(ControlMap cm : this) {
			res += cm.toString() + "\n";
		}
		return res;
	}
	public ControlMap[] toList() {
		ControlMap[] items = new ControlMap[size()];
		int i = 0;
		for(ControlMap cm : this) {
			items[i] = cm;
			System.out.println(cm);
			i++;
		}
		return items;
	}
	public static ControlGroup fromList(ControlMap[] cms) {
		ControlGroup cg = new ControlGroup();
		for(ControlMap c : cms) {
			cg.add(c);
		}
		return cg;
	}
	public Controller[] getControllers(Controller[] allControllers) {
		ArrayList<Controller> usedControllers = new ArrayList<Controller>();
		for(Controller c : allControllers) {
			for(ControlMap cm : this) {
				if(cm.getControllerType().equals(c.getType().toString())) {
					usedControllers.add(c);
					break;
				}
			}
		}
		int i = 0;
		Controller[] controllerArray = new Controller[usedControllers.size()];
		for(Controller co : usedControllers) {
			controllerArray[i] = co;
			i++;
		}
		return controllerArray;
	}
}
