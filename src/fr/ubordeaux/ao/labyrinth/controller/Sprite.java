package fr.ubordeaux.ao.labyrinth.controller;

import fr.ubordeaux.ao.labyrinth.model.MGraph;
import fr.ubordeaux.ao.labyrinth.model.MISprite;
import fr.ubordeaux.ao.labyrinth.model.MLabyrinth;
import fr.ubordeaux.ao.labyrinth.model.MLabyrinth.Directions;
import fr.ubordeaux.ao.labyrinth.model.MVertex;
import fr.ubordeaux.ao.labyrinth.view.VISprite;
import javafx.scene.Node;

public abstract class Sprite implements ISprite {

	protected abstract VISprite getView();

	protected abstract MISprite getModel();

	public MVertex getVertex(MGraph path) {
		return path.getVertexByXY(getX(), getY());
	}

	private void updateUI() {
		getView().updateUI(getModel());
	}

	private void setX(int x) {
		getModel().setX(x);
	}

	private void setY(int y) {
		getModel().setY(y);
	}

	@Override
	public void moveEast(Labyrinth labyrinth, boolean closeAfter) {
		MVertex vertex = getVertex(labyrinth.getGraph());
		if (labyrinth.isOpened(vertex, MLabyrinth.Directions.EAST)) {
			if (closeAfter && labyrinth.isOpenedDoor(vertex, MLabyrinth.Directions.EAST)) {
				labyrinth.closeDoor(labyrinth.getEdge(vertex, MLabyrinth.Directions.EAST));
			}
			setX(getX() + 1);
			updateUI();
		}
	}

	@Override
	public void moveWest(Labyrinth labyrinth, boolean closeAfter) {
		MVertex vertex = getVertex(labyrinth.getGraph());
		if (labyrinth.isOpened(vertex, MLabyrinth.Directions.WEST)) {
			if (closeAfter && labyrinth.isOpenedDoor(vertex, MLabyrinth.Directions.WEST)) {
				labyrinth.closeDoor(labyrinth.getEdge(vertex, MLabyrinth.Directions.WEST));
			}
			setX(getX() - 1);
			updateUI();
		}
	}

	@Override
	public void moveNorth(Labyrinth labyrinth, boolean closeAfter) {
		MVertex vertex = getVertex(labyrinth.getGraph());
		if (labyrinth.isOpened(vertex, MLabyrinth.Directions.NORTH)) {
			if (closeAfter && labyrinth.isOpenedDoor(vertex, MLabyrinth.Directions.NORTH)) {
				labyrinth.closeDoor(labyrinth.getEdge(vertex, MLabyrinth.Directions.NORTH));
			}
			setY(getY() - 1);
			updateUI();
		}
	}

	@Override
	public void moveSouth(Labyrinth labyrinth, boolean closeAfter) {
		MVertex vertex = getVertex(labyrinth.getGraph());
		if (labyrinth.isOpened(vertex, MLabyrinth.Directions.SOUTH)) {
			if (closeAfter && labyrinth.isOpenedDoor(vertex, MLabyrinth.Directions.SOUTH)) {
				labyrinth.closeDoor(labyrinth.getEdge(vertex, MLabyrinth.Directions.SOUTH));
			}
			setY(getY() + 1);
			updateUI();
		}
	}

	public void move(int x, int y) {
		getModel().move(x, y);
		updateUI();
	}

	public void initXY(int x, int y) {
		getModel().move(x, y);
		initUI();
	}

	private void initUI() {
		getView().initUI(getModel());
	}

	public void moveManhattan(Labyrinth labyrinth) {
		MVertex vertex = getVertex(labyrinth.getGraph());
		for (Directions dir : Directions.values()) {
			MVertex next = labyrinth.getGraph().getVertexByDir(vertex, dir);
			if (labyrinth.getGraph().isConnected(vertex, next) && (next.getNbr() == vertex.getNbr() - 1)) {
				move(labyrinth, dir);
				return;
			}
		}
	}

	private void move(Labyrinth labyrinth, Directions dir) {
		switch (dir) {
		case NORTH:
			moveNorth(labyrinth, false);
			break;
		case SOUTH:
			moveSouth(labyrinth, false);
			break;
		case EAST:
			moveEast(labyrinth, false);
			break;
		case WEST:
			moveWest(labyrinth, false);
			break;
		}
	}

	public boolean collides(Sprite sprite) {
		return ((sprite.getX() == getX()) && (sprite.getY() == getY()));
	}

	public int getX() {
		return getModel().getX();
	}

	public int getY() {
		return getModel().getY();
	}

	@Override
	public void remove() {
		move(-1, -1);
	}

	public Node getImageView() {
		return getView().getImageView();
	}

}
