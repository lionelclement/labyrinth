package fr.ubordeaux.ao.labyrinth.controller;

import java.io.FileNotFoundException;
import java.util.Set;

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
		return this.mLabyrinth.getGraph().getVertexByXY(x, y);
	}

	public void launchManhattan(MVertex sourceVertex, MVertex targetVertex) {
		this.mLabyrinth.launchManhattan(sourceVertex, targetVertex);
	}

	public void start(Stage stage) {
		this.vLabyrinth.start(stage);
	}

	public void setOnKeyPressed(EventHandler<KeyEvent> eventHandlerOnKeypressed) {
		this.vLabyrinth.setOnKeyPressed(eventHandlerOnKeypressed);
	}

	public void drawFrame() {
		this.vLabyrinth.drawFrame();
	}

	public void addSprite(ISprite iSprite) {
		this.vLabyrinth.addSprite(iSprite);
	}

	public void removeAllWalls() {
		this.vLabyrinth.removeAllWalls();
	}

	public void removeAllEdges() {
		this.mLabyrinth.removeAllEdges();
	}

	public void removeAllVertices() {
		this.mLabyrinth.removeAllVertices();
	}

	public int getWidth() {
		return this.mLabyrinth.getWidth();
	}

	public int getHeight() {
		return this.mLabyrinth.getHeight();
	}

	public void buildRandomPath(MVertex vdoor) {
		this.mLabyrinth.buildRandomPath(null, vdoor);
	}

	public Set<MVertex> vertexSet() {
		return this.mLabyrinth.getGraph().vertexSet();
	}

	public void closeDoorRandom() {
		this.mLabyrinth.closeDoorRandom();
	}

	public void openDoorRandom() {
		this.mLabyrinth.openDoorRandom();
	}

	public boolean inBorders(MVertex source, Directions dir) {
		return this.mLabyrinth.inBorders(source, dir);
	}

	public MVertex getVertexByDir(MVertex source, Directions dir) {
		return this.mLabyrinth.getGraph().getVertexByDir(source, dir);
	}

	public boolean isWall(MVertex source, Directions dir) {
		return this.mLabyrinth.isWall(source, dir);
	}

	public void drawWall(int x, int y, int x2, int y2, Paint wallColor) {
		this.vLabyrinth.drawWall(x, y, x2, y2, wallColor);
	}

	public boolean isClosedDoor(MVertex source, Directions dir) {
		return this.mLabyrinth.isClosedDoor(source, dir);
	}

	public boolean isOpenedDoor(MVertex source, Directions dir) {
		return this.mLabyrinth.isOpenedDoor(source, dir);
	}

	public MVertex getVertexByNbr(int i) {
		return this.mLabyrinth.getGraph().getVertexByNbr(i);
	}

	public boolean isOpened(MVertex vertex, Directions dir) {
		return this.mLabyrinth.isOpened(vertex, dir);
	}

	public MGraph getGraph() {
		return this.mLabyrinth.getGraph();
	}

}
