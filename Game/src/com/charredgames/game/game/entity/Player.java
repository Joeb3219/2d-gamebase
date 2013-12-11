package com.charredgames.game.game.entity;

import com.charredgames.game.game.Keyboard;

public class Player extends Mob{

	public Player(Keyboard input){
		super(input);
		this.x = 40;
		this.y = 40;
	}
	
	public void update(){
		if(input.down) y++;
		if(input.right) x++;
		if(input.left) x--;
		if(input.jump) y--;
		
		if(y < 0) y = 0;
		if(y > 128 * 8) y = 128 * 8;
	}
	
}
