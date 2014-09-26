package reader;

import java.awt.AWTException;
import java.awt.Robot;
import net.java.games.input.Controller;
import net.java.games.input.EventQueue;

public abstract class AbstractEventReader implements Runnable {
	protected ControlMap[] map;
	protected Controller[] controllers;
	protected Robot bot = null;

	public AbstractEventReader(ControlMap[] cm, Controller[] controllers) {
		map = cm;
		this.controllers = controllers;
	}
	public abstract void handleQueue(EventQueue eq, Controller c);

	@Override
	public void run() {
		bot = null;
		try {
			bot = new Robot();
		} catch(AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while(true) {
				for(Controller controller : controllers) {
					controller.poll();
					EventQueue queue = controller.getEventQueue();
					handleQueue(queue, controller);
				}
				Thread.sleep(15);

			}
		} catch(InterruptedException e) {
			System.out.println("stopped");
		}
	}
}
