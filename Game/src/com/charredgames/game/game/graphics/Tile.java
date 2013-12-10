package com.charredgames.game.game.graphics;

import com.charredgames.game.game.Controller;

public class Tile {

	private Sprite sprite;
	private boolean isSolid;
	
	public static final Tile AIR = new Tile(0, Sprite.nullSprite, false);
	public static final Tile a = new Tile(1, Sprite.nullSprite, true);
	public static final Tile b = new Tile(2, Sprite.nullSprite, true);
	public static final Tile c = new Tile(3, Sprite.nullSprite, true);
	public static final Tile d = new Tile(4, Sprite.nullSprite, true);
	public static final Tile e = new Tile(5, Sprite.nullSprite, true);
	public static final Tile f = new Tile(6, Sprite.nullSprite, true);
	public static final Tile g = new Tile(7, Sprite.nullSprite, true);
	public static final Tile h = new Tile(8, Sprite.nullSprite, true);
	public static final Tile i = new Tile(9, Sprite.nullSprite, true);
	public static final Tile j = new Tile(10, Sprite.nullSprite, true);
	public static final Tile k = new Tile(11, Sprite.nullSprite, true);
	public static final Tile l = new Tile(12, Sprite.nullSprite, true);
	public static final Tile m = new Tile(13, Sprite.nullSprite, true);
	public static final Tile n = new Tile(14, Sprite.nullSprite, true);
	public static final Tile o = new Tile(15, Sprite.nullSprite, true);
	
	
	public Tile(int identifier, Sprite sprite, boolean solid){
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
	
}
