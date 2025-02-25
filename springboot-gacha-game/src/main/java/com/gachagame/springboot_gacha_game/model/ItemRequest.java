package com.gachagame.springboot_gacha_game.model;

public class ItemRequest {
	
	private String name;
    private String rarity;
    private String description;
    
    // Constructor to initialize fields
    public ItemRequest(String name, String rarity, String description) {
        this.name = name;
        this.rarity = rarity;
        this.description = description;
    }

	// Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
