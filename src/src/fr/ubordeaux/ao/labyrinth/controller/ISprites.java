package fr.ubordeaux.ao.labyrinth.controller;

public interface ISprites {
	
	int size();
	void add() throws Exception;
	void add(String fileName) throws Exception;
	void add(int num) throws Exception;
	void move(int i, int x, int y);
	boolean collides(int i, Sprite sprite);
	int getX(int i);
	int getY(int i);
	void remove(int i);
	ISprite get(int i);
	void moveManhattan(int i, Labyrinth labyrinth);

}
