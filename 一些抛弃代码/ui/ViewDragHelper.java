/**
 * 
 */
package ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.SwingUtilities;

/**
 * ��ͼ�϶�������
 */
public class ViewDragHelper {

	private Window fWindow;

	private Component fComponent;
	private int dx, dy;

	/**
	 * ��ָ����Componentͨ������϶����ƶ�Window
	 */
	public ViewDragHelper(Window window, Component component) {

		fWindow = window;
		fComponent = component;

		fComponent.addMouseListener(createMouseListener());
		fComponent.addMouseMotionListener(createMouseMotionListener());
	}

	private MouseListener createMouseListener() {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point clickPoint = new Point(e.getPoint());
				SwingUtilities.convertPointToScreen(clickPoint, fComponent);
				dx = clickPoint.x - fWindow.getX();
				dy = clickPoint.y - fWindow.getY();
				// fWindow.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseReleased(MouseEvent e) {
				fWindow.setCursor(Cursor.getDefaultCursor());
			}
		};
	}

	private MouseMotionAdapter createMouseMotionListener() {
		return new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point dragPoint = new Point(e.getPoint());
				SwingUtilities.convertPointToScreen(dragPoint, fComponent);

				fWindow.setLocation(dragPoint.x - dx, dragPoint.y - dy);
			}
		};
	}
}
