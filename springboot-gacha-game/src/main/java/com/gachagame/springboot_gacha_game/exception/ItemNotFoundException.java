package com.gachagame.springboot_gacha_game.exception;

public class ItemNotFoundException extends RuntimeException{

	public ItemNotFoundException(String message) {
        super(message);
    }
}
