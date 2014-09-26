package reader;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.io.Serializable;
import net.java.games.input.Component;
import net.java.games.input.Controller;

public class ControlMap implements Serializable {
	// private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	/** 11:47 Sunday */
	private static final long serialVersionUID = 6770862355602380978L;

	// Moves mouse on given axis, only if input is analogue
	public static final int MOUSE_CONTROL = 1;
	public static final int X_AXIS = 0;
	public static final int Y_AXIS = 1;
	// Activates primary on up/ left (-1.0f) and secondary on down/ right (1.0f)
	public static final int ANALOGUE_KEY_CONTROL = 3;
	// Maps button to primary key code (press)
	public static final int BUTTON_KEY_CONTROL = 4;
	// Maps button to primary key code (toggle)
	public static final int BUTTON_TOGGLE_KEY_CONTROL = 5;
	// Maps buttons to up/ down on mouse wheel
	public static final int BUTTON_MOUSE_WHEEL_CONTROL = 6;
	// Maps buttons to left/ right click
	public static final int BUTTON_MOUSE_CLICK_CONTROL = 7;

	private String controllerType = null;
	private String identifier = null;
	private int primaryCode = -1;
	private int secondaryCode = -1;

	private int controlMode = 0;
	private float dx = 0, dy = 0;

	public static final int DEFAULT_MOUSE_SENSITIVITY = 14;
	private int mouseSensitivity = DEFAULT_MOUSE_SENSITIVITY;

	private boolean toggleStatus = false;
	private long toggleTime = 0L;
	private static final long MIN_TOGGLE = 333;

	public ControlMap() {

	}
	// For Everything!?
	public ControlMap(Controller c, Component.Identifier i, int primary, int secondary, int mode) {
		controllerType = c.getType().toString();
		identifier = i.toString();
		primaryCode = primary;
		secondaryCode = secondary;
		controlMode = mode;
	}

	public boolean isControl(Controller c, Component.Identifier i) {
		return isControl(c.getType().toString(), i.toString());
	}
	public boolean isControl(String c, String i) {
		return c.equals(controllerType) && identifier.equals(i);
	}

	public void performAction(Robot bot, float value) {
		switch(controlMode){
			case MOUSE_CONTROL: {
				if(primaryCode == X_AXIS) {
					dx = value;
				} else {
					dy = value;
				}
				break;
			}
			case ANALOGUE_KEY_CONTROL: {
				if(value < -0.3f) {
					bot.keyPress(primaryCode);
				} else {
					bot.keyRelease(primaryCode);
				}
				if(value > 0.3f) {
					bot.keyPress(secondaryCode);
				} else {
					bot.keyRelease(secondaryCode);
				}
				break;
			}
			case BUTTON_KEY_CONTROL: {
				if(value == 1.0f) {
					bot.keyPress(primaryCode);
				} else {
					bot.keyRelease(primaryCode);
				}
				break;
			}
			case BUTTON_TOGGLE_KEY_CONTROL: {
				long now = System.currentTimeMillis();
				if(toggleTime + MIN_TOGGLE < now) {
					if(value == 1.0f) {
						toggleStatus = !toggleStatus;
					} else {
						toggleTime = now;
					}
					if(toggleStatus) {
						bot.keyPress(primaryCode);
						toggleTime = now;
					} else {
						bot.keyRelease(primaryCode);
					}
				}
				break;
			}
			case BUTTON_MOUSE_WHEEL_CONTROL: {
				if(value == 1.0f) {
					bot.mouseWheel(primaryCode);
				}
				break;
			}
			case BUTTON_MOUSE_CLICK_CONTROL: {
				if(value == 1.0f) {
					bot.mousePress(primaryCode);
				} else {
					bot.mouseRelease(primaryCode);
				}
				break;
			}
		}
	}
	public void updateMouse(Robot bot) {
		Point p = MouseInfo.getPointerInfo().getLocation();
		// float dx = this.dx;
		// float dy = this.dy;
		// if(p.x + dx <= 0) {
		// dx = -p.x / 20f;
		// }
		// if(p.y + dy <= 0) {
		// dy = -p.y / 20f;
		// }
		// if(p.x + dx >= SCREEN_SIZE.width) {
		// dx = (SCREEN_SIZE.width - p.x) / 20f;
		// }
		// if(p.y + dy >= SCREEN_SIZE.height) {
		// dy = (SCREEN_SIZE.height - p.y) / 20f;
		// }
		bot.mouseMove(p.x + (int) (mouseSensitivity * dx), p.y + (int) (mouseSensitivity * dy));
	}
	public boolean isMouse() {
		return controlMode == MOUSE_CONTROL;
	}
	public void setController(Controller controller) {
		setController(controller.getType());
	}
	public void setController(Controller.Type type) {
		this.controllerType = type.toString();
	}
	public void setIdentifier(Component.Identifier identifier) {
		this.identifier = identifier.toString();
	}
	public void setPrimaryCode(int primaryCode) {
		this.primaryCode = primaryCode;
	}
	public void setSecondaryCode(int secondaryCode) {
		this.secondaryCode = secondaryCode;
	}
	public void setControlMode(int controlMode) {
		this.controlMode = controlMode;
	}
	public int getPrimaryCode() {
		return primaryCode;
	}
	public int getSecondaryCode() {
		return secondaryCode;
	}
	public int getControlMode() {
		return controlMode;
	}

	public String getControllerType() {
		return controllerType;
	}
	public String getIdentifier() {
		return identifier;
	}
	public int getMouseSensitivity() {
		return mouseSensitivity;
	}
	public void setMouseSensitivity(int mouseSensitivity) {
		this.mouseSensitivity = mouseSensitivity;
	}
	@Override
	public String toString() {
		String co = "null";
		String id = "null";
		if(controllerType != null) {
			co = controllerType.toString();
		}
		if(identifier != null) {
			id = identifier.toString();
		}
		String res = "[Controller: " + co + " id: " + id + " Controlmode: " + Integer.toString(controlMode) + " primary key: " + Integer.toString(primaryCode)
				+ " secondary key: " + Integer.toString(secondaryCode) + "]";
		return res;
	}
}
