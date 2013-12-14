package com.charredgames.game.game.world;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.charredgames.game.game.Controller;
import com.charredgames.game.game.Game;
import com.charredgames.game.game.graphics.Screen;
import com.charredgames.game.game.graphics.Tile;

public class World{

	private ArrayList<Chunk> chunks = new ArrayList<Chunk>();
	//private Thread chunkThread;
	
	public World(){
		generateWorld();
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable(){
			public void run() {
				if(Controller.debug) System.out.println("Running chunk saves");
				int saved = 0;
				for(Chunk chunk : chunks){
					if(!chunk.hasUpdated()) continue;
					saved ++;
					chunk.saveChunk(1);
					chunk.setUpdated(false);
				}
				if(Controller.debug) System.out.println(saved + " chunks saved!");
			}
		}, 2, 15, TimeUnit.SECONDS);
		//chunkThread = new Thread(this);
		//chunkThread.start();
	}
	
	private void generateWorld(){
		for(int i = -2; i <= 2; i ++){
			Chunk chunk = new Chunk(i);
			chunk.generateRandomChunk();
			chunks.add(chunk);
		}
	}

	public Tile getTile(int x, int y){
		x /= Controller._TILESIZE;
		y /= Controller._TILESIZE;
		x = (int) Math.floor(x--);
		y = (int) Math.floor(y--);
		
		int chunkId = x/16;
		x -= (chunkId * 16);
		x = Math.abs(x);
		
		for(Chunk chunk : chunks){
			if(chunk.getId() == chunkId) {
				return chunk.getBlockAt(x, y).getMaterial().getPlacedTile();
			}
		}
		
		Chunk newChunk = new Chunk(chunkId);
		newChunk.generateRandomChunk();
		chunks.add(newChunk);
		return Tile.BEDROCK;
	}
	
	public void render(int playerX,  Screen screen){
		int xMin = (int) ((playerX - (Game._WIDTH / 2)) - (2.5 * 16));
		int xMax = (int) ((playerX + (Game._WIDTH / 2)) + (2.5 * 16));
		
		for(int y = 0 * Controller._TILESIZE; y < (256 * Controller._TILESIZE); y += Controller._TILESIZE){
			for(int x = xMin; x < xMax; x += Controller._TILESIZE){
				getTile(x,y).render(x, y, screen);
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
	
	public int getChunkId(int x){
		x /= Controller._TILESIZE;
		x = (int) Math.floor(x--);
		
		int chunkId = x/16;
		//System.out.println(chunkId + " " + x + " " + x/16);
		//if(x <= 0) chunkId --;
		return chunkId;
	}
	
	public boolean isStandingOnSolid(int xPos, int yPos){
		if(getTile(xPos, yPos + Controller._TILESIZE).isSolid()) return true;
		return false;
	}
	
	public int getTotalChunks(){
		return chunks.size();
	}
	
}
