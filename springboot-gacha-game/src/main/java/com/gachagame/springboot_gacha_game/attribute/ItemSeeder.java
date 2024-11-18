package com.gachagame.springboot_gacha_game.attribute;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.gachagame.springboot_gacha_game.model.ItemModel;
import com.gachagame.springboot_gacha_game.repository.ItemRepository;

@Component
public class ItemSeeder implements CommandLineRunner{
	
	private final ItemRepository itemRepository;
	
	public ItemSeeder(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	@Override
    public void run(String... args) throws Exception {
		if(itemRepository.count() == 0) { // prevent duplicate data
            // Add some dummy items
            itemRepository.save(new ItemModel("Common Sword", "COMMON", ""));
            itemRepository.save(new ItemModel("Rare Sword", "RARE", ""));
            itemRepository.save(new ItemModel("Epic Sword", "EPIC", ""));
            itemRepository.save(new ItemModel("Legendary Sword", "LEGENDARY", ""));
		}
    }
}
