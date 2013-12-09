package com.charredgames.game.game.graphics;


public class Sprite {

	public final int size;
	private int x, y;
	public int[] pixels;
	private SpriteSheet spriteSheet;

	public static final Sprite nullSprite = new Sprite(16, 0xFF222222);
	
	public Sprite(int size, int x, int y, SpriteSheet spriteSheet){
		this.size = size;
		this.x = x * size;
		this.y = y * size;
		this.spriteSheet = spriteSheet;
		pixels = new int[size * size];
		load();
	}
	
	public Sprite(int size, int colour){
		this.size = size;
		pixels = new int[size * size];
		for(int i = 0; i < pixels.length; i++) pixels[i] = colour;
	}
	
	private void load(){
		for(int y = 0; y < size; y ++){
			for(int x = 0; x < size; x ++){
				pixels[x + y * size] = spriteSheet.pixels[(x + this.x) + (y + this.y) * spriteSheet.size];
			}
		}
	}
	
}
