package controller;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;
import reader.AbstractEventReader;
import view.ListPane;

public class EventListSelector extends AbstractEventReader {
	private Component[] components = null;
	private ListPane view = null;

	public EventListSelector(Controller controller, ListPane lp) {
		super(null, new Controller[] { controller });
		this.components = controller.getComponents();
		view = lp;
	}

	@Override
	public void handleQueue(EventQueue queue, Controller c) {
		Event event = new Event();
		while(queue.getNextEvent(event)) {
			Component comp = event.getComponent();
			float value = event.getValue();
			// This will match 'on' for analogue and digital input.
			if(value > 0.9f || value < -0.9f) {
				int i = 0;
				for(Component inList : components) {
					if(inList.getIdentifier() == comp.getIdentifier()) {
						view.setSelectedIndex(i);
						break;
					}
					i++;
				}
			}
		}
	}
}
