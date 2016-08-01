package com.framework.qa.webelementcore.elementbase.util;

import com.framework.qa.webelementcore.elementbase.core.BaseButton;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import org.apache.log4j.Logger;

public class KeyStrokeExecutor {

	private Robot robot;
	final static Logger log = Logger.getLogger(KeyStrokeExecutor.class);

	public KeyStrokeExecutor() throws AWTException {
		this.robot = new Robot();
	}

	public void executeKeyStroke(String keystroke) {
		KeyStroke keyStroke = KeyStroke.getKeyStroke(keystroke);
		this.pressKeyWithRobot(keyStroke);
	}

	private void pressKeyWithRobot(KeyStroke keyStroke) {
		int modifiers = keyStroke.getModifiers();
		pressModifierKeys(modifiers, robot);

		if (keyStroke.getKeyCode() != 0) {
			robot.keyPress(keyStroke.getKeyCode());
			robot.keyRelease(keyStroke.getKeyCode());
		}

		if (keyStroke.getKeyCode() == 0 && (int) keyStroke.getKeyChar() != 0) {
			robot.keyPress((int) keyStroke.getKeyChar());
			robot.keyRelease((int) keyStroke.getKeyChar());
		}
		releaseModifierKeys(modifiers, robot);
	}

	private void releaseModifierKeys(int modifiers, Robot robot) {
		if ((modifiers & KeyEvent.SHIFT_MASK) != 0) {
			robot.keyRelease(KeyEvent.VK_SHIFT);
		}
		if ((modifiers & KeyEvent.CTRL_MASK) != 0) {
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
		if ((modifiers & KeyEvent.ALT_MASK) != 0) {
			robot.keyRelease(KeyEvent.VK_ALT);
		}
		if ((modifiers & KeyEvent.META_MASK) != 0) {
			robot.keyRelease(KeyEvent.VK_META);
		}
		if ((modifiers & KeyEvent.ALT_GRAPH_MASK) != 0) {
			robot.keyRelease(KeyEvent.VK_ALT_GRAPH);
		}
	}

	private void pressModifierKeys(int modifiers, Robot robot) {
		if ((modifiers & KeyEvent.SHIFT_MASK) != 0) {
			robot.keyPress(KeyEvent.VK_SHIFT);
		}
		if ((modifiers & KeyEvent.CTRL_MASK) != 0) {
			robot.keyPress(KeyEvent.VK_CONTROL);
		}
		if ((modifiers & KeyEvent.ALT_MASK) != 0) {
			robot.keyPress(KeyEvent.VK_ALT);
		}
		if ((modifiers & KeyEvent.META_MASK) != 0) {
			robot.keyPress(KeyEvent.VK_META);
		}
		if ((modifiers & KeyEvent.ALT_GRAPH_MASK) != 0) {
			robot.keyPress(KeyEvent.VK_ALT_GRAPH);
		}
	}
}
