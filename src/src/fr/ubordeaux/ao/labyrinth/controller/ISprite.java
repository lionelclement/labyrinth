package fr.ubordeaux.ao.labyrinth.controller;

import javafx.scene.Node;

public interface ISprite {
	
	void moveEast(Labyrinth labyrinth, boolean closeAfter);
	void moveWest(Labyrinth labyrinth, boolean closeAfter);
	void moveNorth(Labyrinth labyrinth, boolean closeAfter);
	void moveSouth(Labyrinth labyrinth, boolean closeAfter);
	void move(int x, int y);
	void moveManhattan(Labyrinth labyrinth);
	void remove();
	void initXY(int x, int y);
	boolean collides(Sprite sprite);
	int getX();
	int getY();
	Node getImageView();

}
