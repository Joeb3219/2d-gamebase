package com.charredgames.game.game;

import java.util.HashMap;
import java.util.Map;

import com.charredgames.game.game.graphics.Tile;
import com.charredgames.game.game.inventory.Material;

public class Controller {

	/*
	 * Dev options
	 */
	
	public static final boolean debug = true;
	
	/*
	 * Controller
	 */
	
	public static final int _TILESIZE = 8;
	public static Map<Integer, Tile> tileIdentifiers = new HashMap<Integer, Tile>();
	public static Map<Integer, Material> materialIdentifiers = new HashMap<Integer, Material>();
	
	public static void addTile(int identifier, Tile tile){
		tileIdentifiers.put(identifier, tile);
	}

	public static void addMaterial(int id, Material material){
		materialIdentifiers.put(id, material);
	}
	
}
