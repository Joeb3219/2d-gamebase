package com.charredgames.game.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseMotionListener, MouseListener{

	public static int x, y, button = -1;
	
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
	
	}

	public void mouseExited(MouseEvent e) {
	
	}

	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		button = e.getButton();
	}

	public void mouseReleased(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		button = -1;
	}

}