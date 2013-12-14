package com.charredgames.game.game.entity;

import com.charredgames.game.game.Controller;
import com.charredgames.game.game.Game;
import com.charredgames.game.game.Keyboard;
import com.charredgames.game.game.graphics.Screen;
import com.charredgames.game.game.graphics.Sprite;

public class Mob extends Entity{

	protected int health, maxHealth;
	protected int direction = 1;
	protected MobType type;
	protected Keyboard input;
	protected Sprite sprite = new Sprite(Controller._TILESIZE, 0xff448844);
	protected boolean isJumping = false, isFalling = false;
	protected int jumpHeight = 0;
	
	public Mob(int x, int y, MobType type){
		this.x = x;
		this.y = y;
		this.type = type;
		this.health = type.getHealth();
		this.maxHealth = type.getMaxHealth();
	}
	
	public Mob(Keyboard input){
		this.input = input;
		this.type = MobType.PLAYER;
		this.health = type.getHealth();
		this.maxHealth = type.getMaxHealth();
	}
	
	public void update(){
		
	}
	
	public int getHealth(){
		return health;
	}

	public int getMaxHealth(){
		return maxHealth;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public void render(Screen screen){
		screen.renderTile(this.x, this.y, sprite);
	}
	
	public void jump(){
		if(isJumping){
			jumpHeight -= 1;
			y -= 1;
			if(direction == 1) x -= 1;
			else if(direction == 2) x += 1;
			if(jumpHeight <= 0) {
				jumpHeight = -40;
				isJumping = false;
				isFalling = true;
			}
		}
		
		else if(isFalling){
			jumpHeight += 1;
			y += 1;
			if(direction == 1) x -= 1;
			else if(direction == 2) x += 1;
			if(jumpHeight >= 0 || Game.world.isStandingOnSolid(x, y)) {
				isJumping = false;
				isFalling = false;
				jumpHeight = 0;
			}
		}
		
		else if(!Game.world.isStandingOnSolid(x, y)){
			isFalling = true;
			isJumping = false;
			jumpHeight = 1;
		}
		
	}
}
