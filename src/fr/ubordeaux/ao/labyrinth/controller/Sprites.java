package fr.ubordeaux.ao.labyrinth.controller;

import java.util.ArrayList;
import java.util.List;

import fr.ubordeaux.ao.labyrinth.model.MLabyrinth;

public  class Sprites implements ISprites {

	protected List<ISprite> sprites;
	private final SpriteFactory factory;

	protected Sprites(SpriteFactory spriteFactory){
		this.sprites = new ArrayList<ISprite>();
		this.factory = spriteFactory;
	}

	@Override
	public ISprite get(int i) {
		return sprites.get(i);
	}

	@Override
	public int size() {
		return sprites.size();
	}

	@Override
	public void add(String fileName) throws Exception{
		sprites.add(factory.create(fileName));
	}

	@Override
	public void add(int num) throws Exception{
		sprites.add(factory.create(num));
	}

	@Override
	public void add() throws Exception{
		sprites.add(factory.create());
	}
	
	@Override
	public void remove(int i) {
		get(i).remove();
	}

	@Override
	public void move(int i, int x, int y) {
		get(i).move(x,  y);
	}

	@Override
	public void moveManhattan(int i, Labyrinth labyrinth) {
		this.get(i).moveManhattan(labyrinth);
	}

	@Override
	public boolean collides(int i, Sprite sprite) {
		return this.get(i).collides(sprite);
	}

	@Override
	public int getX(int i) {
		return get(i).getX();
	}

	@Override
	public int getY(int i) {
		return get(i).getY();
	}


}
