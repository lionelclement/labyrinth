package fr.ubordeaux.ao.labyrinth.controller;

public interface ISpriteFactory {
	
	ISprite create(String fileName) throws Exception;
	ISprite create() throws Exception;
	ISprite create(int num) throws Exception;

}
