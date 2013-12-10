package com.charredgames.game.game.world;

import java.util.ArrayList;

public class World {

	private ArrayList<Chunk> chunks = new ArrayList<Chunk>();
	
	public World(){
		generateWorld();
	}
	
	private void generateWorld(){
		for(int i = -2; i <= 2; i ++){
			Chunk chunk = new Chunk(i);
			chunk.generateRandomChunk();
			chunks.add(chunk);
		}
	}
	
}
