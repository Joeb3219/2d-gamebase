package com.charredgames.game.game.graphics;

import com.charredgames.game.game.Controller;

public class Tile {

	private Sprite sprite;
	private boolean isSolid;
	private String name;
	
	public static final Tile AIR = new Tile("AIR", 0, new Sprite(8, 0xFFE6E6E6), false);
	public static final Tile DIRT = new Tile("DIRT", 1, new Sprite(8, 0xFF855400), true);
	public static final Tile STONE = new Tile("STONE", 2, new Sprite(8, 0xFF525252), true);
	public static final Tile COAL = new Tile("COAL", 10, new Sprite(8, 0xFF000000), true);
	public static final Tile IRON = new Tile("IRON", 11, new Sprite(8, 0xFFCCA768), true);
	public static final Tile URANIUM = new Tile("URANIUM", 12, new Sprite(8, 0xFF2AD417), true);
	public static final Tile PLUTONIUM = new Tile("PLUTONIUM", 13, new Sprite(8, 0xFF056967), true);
	public static final Tile IRIDUM = new Tile("IRIDUM", 14, new Sprite(8, 0xFF66FF11), true);
	public static final Tile BEDROCK = new Tile("BEDROCK", 100, Sprite.BEDROCK, true);
	
	
	public Tile(String name, int identifier, Sprite sprite, boolean solid){
		this.name = name;
		this.sprite = sprite;
		this.isSolid = solid;
		Controller.addTile(identifier, this);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x, y, this.sprite);
	}
	
	public boolean isSolid(){
		return isSolid;
	}
	
	public String getName(){
		return name;
	}
	
}
