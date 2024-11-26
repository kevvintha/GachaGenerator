package com.gachagame.springboot_gacha_game.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gachagame.springboot_gacha_game.model.ItemModel;
import com.gachagame.springboot_gacha_game.model.ItemRequest;
import com.gachagame.springboot_gacha_game.service.ItemService;

@RunWith(MockitoJUnitRunner.class) // or use @ExtendWith(MockitoExtension.class) if using JUnit 5
class ItemControllerTest {
	
	@Mock
    private ItemService itemService; // Mock the service layer

    @InjectMocks
    private ItemController itemController; // Controller to test

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        itemController = new ItemController(itemService); 
  
    }
    
	@Test
	void testItemController() {
        // Check that the controller is instantiated with the mock service
		assertNotNull(itemController);
	}

	@Test
	void testGetItemsByRarity() {
		ItemModel mockItem = new ItemModel("Test Stick", "Common", "Test Description");
		when(itemService.getItemByRarity()).thenReturn(mockItem);
        
		// Call the controller method
		ResponseEntity<ItemModel> response = itemController.getItemsByRarity();
        
		assertEquals(HttpStatus.FOUND, response.getStatusCode()); // Verify HTTP status
		assertEquals(mockItem, response.getBody());               // Verify the body
		
        // Verify interaction with the service
		verify(itemService, times(1)).getItemByRarity();

	}

	@Test
	void testGetItemById() {
        Long itemId = 1L;
        ItemModel mockItem = new ItemModel(); // Example ItemModel
        
        mockItem.setId(itemId);
        mockItem.setName("Sword");
        mockItem.setRarity("COMMON");
        mockItem.setDescription("A common sword");
        when(itemService.getItemById(itemId)).thenReturn(mockItem); // Mock the service call

        ItemModel actualItem = itemController.getItemById(itemId); 

        assertNotNull(actualItem); 
        assertEquals(mockItem.getId(), actualItem.getId()); // Ensure the ID matches
        assertEquals(mockItem.getName(), actualItem.getName()); // Ensure the name matches
        assertEquals(mockItem.getRarity(), actualItem.getRarity()); // Ensure the rarity matches
        verify(itemService).getItemById(itemId); 
	}

	@Test
	void testGetItemByName() {
		ItemModel mockItem = new ItemModel("Test Stick", "Common", "Test Description");
		String itemName = mockItem.getName();
		
	    // Mock the behavior of the service to return a list containing the mock item
		when(itemService.getItemByName(itemName)).thenReturn(Collections.singletonList(mockItem)); // Return a list with one item
		
		List<ItemModel> actualItem = itemService.getItemByName(itemName);

	    assertEquals(1, actualItem.size());           // Assert that the list has one item
	    assertEquals(mockItem, actualItem.get(0));    // Assert that the first item is the mock item
	    verify(itemService).getItemByName(itemName);  // Verify that the service method was called
	    
    }

	@Test
	void testCreateItem() throws Exception {
		ItemRequest itemRequest = new ItemRequest("Test Sword", "COMMON", "A basic sword");
        ItemModel mockItem = new ItemModel("Test Sword", "COMMON", "A basic sword");
        
        when(itemService.saveItem(any(ItemModel.class))).thenReturn(mockItem);

        // Call the controller method directly
        ResponseEntity<ItemModel> response = itemController.createItem(itemRequest);
        		
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Sword", response.getBody().getName());
        assertEquals("COMMON", response.getBody().getRarity());
        assertEquals("A basic sword", response.getBody().getDescription());

        // Verify that saveItem() was called with the correct argument
        verify(itemService, times(1)).saveItem(any(ItemModel.class));
	}

}
