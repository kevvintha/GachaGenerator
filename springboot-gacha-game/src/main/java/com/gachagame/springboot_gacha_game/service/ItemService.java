package com.gachagame.springboot_gacha_game.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.gachagame.springboot_gacha_game.exception.ItemNotFoundException;
import com.gachagame.springboot_gacha_game.model.ItemModel;
import com.gachagame.springboot_gacha_game.repository.ItemRepository;


@Service
public class ItemService {
	
	private final ItemRepository itemRepository;
	
    private final Random random = new Random();

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    public ItemModel saveItem(ItemModel item) {
        return itemRepository.save(item);
    }
    
    // Generate random item based off rarity
    public ItemModel getItemsByRarity(String rarity) {
    	List<ItemModel> findByRarity = itemRepository.findByRarity(rarity);
    	
    	if(findByRarity.isEmpty()) {
    		throw new ItemNotFoundException("No items found with rarity: " + rarity);
    	}
    	
    	// Select a random item from the filtered list
        int randomIndex = random.nextInt(findByRarity.size());
        return findByRarity.get(randomIndex);
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

