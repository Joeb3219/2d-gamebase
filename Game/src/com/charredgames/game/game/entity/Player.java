package com.charredgames.game.game.entity;

import com.charredgames.game.game.Keyboard;

public class Player extends Mob{

	public Player(Keyboard input){
		super(input);
		this.x = 40;
		this.y = 40;
	}
	
	public void update(){
		if(input.down) y += 2;
		if(input.right) x += 2;
		if(input.left) x -= 2;
		if(input.jump) y -= 2;
		
		if(y < 0) y = 0;
		if(y > 256 * 8) y = 256 * 8;
	}
	
}
