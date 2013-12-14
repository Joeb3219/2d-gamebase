package com.charredgames.game.game.entity;

import com.charredgames.game.game.Game;
import com.charredgames.game.game.Keyboard;

public class Player extends Mob{

	public Player(Keyboard input){
		super(input);
		this.x = 40;
		this.y = 40;
	}
	
	public void update(){
		direction = 0;
		if(input.down) y += 2;
		if(input.right) {
			x += 2;
			direction = 2;
		}
		if(input.left) {
			x -= 2;
			direction = 1;
		}
		if(input.jump && !isJumping && !isFalling && Game.world.isStandingOnSolid(x, y)) {
			isJumping = true;
			jumpHeight = 40;
		}
		
		jump();
		
		if(y < 0) y = 0;
		if(y > 256 * 8) y = 256 * 8;
	}
	
}
