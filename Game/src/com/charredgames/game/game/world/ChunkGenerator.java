package com.charredgames.game.game.world;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Joe Boyle <joe@charredgames.com>
 * @since Dec 10, 2013
 */
public class ChunkGenerator {

	private int id, startingXPos;
	private Random rand = new Random();
	
	public ChunkGenerator(int id){
		this.id = id;
		this.startingXPos = id * 16;
	}
	
	public int generateRandomTile(int row){
		
		return 1;
	}
	
	public ArrayList<Integer> generateRandomTiles(){
		ArrayList<Integer> tiles = new ArrayList<Integer>();
		
		int maxVal = 0;
		
		for(int y = 0; y < 128; y++){
			if(y <= 30) maxVal = 1;
			else if(y <= 30) maxVal = 1;
			else if(y <= 40) maxVal = 2;
			else if(y <= 60) maxVal = 4;
			else if(y <= 80) maxVal = 10;
			else if(y <= 90) maxVal = 12;
			else if(y <= 100) maxVal = 15;
			else maxVal = 100;
			for(int x = 0; x < 16; x ++){
				tiles.add(rand.nextInt(maxVal));
			}
		}
		
		return tiles;
	}
	
}
