package com.charredgames.game.game.inventory;

/**
 * @author Joe Boyle <joe@charredgames.com>
 * @since Dec 12, 2013
 */
public class InventoryPack {

	private Material material;
	private int quantity;
	
	public InventoryPack(Material material, int quantity){
		this.material = material;
		this.quantity = quantity;
	}
	
	public InventoryPack(Material material){
		this.material = material;
		this.quantity = material.getMaxStack();
	}
	
	
	
	public int getQuantity(){
		return quantity;
	}
	
	public Material getMaterial(){
		return material;
	}
	
}
