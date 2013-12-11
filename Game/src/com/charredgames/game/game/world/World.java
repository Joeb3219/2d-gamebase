package com.charredgames.game.game.world;

import java.util.ArrayList;

import com.charredgames.game.game.Game;
import com.charredgames.game.game.graphics.Screen;
import com.charredgames.game.game.graphics.Tile;

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

	public Tile getTile(int x, int y){
		x /= 8;
		y /= 8;
		x = (int) Math.floor(x--);
		y = (int) Math.floor(y--);
		
		int chunkId = x/16;
		x -= (chunkId * 16);
		x = Math.abs(x);
		
		for(Chunk chunk : chunks){
			if(chunk.getId() == chunkId) return chunk.getTile(x, y);
		}
		
		return Tile.AIR;
	}
	
	public void render(int xOffset, int yOffset,  Screen screen){
		//int xMin = (int) (playerX - (2.5 * 16));
		//int xMax = (int) (playerX + (2.5 * 16));
		
		int xMax = (int) ((xOffset + (Game._WIDTH / 2)) + (2.5) * 16);
		
		int x0 = (int) ((xOffset + (screen.getWidth()) - (2.5 * 16)) + 8 ) / 8;
		int x1 = (int) ((xOffset + (screen.getWidth()) + (2.5 * 16)) + 8 ) / 8;
		int y0 = (yOffset + screen.getHeight() + 8) / 8;
		int y1 = (128);
		
		for(int y = y0 * 8; y < y1; y ++){
			for(int x = x0; x < x1; x ++){
				getTile(x,y).render(x * 8, y * 8, screen);
			}
		}
		
		/*int x0 = xOffset / 8;
		int y0 = yOffset / 8;
		int x1 = (xOffset + screen.getWidth() + 8) / 8;
		int y1 = (yOffset + screen.getHeight() + 8) / 8;
		for(int y = y0; y < y1; y++){
			for(int x = x0; x < x1; x++){
				getTile(x,y).render(x * 16, y * 16, screen);
			}
		}*/
	}
	
}
