package com.charredgames.game.game.world;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.charredgames.game.game.Controller;
import com.charredgames.game.game.graphics.Tile;


/**
 * @author Joe Boyle <joe@charredgames.com>
 * @since Dec 9, 2013
 */
public class Chunk implements Runnable{

	private int id;
	private int[] tiles;
	private Map<Integer, ArrayList<Integer>> tileMap = new LinkedHashMap<Integer, ArrayList<Integer>>();
	private Random rand = new Random();
	private Thread chunkThread;
	private boolean updated = false;
	private ChunkGenerator generator;
	
	public Chunk(int id){
		this.id = id;
		generator = new ChunkGenerator(id);
		tiles = new int[16 * 128];
		for(int i = 0; i < tiles.length; i ++) tiles[i] = 0;
		chunkThread = new Thread(this, "chunk" + id);
		chunkThread.start();
	}
	
	public void generateRandomChunk(){
		ArrayList<Integer> rowTiles = new ArrayList<Integer>();
		int row = 0, col = 1;
		ArrayList<Integer> tiles = generator.generateRandomTiles();
		for(int i : tiles){
			rowTiles.add(i);
			if(col == 16){
				tileMap.put(row, rowTiles);
				col = 0;
				row++;
				rowTiles = new ArrayList<Integer>();
			}
			col++;
		}
		
		updated = true;
	}
	
	private int getHighestYPos(int xPos){
		for(int row = 0; row < tileMap.size(); row++){
			for(int i : tileMap.get(row)){
				if(!Controller.tileIdentifiers.get(i).isSolid()) return row;
			}
		}
		return 1;
	}
	
	private void saveChunk(){
		PrintWriter writer;
		try {
			writer = new PrintWriter("chunk" + id + ".cgf", "UTF-8");
			for(Entry<Integer, ArrayList<Integer>> entry : tileMap.entrySet()){
				String output = "";
				int pos = 0;
				for(int i : entry.getValue()){
					pos++;
					if(pos<16) output = output +  i + ",";
					else output = output +  i;
				}
				writer.println(output);
			}
			writer.close();
		} catch (FileNotFoundException e) {e.printStackTrace();} catch (UnsupportedEncodingException e) {e.printStackTrace();} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			chunkThread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(!updated) return;
		saveChunk();
		updated = false;
	}

	public int getId(){
		return id;
	}
	
	public Tile getTile(int x, int y){
		//if(y >= tileMap.size() || 16 >= x) return Tile.AIR;
		int tileId = tileMap.get(y).get(x);
		for(Entry<Integer, Tile> entry : Controller.tileIdentifiers.entrySet()){
			if(entry.getKey() == tileId) return entry.getValue();
		}
		return Tile.AIR;
	}
}
