package com.charredgames.game.game.block;

import com.charredgames.game.game.inventory.Material;
import com.charredgames.game.game.inventory.MaterialType;

/**
 * @author Joe Boyle <joe@charredgames.com>
 * @since Dec 12, 2013
 */
public class Block {

	private Material material;
	private int damage;
	
	public static final Block AIR = new Block(Material.AIR);
	
	public Block(Material material){
		this.material = material;
	}
	
	public Material getMaterial(){
		if(material == null) return Material.AIR;
		return material;
	}
	
	public boolean isSolid(){
		if(material.getType() == MaterialType.BLOCK) return true;
		return false;
	}
	
}
