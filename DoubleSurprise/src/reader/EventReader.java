package reader;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

public class EventReader extends AbstractEventReader {

	public EventReader(ControlMap[] cm, Controller[] controllers) {
		super(cm, controllers);
	}
	@Override
	public void handleQueue(EventQueue queue, Controller controller) {
		Event event = new Event();
		while(queue.getNextEvent(event)) {
			Component comp = event.getComponent();
			float value = event.getValue();
			for(ControlMap m : map) {
				if(m.isControl(controller, comp.getIdentifier())) {
					m.performAction(bot, value);
				}
			}
		}
		for(ControlMap m : map) {
			if(m.isMouse()) {
				m.updateMouse(bot);
			}
		}
	}

}
