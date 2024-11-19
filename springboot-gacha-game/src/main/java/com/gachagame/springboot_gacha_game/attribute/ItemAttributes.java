package com.gachagame.springboot_gacha_game.attribute;

public class ItemAttributes {
	
	public enum Rarity {
		// Percentages for rarity by attributes
		COMMON(70),      // 70% chance for COMMON items
	    RARE(20),        // 20% chance for RARE items
	    EPIC(9),         // 9% chance for EPIC items
	    LEGENDARY(1);    // 1% chance for LEGENDARY items
	    
	    private final int weight;

	    // Constructor to initialize the weight for each rarity
	    Rarity(int weight) {
	        this.weight = weight;
	    }

	    // Getter for weight 
	    public int getWeight() {
	        return weight;
	    }
    }
	
	public enum ItemType {
        WEAPON,
        ARMOR,
        ACCESSORY
    }

    public enum Status {
        AVAILABLE,
        UNAVAILABLE,
        DISCONTINUED
    }
	
}
