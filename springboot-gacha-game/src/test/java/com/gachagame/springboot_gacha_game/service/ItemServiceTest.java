package com.gachagame.springboot_gacha_game.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gachagame.springboot_gacha_game.model.ItemModel;
import com.gachagame.springboot_gacha_game.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
	
    @InjectMocks
    private ItemService itemService;
	
	@Mock
    private ItemRepository itemRepository;

	@Test
	void testGetItemByRarity() {
	    // Mock items with different rarities
	    ItemModel commonItem = new ItemModel("Test Sword", "COMMON", "A basic sword");
	    ItemModel rareItem = new ItemModel("Diamond Sword", "RARE", "A sharp and shiny sword");
	    ItemModel epicItem = new ItemModel("Excalibur", "EPIC", "A legendary sword");

	    when(itemRepository.findAll()).thenReturn(List.of(commonItem, rareItem, epicItem));

	    ItemModel selectedItem = itemService.getItemByRarity();

	    assertNotNull(selectedItem, "The selected item should not be null.");
	    List<String> validRarities = List.of("COMMON", "RARE", "EPIC");
	    assertTrue(validRarities.contains(selectedItem.getRarity()), "The rarity should match one of the expected values.");
	    
	    verify(itemRepository, times(1)).findAll();

	}

	@Test
	void testGetItemByName() {
        String itemName = "Test Item";
        ItemModel mockItem = new ItemModel(itemName, "Common", "Description");
        when(itemRepository.findByName(eq(itemName))).thenReturn(Collections.singletonList(mockItem));

        List<ItemModel> result = itemService.getItemByName(itemName);

        assertNotNull(result); 
        assertFalse(result.isEmpty(), "The result should not be empty but is."); 
        assertEquals(1, result.size()); 
        assertEquals(itemName, result.get(0).getName()); 
        verify(itemRepository).findByName(itemName); 
	}

	@Test
	void testGetItemById() {
		Long itemId = 1L;
	    
		ItemModel savedItem = new ItemModel();
	    savedItem.setId(itemId); 
	    savedItem.setName("Legendary Sword");
	    savedItem.setRarity("LEGENDARY");
	    savedItem.setDescription("A mythical sword of immense power");
        
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(savedItem));

        ItemModel result = itemService.getItemById(itemId);

        assertNotNull(result, "The result should not be null.");
        assertEquals(itemId, result.getId(), "The ID should match the input.");
        assertEquals("Legendary Sword", result.getName(), "The name should match.");
        assertEquals("LEGENDARY", result.getRarity(), "The rarity should match.");
        assertEquals("A mythical sword of immense power", result.getDescription(), "The description should match.");
        
        verify(itemRepository, times(1)).findById(itemId);

	}

	@Test
	void testCreateItem() {
	    String name = "Legendary Sword";
	    String rarity = "LEGENDARY";
	    String description = "A mythical sword of immense power";

	    // Mocking the saved item
	    ItemModel savedItem = new ItemModel();
	    savedItem.setId(1L); // Assuming an ID is assigned upon saving
	    savedItem.setName(name);
	    savedItem.setRarity(rarity);
	    savedItem.setDescription(description);
        
        when(itemRepository.save(any(ItemModel.class))).thenReturn(savedItem);
        
        ItemModel result = itemService.createItem(name, rarity, description);

        assertNotNull(result); 
        assertEquals(1L, result.getId(), "The ID should be 1.");
        assertEquals(name, result.getName(), "The name should match the input.");
        assertEquals(rarity, result.getRarity(), "The rarity should match the input.");
        assertEquals(description, result.getDescription(), "The description should match the input.");
        
        // Verify the save method was called with the correct item
        ArgumentCaptor<ItemModel> captor = ArgumentCaptor.forClass(ItemModel.class);
        verify(itemRepository, times(1)).save(captor.capture());

        ItemModel capturedItem = captor.getValue();
        assertEquals(name, capturedItem.getName(), "Captured name should match.");
        assertEquals(rarity, capturedItem.getRarity(), "Captured rarity should match.");
        assertEquals(description, capturedItem.getDescription(), "Captured description should match.");
    }
	
	@Test
	void testUpdateItem() {
        Long itemId = 1L;
        ItemModel existingItem = new ItemModel();
        existingItem.setId(itemId);
        existingItem.setName("Old Name");
        existingItem.setRarity("COMMON");
        existingItem.setDescription("Old Description");

        ItemModel updatedItem = new ItemModel();
        updatedItem.setName("New Name");
        updatedItem.setRarity("RARE");
        updatedItem.setDescription("New Description");

        when(itemRepository.findById(itemId)).thenReturn(Optional.of(existingItem));
        when(itemRepository.save(any(ItemModel.class))).thenReturn(updatedItem);

        ItemModel result = itemService.updateItem(itemId, updatedItem);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        assertEquals("RARE", result.getRarity());
        assertEquals("New Description", result.getDescription());

        verify(itemRepository, times(1)).findById(itemId);
        verify(itemRepository, times(1)).save(existingItem);
	}

	@Test
	void testDeleteItem() {
        Long itemId = 1L;

        itemService.deleteItem(itemId);

        verify(itemRepository, times(1)).deleteById(itemId);	}

}
