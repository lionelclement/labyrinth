package fr.ubordeaux.ao.labyrinth.controller;

//import fr.ubordeaux.ao.labyrinth.model.MLabyrinth;
import javafx.scene.Node;

public interface ISprite {
	
	void moveEast(Labyrinth labyrinth);
	void moveWest(Labyrinth labyrinth);
	void moveNorth(Labyrinth labyrinth);
	void moveSouth(Labyrinth labyrinth);
	void move(int x, int y);
	void moveManhattan(Labyrinth labyrinth);
	void remove();
	void initXY(int x, int y);
	boolean collides(Sprite sprite);
	int getX();
	int getY();
	Node getImageView();

}
