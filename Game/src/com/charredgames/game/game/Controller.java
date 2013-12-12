package com.charredgames.game.game;

import java.util.HashMap;
import java.util.Map;

import com.charredgames.game.game.graphics.Tile;

public class Controller {

	/* http://www.java-gaming.org/index.php?PHPSESSID=0q5u6pr9relos5s6spsdnmjpc2&topic=25516.0
	 * Dev options
	 */
	
	public static final boolean debug = true;
	
	/*
	 * Controller
	 */
	
	public static Map<Integer, Tile> tileIdentifiers = new HashMap<Integer, Tile>();
	
	public static void addTile(int identifier, Tile tile){
		tileIdentifiers.put(identifier, tile);
	}
	
}
