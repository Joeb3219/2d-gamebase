package com.charredgames.game.game.inventory;

import java.util.ArrayList;

/**
 * @author Joe Boyle <joe@charredgames.com>
 * @since Dec 12, 2013
 */
public class Inventory {

	private int slots;
	public ArrayList<InventoryPack> inventory = new ArrayList<InventoryPack>();
	
	public Inventory(int slots){
		this.slots = slots;
		for(int i = 0; i < slots; i++) inventory.add(new InventoryPack(Material.AIR));
	}
	
}
