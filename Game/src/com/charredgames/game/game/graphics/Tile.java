package com.charredgames.game.game.graphics;

import com.charredgames.game.game.Controller;

public class Tile {

	private Sprite sprite;
	private boolean isSolid;
	private String name;
	
	public static final Tile AIR = new Tile("AIR", 0, Sprite.IRON, false);
	public static final Tile DIRT = new Tile("DIRT", 1, Sprite.nullSprite, true);
	public static final Tile STONE = new Tile("STONE", 10, Sprite.nullSprite, true);
	public static final Tile COAL = new Tile("COAL", 11, Sprite.nullSprite, true);
	public static final Tile IRON = new Tile("IRON", 12, Sprite.nullSprite, true);
	public static final Tile URANIUM = new Tile("URANIUM", 13, Sprite.nullSprite, true);
	public static final Tile PLUTONIUM = new Tile("PLUTONIUM", 14, Sprite.nullSprite, true);
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
