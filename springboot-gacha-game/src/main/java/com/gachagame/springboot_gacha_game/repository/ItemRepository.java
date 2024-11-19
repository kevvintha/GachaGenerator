package com.gachagame.springboot_gacha_game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gachagame.springboot_gacha_game.model.ItemModel;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long>{
	
	List<ItemModel> findByRarity(String rarity);
		
	List<ItemModel> findByName(String name);

}
