package com.charredgames.game.game.entity;

import com.charredgames.game.game.Keyboard;

public class Mob extends Entity{

	protected int health, maxHealth;
	protected MobType type;
	protected Keyboard input;
	
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
}
