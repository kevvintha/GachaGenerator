package com.gachagame.springboot_gacha_game.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.gachagame.springboot_gacha_game.attribute.ItemAttributes.Rarity;
import com.gachagame.springboot_gacha_game.exception.ItemNotFoundException;
import com.gachagame.springboot_gacha_game.model.ItemModel;
import com.gachagame.springboot_gacha_game.repository.ItemRepository;


@Service
public class ItemService {
	
	private ItemRepository itemRepository;
	
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    public ItemModel saveItem(ItemModel item) {
        return itemRepository.save(item);
    }
    
    // This method selects a random item based on the rarity weights.
    public ItemModel getItemByRarity() {
    	// Retrieve all items from the repository
        List<ItemModel> allItems = itemRepository.findAll();
        List<ItemModel> weightedItems = new ArrayList<>();

        // Loop through all items and add them to the weighted list based on their rarity
        for (ItemModel item : allItems) {
        	// Get the item's rarity
            String rarity = item.getRarity();
            // Convert the rarity string to the enum
            Rarity itemRarity = Rarity.valueOf(rarity);
            // Get the weight from the enum
            int weight = itemRarity.getWeight();
            // Add the item to the weighted list according to its weight (chance)
            for (int i = 0; i < weight; i++) {
                weightedItems.add(item);
            }
        }

        // Select a random item from the weighted list
        Random random = new Random();
        int randomIndex = random.nextInt(weightedItems.size());
        return weightedItems.get(randomIndex); 
    }
    
    // Get all items display (postman)
    public List<ItemModel> getItemsByName(String name) {
        return itemRepository.findByName(name);
    }
        
    // Finding item based on Id (postman)
    public ItemModel getItemById(Long id) {
        return itemRepository
        		.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with id " + id));
    }

    // POST: Creating items
    public ItemModel createItem(String name, String rarity, String description) {
    	// Creating a new ItemModel instance
    	ItemModel newItem = new ItemModel();
    	newItem.setName(name);
    	newItem.setRarity(rarity);
    	newItem.setDescription(description);
    	
    	// Save the item using the repository
    	return itemRepository.save(newItem);
    }
    
    // This is to update a item (postman)
    public ItemModel updateItem(Long id, ItemModel updatedItem) {
        ItemModel existingItem = getItemById(id);
        existingItem.setName(updatedItem.getName());
        existingItem.setRarity(updatedItem.getRarity());
        existingItem.setDescription(updatedItem.getDescription());
        return itemRepository.save(existingItem);
    }
    
    // Delete items (postman)
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
  
}

