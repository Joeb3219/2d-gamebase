package com.charredgames.game.game.world;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.charredgames.game.game.Controller;
import com.charredgames.game.game.graphics.Tile;


/**
 * @author Joe Boyle <joe@charredgames.com>
 * @since Dec 9, 2013
 */
public class Chunk{

	private int id;
	private int[] tiles;
	private Map<Integer, ArrayList<Integer>> tileMap = new LinkedHashMap<Integer, ArrayList<Integer>>();
	private boolean updated = false;
	private ChunkGenerator generator;
	private int chunkHeight = 256; //Was 128
	
	public Chunk(int id){
		this.id = id;
		generator = new ChunkGenerator(id, chunkHeight);
		tiles = new int[16 * chunkHeight];
		for(int i = 0; i < tiles.length; i ++) tiles[i] = 0;
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
	
	public void saveChunk(int worldId){
		PrintWriter writer;
		try {
			writer = new PrintWriter("saves/" + worldId + "/chunk" + id + ".cgf", "UTF-8");
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

	public int getId(){
		return id;
	}
	
	public void setUpdated(boolean val){
		updated = val;
	}
	
	public boolean hasUpdated(){
		return updated;
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
