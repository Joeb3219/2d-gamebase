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
		
		
		for(int y = 0; y < 128; y++){
			for(int x = 0; x < 16; x ++){
				int value = rand.nextInt(100);
				if(y <= 30) tiles.add(0);
				else if(y <= 55){
					if(value <= 40) tiles.add(0);
					else tiles.add(1);
				}
				else if(y <= 48){
					if(value <= 20) tiles.add(0);
					else if(value <= 40) tiles.add(1);
					else tiles.add(2);
				}
				else if(y <= 80){
					if(value <= 5) tiles.add(0);
					else if(value <= 70) tiles.add(2);
					else if(value <= 80) tiles.add(10);
					else if(value <= 90) tiles.add(11);
					else tiles.add(12);
				}
				else if(y <= 120){
					if(value <= 5) tiles.add(0);
					else if(value <= 70) tiles.add(2);
					else if(value <= 80) tiles.add(10);
					else if(value <= 90) tiles.add(11);
					else if(value <= 95) tiles.add(12);
					else tiles.add(13);
				}
				else if(y == 127 || y == 128) tiles.add(100);
				else{
					if(value <= 5) tiles.add(0);
					else if(value <= 70) tiles.add(2);
					else if(value <= 80) tiles.add(10);
					else if(value <= 90) tiles.add(11);
					else if(value <= 95) tiles.add(12);
					else if(value <= 98) tiles.add(13);
					else tiles.add(14);
				}
			}
		}
		
		return tiles;
	}
	
}
