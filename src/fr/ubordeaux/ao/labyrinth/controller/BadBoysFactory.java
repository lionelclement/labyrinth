package fr.ubordeaux.ao.labyrinth.controller;

public class BadBoysFactory implements ISpriteFactory  {
	
	public BadBoysFactory() {
		super();
	}

	@Override
	public ISprite create() {
		return new BadBoy();
	}

	@Override
	public ISprite create(String fileName) throws Exception {
		throw new Exception("Internal error");
	}

	@Override
	public ISprite create(int num) throws Exception {
		throw new Exception("Internal error");
	}

}
