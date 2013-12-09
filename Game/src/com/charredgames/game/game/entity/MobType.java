package com.charredgames.game.game.entity;

public enum MobType {

	MOB(20, 20), PLAYER(20, 50);
	
	private int health, maxHealth;
	
	private MobType(int health, int maxHealth){
		this.health = health;
		this.maxHealth = maxHealth;
	}
	
	public int getHealth(){
		return health;
	}
	
	public int getMaxHealth(){
		return maxHealth;
	}
	
}
