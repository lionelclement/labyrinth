package fr.ubordeaux.ao.labyrinth.view;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import fr.ubordeaux.ao.labyrinth.controller.Door;
import fr.ubordeaux.ao.labyrinth.controller.ISprite;
import fr.ubordeaux.ao.labyrinth.controller.Sprite;
import fr.ubordeaux.ao.labyrinth.model.MEdge;
import fr.ubordeaux.ao.labyrinth.model.MGraph;
import fr.ubordeaux.ao.labyrinth.model.MVertex;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VLabyrinth {

	static final int SPAN = 4; // Pixels for a unit
	static final int WALL = 2; // thickness of the walls (in units)
	static final int CELL = 9; // size of the cells (in units)
	public static final Paint SCENE_COLOR = Color.WHITE;
	public static final Paint OPENED_DOOR_COLOR = Color.WHITE;
	public static final Paint CLOSED_DOOR_COLOR = Color.RED;
	public static final Paint WALL_COLOR = Color.BURLYWOOD;
	public Scene scene;
	public Pane pane = new Pane();
	public Stage stage;
	List<Rectangle> walls;
	private int width, height;

	public VLabyrinth(int width, int height) throws FileNotFoundException {
		super();
		this.walls = new ArrayList<Rectangle>();
		this.width = width;
		this.height = height;
		InputStream inputStream;
	}

	public void start(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Labyrinthe");
		this.stage.setResizable(false);
		scene = new Scene(pane, ((WALL + CELL) * width + WALL) * SPAN, ((WALL + CELL) * height + WALL) * SPAN);
		scene.setFill(SCENE_COLOR);
		this.stage.show();
	}

	public void drawFrame() {
		Rectangle square;
		stage.setScene(scene);

		square = new Rectangle(0, 0, SPAN * (width * (CELL + WALL) + WALL), WALL * SPAN);
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);

		square = new Rectangle(0, SPAN * (height * (CELL + WALL)), SPAN * (width * (CELL + WALL) + WALL), WALL * SPAN);
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);

		square = new Rectangle(0, 0, WALL * SPAN, SPAN * (height * (CELL + WALL) + WALL));
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);

		square = new Rectangle(SPAN * (width * (CELL + WALL)), 0, WALL * SPAN, SPAN * (height * (CELL + WALL) + WALL));
		square.setFill(WALL_COLOR);
		pane.getChildren().add(square);

		for (int x = 0; x < width - 1; ++x) {
			int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
			for (int y = 0; y < height - 1; ++y) {
				int offsetY = ((WALL + CELL) + (WALL + CELL) * y) * SPAN;
				square = new Rectangle(offsetX, offsetY, WALL * SPAN, WALL * SPAN);
				square.setFill(WALL_COLOR);
				pane.getChildren().add(square);
			}
		}
	}

	public void drawWall(int xs, int ys, int xt, int yt, Paint color) {
		Rectangle square = null;
		int x = 0, y = 0, xspan = 0, yspan = 0;
		if (ys == yt) {
			x = ((WALL + CELL) + (WALL + CELL) * ((int) (xs + xt) / 2)) * SPAN;
			y = (WALL + ys * (WALL + CELL)) * SPAN;
			xspan = WALL * SPAN;
			yspan = CELL * SPAN;
			square = new Rectangle(x, y, xspan, yspan);
			square.setFill(color);
			pane.getChildren().add(square);
		} else if (xs == xt) {
			x = (WALL + xs * (WALL + CELL)) * SPAN;
			y = ((WALL + CELL) + (WALL + CELL) * ((int) (ys + yt) / 2)) * SPAN;
			;
			xspan = CELL * SPAN;
			yspan = WALL * SPAN;
			square = new Rectangle(x, y, xspan, yspan);
			square.setFill(color);
			pane.getChildren().add(square);
		}
		walls.add(square);
	}

	public void drawPath(MGraph path) {
		for (MEdge edge : path.edgeSet()) {
			Paint color = Color.BLACK;
			;
			switch (edge.getType()) {
			case OPENED_DOOR:
				color = OPENED_DOOR_COLOR;
				break;
			case CLOSED_DOOR:
				color = CLOSED_DOOR_COLOR;
				break;
			case CORRIDOR:
				color = Color.GREEN;
				break;
			}
			Line line = new Line((WALL + edge.getSource().getX() * (WALL + CELL) + CELL * 0.5) * SPAN,
					(WALL + edge.getSource().getY() * (WALL + CELL) + CELL * 0.5) * SPAN,
					(WALL + edge.getTarget().getX() * (WALL + CELL) + CELL * 0.5) * SPAN,
					(WALL + edge.getTarget().getY() * (WALL + CELL) + CELL * 0.5) * SPAN);
			line.setStroke(color);
			pane.getChildren().add(line);
			Circle circle = new Circle((WALL + edge.getSource().getX() * (WALL + CELL) + CELL * 0.5) * SPAN,
					(WALL + edge.getSource().getY() * (WALL + CELL) + CELL * 0.5) * SPAN, 4);
			circle.setFill(Color.GREEN);
			pane.getChildren().add(circle);
			circle = new Circle((WALL + edge.getTarget().getX() * (WALL + CELL) + CELL * 0.5) * SPAN,
					(WALL + edge.getTarget().getY() * (WALL + CELL) + CELL * 0.5) * SPAN, 4);
			circle.setFill(Color.GREEN);
			pane.getChildren().add(circle);
		}
	}

	public void drawVertex(MVertex vertex) {
		if (vertex.getText() != null)
			this.pane.getChildren().remove(vertex.getText());
		Text text = new Text();
		vertex.setText(text);

		text.setX((WALL + vertex.getX() * (WALL + CELL) + CELL * 0.5) * SPAN);
		text.setY((WALL + vertex.getY() * (WALL + CELL) + CELL * 0.5) * SPAN);
		text.setText(String.valueOf(vertex.getNbr()));
		this.pane.getChildren().add(text);
	}

	public void setOnKeyPressed(EventHandler<KeyEvent> eventHandler) {
		scene.setOnKeyPressed(eventHandler);
	}

	public void addSprite(ISprite iSprite) {
		pane.getChildren().add(iSprite.getImageView());
	}

	public void removeAllWalls() {
		for (Rectangle rec : walls)
			pane.getChildren().remove(rec);

	}

	public void addSprite(Sprite sprite) {
		pane.getChildren().add(sprite.getImageView());
	}

}