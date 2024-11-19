package com.gachagame.springboot_gacha_game.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gachagame.springboot_gacha_game.model.ItemModel;
import com.gachagame.springboot_gacha_game.model.ItemRequest;
import com.gachagame.springboot_gacha_game.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {
	
	private final ItemService service;
	
	public ItemController(ItemService itemService) {
        this.service = itemService;
    }
	
	// Generate random item based off rarity *WORK*
    @GetMapping("/random")
    public ResponseEntity<ItemModel> getItemsByRarity() {
    	ItemModel items = service.getItemByRarity();
        return new ResponseEntity<>(items, HttpStatus.FOUND);
    }
   
    // Postman test: Finding the id of the item *WORK* xD
    @GetMapping("/id/{id}")
    public ItemModel getItemById(@RequestParam Long id) {
    	return service.getItemById(id);
    }
    
    // Postman test: Finding the name of the item *WORK*
    @GetMapping("/name/{name}")
    public ResponseEntity<List<ItemModel>> getItemsByName(@PathVariable String name) {
        List<ItemModel> items = service.getItemsByName(name);
        return new ResponseEntity<>(items, HttpStatus.FOUND);
    }
    
    // Postman test: Creating a item *NOT SURE IF WORKS*
    @PostMapping("/create") 
    public ResponseEntity<ItemModel> createItem(@RequestBody ItemRequest itemRequest ) {   	
    	// Convert DTO to Entity
    	ItemModel item = new ItemModel();
    	item.setName(itemRequest.getName());
    	item.setRarity(itemRequest.getRarity());
    	item.setDescription(itemRequest.getDescription());
    	
    	// Save entity to the database
    	ItemModel createdItem = service.saveItem(item);
    	
    	return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }
	
}
