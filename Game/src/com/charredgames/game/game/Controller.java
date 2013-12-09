package com.charredgames.game.game;

import java.util.HashMap;
import java.util.Map;

import com.charredgames.game.game.graphics.Tile;

public class Controller {

	public static Map<Integer, Tile> tileColours = new HashMap<Integer, Tile>();
	
	public static void addTile(int identifier, Tile tile){
		tileColours.put(identifier, tile);
	}
	
}
