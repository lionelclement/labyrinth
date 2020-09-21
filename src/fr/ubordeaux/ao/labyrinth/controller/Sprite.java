package fr.ubordeaux.ao.labyrinth.controller;

import fr.ubordeaux.ao.labyrinth.model.MEdge;
import fr.ubordeaux.ao.labyrinth.model.MGraph;
import fr.ubordeaux.ao.labyrinth.model.MISprite;
import fr.ubordeaux.ao.labyrinth.model.MLabyrinth;
import fr.ubordeaux.ao.labyrinth.model.MLabyrinth.Directions;
import fr.ubordeaux.ao.labyrinth.model.MVertex;
import fr.ubordeaux.ao.labyrinth.view.VISprite;
import javafx.scene.Node;

public  class Sprite implements ISprite {

	protected VISprite view;
	protected MISprite model;

	protected MVertex getVertex(MGraph path){
		return path.getVertexByXY(model.getX(), model.getY());
	}

	@Override
	public void moveEast(Labyrinth labyrinth, boolean closeAfter) {
		MVertex vertex = getVertex(labyrinth.getGraph());
		if (labyrinth.isOpened(vertex, MLabyrinth.Directions.EAST)){
			if (closeAfter && labyrinth.isOpenedDoor(vertex, MLabyrinth.Directions.EAST)) {
				labyrinth.closeDoor(labyrinth.getEdge(vertex, MLabyrinth.Directions.EAST));
			}
			model.setX(model.getX()+1);
			view.updateUI(model);
		}
	}

	@Override
	public void moveWest(Labyrinth labyrinth, boolean closeAfter) {
		MVertex vertex = getVertex(labyrinth.getGraph());
		if (labyrinth.isOpened(vertex, MLabyrinth.Directions.WEST)){
			if (closeAfter && labyrinth.isOpenedDoor(vertex, MLabyrinth.Directions.WEST)) {
				labyrinth.closeDoor(labyrinth.getEdge(vertex, MLabyrinth.Directions.WEST));
			}
			model.setX(model.getX()-1);
			view.updateUI(model);
		}
	}

	@Override
	public void moveNorth(Labyrinth labyrinth, boolean closeAfter) {
		MVertex vertex = getVertex(labyrinth.getGraph());
		if (labyrinth.isOpened(vertex, MLabyrinth.Directions.NORTH)){
			if (closeAfter && labyrinth.isOpenedDoor(vertex, MLabyrinth.Directions.NORTH)) {
				labyrinth.closeDoor(labyrinth.getEdge(vertex, MLabyrinth.Directions.NORTH));
			}
			model.setY(model.getY()-1);
			view.updateUI(model);
		}
	}

	@Override
	public void moveSouth(Labyrinth labyrinth, boolean closeAfter) {
		MVertex vertex = getVertex(labyrinth.getGraph());
		if (labyrinth.isOpened(vertex, MLabyrinth.Directions.SOUTH)){
			if (closeAfter && labyrinth.isOpenedDoor(vertex, MLabyrinth.Directions.SOUTH)) {
				labyrinth.closeDoor(labyrinth.getEdge(vertex, MLabyrinth.Directions.SOUTH));
			}
			model.setY(model.getY()+1);
			view.updateUI(model);
		}
	}

	public void move(int x, int y) {
		model.move(x, y);
		view.updateUI(model);
	}

	public void initXY(int x, int y) {
		model.move(x, y);
		view.initUI(model);
	}

	public void moveManhattan(Labyrinth labyrinth) {
		MVertex vertex = getVertex(labyrinth.getGraph());
		for (Directions dir : Directions.values()) {
			MVertex next = labyrinth.getGraph().getVertexByDir(vertex, dir);
			if (labyrinth.getGraph().isConnected(vertex, next) && (next.getNbr()==vertex.getNbr()-1)){
				move(labyrinth, dir);
				return;
			}
		}
	}

	private void move(Labyrinth labyrinth, Directions dir) {
		switch (dir){
		case NORTH: moveNorth(labyrinth, false); break;
		case SOUTH: moveSouth(labyrinth, false); break;
		case EAST: moveEast(labyrinth, false); break;
		case WEST: moveWest(labyrinth, false); break;
		}
	}

	public boolean collides(Sprite sprite){
		return ((sprite.model.getX() == this.model.getX()) && 
				(sprite.model.getY() == this.model.getY()));
	}

	public int getX() {
		return this.model.getX();
	}

	public int getY() {
		return this.model.getY();
	}

	@Override
	public void remove() {
		this.move(-1, -1);
	}

	public Node getImageView() {
		return this.view.getImageView();
	}


}
