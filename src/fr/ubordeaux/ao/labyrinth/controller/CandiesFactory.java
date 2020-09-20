package fr.ubordeaux.ao.labyrinth.controller;

public class CandiesFactory extends SpriteFactory {

	public CandiesFactory() {
		super();
	}

	@Override
	public ISprite create(int num) throws Exception {
		return new Candy(num);
	}

	@Override
	public ISprite create(String fileName) throws Exception {
		throw new Exception("Internal error");
	}

	@Override
	public ISprite create() throws Exception {
		throw new Exception("Internal error");
	}

}
