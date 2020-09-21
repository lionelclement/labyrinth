package fr.ubordeaux.ao.labyrinth.controller;

import java.io.FileNotFoundException;
import java.util.Set;

import fr.ubordeaux.ao.labyrinth.model.MEdge;
import fr.ubordeaux.ao.labyrinth.model.MGraph;
import fr.ubordeaux.ao.labyrinth.model.MLabyrinth;
import fr.ubordeaux.ao.labyrinth.model.MLabyrinth.Directions;
import fr.ubordeaux.ao.labyrinth.model.MVertex;
import fr.ubordeaux.ao.labyrinth.view.VLabyrinth;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Labyrinth {
	
	private MLabyrinth mLabyrinth;
	private VLabyrinth vLabyrinth;
	
	public Labyrinth(int width, int height) throws FileNotFoundException {
		mLabyrinth = new MLabyrinth(width, height);
		vLabyrinth = new VLabyrinth(width, height);
	}

	public MVertex getVertexByXY(int x, int y) {
		return mLabyrinth.getGraph().getVertexByXY(x, y);
	}

	public void launchManhattan(MVertex sourceVertex, MVertex targetVertex) {
		mLabyrinth.launchManhattan(sourceVertex, targetVertex);
	}

	public void start(Stage stage) {
		vLabyrinth.start(stage);
	}

	public void setOnKeyPressed(EventHandler<KeyEvent> eventHandlerOnKeypressed) {
		vLabyrinth.setOnKeyPressed(eventHandlerOnKeypressed);
	}

	public void drawFrame() {
		vLabyrinth.drawFrame();
	}

	public void addSprite(ISprite iSprite) {
		vLabyrinth.addSprite(iSprite);
	}

	public void removeAllWalls() {
		vLabyrinth.removeAllWalls();
	}

	public void removeAllEdges() {
		mLabyrinth.removeAllEdges();
	}

	public void removeAllVertices() {
		mLabyrinth.removeAllVertices();
	}

	public int getWidth() {
		return mLabyrinth.getWidth();
	}

	public int getHeight() {
		return mLabyrinth.getHeight();
	}

	public void buildRandomPath(MVertex vdoor) {
		mLabyrinth.buildRandomPath(null, vdoor);
	}

	public Set<MVertex> vertexSet() {
		return mLabyrinth.getGraph().vertexSet();
	}

	public void closeDoorRandom() {
		mLabyrinth.closeDoorRandom();
	}

	public void openDoorRandom() {
		mLabyrinth.openDoorRandom();
	}

	public boolean inBorders(MVertex source, Directions dir) {
		return mLabyrinth.inBorders(source, dir);
	}

	public MVertex getVertexByDir(MVertex source, Directions dir) {
		return mLabyrinth.getGraph().getVertexByDir(source, dir);
	}

	public boolean isWall(MVertex source, Directions dir) {
		return mLabyrinth.isWall(source, dir);
	}

	public void drawWall(int x, int y, int x2, int y2, Paint wallColor) {
		vLabyrinth.drawWall(x, y, x2, y2, wallColor);
	}

	public boolean isClosedDoor(MVertex source, Directions dir) {
		return mLabyrinth.isClosedDoor(source, dir);
	}

	public boolean isOpenedDoor(MVertex source, Directions dir) {
		return mLabyrinth.isOpenedDoor(source, dir);
	}

	public MVertex getVertexByNbr(int i) {
		return mLabyrinth.getGraph().getVertexByNbr(i);
	}

	public boolean isOpened(MVertex vertex, Directions dir) {
		return mLabyrinth.isOpened(vertex, dir);
	}

	public MGraph getGraph() {
		return mLabyrinth.getGraph();
	}

	public void closeDoor(MEdge edge) {
		mLabyrinth.closeDoor(edge);
		this.vLabyrinth.drawWall(edge.getSource().getX(), edge.getSource().getY(), edge.getTarget().getX(), edge.getTarget().getY(), VLabyrinth.CLOSED_DOOR_COLOR);
	}

	public MEdge getEdge(MVertex vertex, Directions dir) {
		return this.mLabyrinth.getGraph().getEdge(vertex, dir);
	}

	public void drawPath() {
		this.vLabyrinth.drawPath(this.mLabyrinth.getGraph());
	}

}
