package com.charredgames.game.game.graphics;

import java.util.Random;

import com.charredgames.game.game.Controller;

public class Tile {

	private Sprite sprite;
	private boolean isSolid;
	private int dropChance = 0;
	private static Random rand = new Random();
	
	
	
	public Tile(int identifier, Sprite sprite, boolean solid, int dropChance){
		this.sprite = sprite;
		this.isSolid = solid;
		this.dropChance = dropChance;
		Controller.addTile(identifier, this);
	}
	
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

	public boolean dropped(){
		if(dropChance == 0) return false;
		if(rand.nextInt(100) < dropChance && rand.nextInt(100) < dropChance) return true;
		return false;
	}
	
}
