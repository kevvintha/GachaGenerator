package com.gachagame.springboot_gacha_game.attribute;

import java.util.HashMap;
import java.util.Map;

public class RarityConfig {
	
	private static final Map<String, Integer> rarityPercentages = new HashMap<>();
	
	// Percentages for rarity by attributes
    static {
        rarityPercentages.put("COMMON", 70);
        rarityPercentages.put("RARE", 20);
        rarityPercentages.put("EPIC", 9);
        rarityPercentages.put("LEGENDARY", 1);
    }

    public static int getPercentageForRarity(String rarity) {
        return rarityPercentages.getOrDefault(rarity, 0); // Default to 0 if rarity not found
    }
}
