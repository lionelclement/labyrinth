package fr.ubordeaux.ao.labyrinth.controller;

public interface ISpriteFactory<T extends Sprite> {
	
	ISprite create(String fileName) throws Exception;
	ISprite create() throws Exception;

}
