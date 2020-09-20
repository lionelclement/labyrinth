package fr.ubordeaux.ao.labyrinth.controller;

public abstract class SpriteFactory implements ISpriteFactory<Sprite> {

	public abstract ISprite create(String fileName) throws Exception;
	public abstract ISprite create() throws Exception;
	public abstract ISprite create(int num) throws Exception;

}
