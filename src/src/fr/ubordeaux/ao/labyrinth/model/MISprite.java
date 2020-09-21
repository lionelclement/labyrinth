package fr.ubordeaux.ao.labyrinth.model;

public interface MISprite {

	void move(int x, int y);

	int getX();

	int getY();

	void setX(int x);

	void setY(int y);

	int getOldX();

	int getOldY();

}
