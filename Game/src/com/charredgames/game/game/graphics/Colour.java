package com.charredgames.game.game.graphics;

import java.awt.Color;

public class Colour {

	public Color color;
	
	public static final Colour HUD_HEALTH_COLOUR = new Colour(255, 10, 10, 255);
	public static final Colour HUD_HEALTH_DIVIDER = new Colour(88, 88, 88, 100);
	public static final Colour HUD_INVENTORY_DIVIDER = new Colour(22, 22, 22, 100);
	public static final Colour HUD_INVENTORY_SLOT = new Colour(00, 00, 00, 200);
	public static final Colour HUD_BG = new Colour(33, 33, 33, 100);
	
	public Colour(int r, int g, int b, int a){
		this.color = new Color(r, g, b, a);
	}
	
	public Color getColour(){
		return color;
	}
	
}
