package com.charredgames.game.game.inventory;

import com.charredgames.game.game.Controller;
import com.charredgames.game.game.graphics.Tile;

/**
 * @author Joe Boyle <joe@charredgames.com>
 * @since Dec 12, 2013
 */
public enum Material {

	AIR(0, Tile.AIR, MaterialType.OTHER, 0, 99),
	DIRT(1, Tile.DIRT, Tile.DIRT, MaterialType.BLOCK, 10, 99),
	STONE(2, Tile.STONE, Tile.STONE, MaterialType.BLOCK, 10, 99),
	COAL(10, Tile.COAL, Tile.COAL, MaterialType.BLOCK, 10, 99),
	IRON(11, Tile.IRON, Tile.IRON, MaterialType.BLOCK, 10, 99),
	URANIUM(12, Tile.URANIUM, Tile.URANIUM, MaterialType.BLOCK, 10, 99),
	PLUTONIUM(13, Tile.PLUTONIUM, Tile.PLUTONIUM, MaterialType.BLOCK, 10, 99),
	IRIDUM(14, Tile.IRIDUM, Tile.IRIDUM, MaterialType.BLOCK, 10, 99),
	BEDROCK(10, Tile.BEDROCK, Tile.BEDROCK, MaterialType.BLOCK, 100000, 99);
	
	
	private int id = 1, damage = 10, value = 0, maxStack = 99;
	private Tile placedTile = null, invTile = null;
	private Material drops;
	private MaterialType type = MaterialType.OTHER;
	
	private Material(int id, Tile invTile, MaterialType type, int damage, int maxStack){ //Used for items like air or tools
		this.id = id;
		this.invTile = invTile;
		this.type = type;
		this.damage = damage;
		this.maxStack = maxStack;
		Controller.addMaterial(id, this);
	}
	
	private Material(int id, Tile placedTile, Tile invTile, MaterialType type, int damage, int maxStack){ //Used for blocks that give what they are (dirt)
		this.id = id;
		this.placedTile = placedTile;
		this.invTile = invTile;
		this.type = type;
		this.damage = damage;
		this.drops = this;
		this.maxStack = maxStack;
		Controller.addMaterial(id, this);
	}
	
	private Material(int id, Tile placedTile, Material drops, int damage, int maxStack){ //Used for things like coal
		this.id = id;
		this.placedTile = placedTile;
		this.invTile = placedTile;
		this.type = MaterialType.BLOCK;
		this.drops = drops;
		this.damage = damage;
		this.maxStack = maxStack;
		Controller.addMaterial(id, this);
	}
	
	public int getId(){
		return id;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public int getDataValue(){
		return value;
	}
	
	public int getMaxStack(){
		return maxStack;
	}
	
	public Tile getPlacedTile(){
		if(placedTile == null) return Tile.AIR;
		return placedTile;
	}
	
	public Tile getInventoryTile(){
		if(invTile == null) return Tile.AIR;
		return invTile;
	}
	
	public Material getDrops(){
		return drops;
	}
	
	public MaterialType getType(){
		return type;
	}
	
}
