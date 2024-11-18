package com.gachagame.springboot_gacha_game.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "items")  // Explicitly map to "items" table
public class ItemModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String rarity;
	
	@Column(nullable = false)
	private String description;
    
	// No-argument constructor (required by JPA)
	 public ItemModel() {
	    }
	 
	// Constructors
	public ItemModel(String name, String rarity, String description) {
		this.name = name;
        this.rarity = rarity;
        this.description = description;
	}
	
	// Getter and Setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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

	@Override
	public String toString() {
		return "Id = " + id + "Name = " + name + "\nRarity = " + rarity + "\nDescription = " + description;
	}
	
}
