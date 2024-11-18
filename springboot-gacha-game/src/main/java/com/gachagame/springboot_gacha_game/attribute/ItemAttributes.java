package com.gachagame.springboot_gacha_game.attribute;

public class ItemAttributes {
	
	public enum Rarity {
        COMMON,
        RARE,
        EPIC,
        LEGENDARY
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
