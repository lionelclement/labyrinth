package fr.ubordeaux.ao.labyrinth.controller;

import java.util.Random;
import fr.ubordeaux.ao.labyrinth.model.MLabyrinth;
import fr.ubordeaux.ao.labyrinth.model.MVertex;
import fr.ubordeaux.ao.labyrinth.view.VLabyrinth;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {

	private static MainController instance;
	private static final int WIDTH = 15;
	private static final int HEIGHT = 15;
	private static final long BADBOYS_SPEED = 25; // % speed badboys 0 to 100
	private static final int NB_BADBOYS = 3; // # badBoys
	private static final int NB_OPENDOOR = 30;
	private static final int NB_CLOSEDOOR = 0;
	private static final int NB_CANDIES = 3;
	private int candies_cpt = NB_CANDIES;
	private static String[] SOUND_FILES = { "src/sounds/Tr-3_stac_mf1_A#3.wav", "src/sounds/Water Bowl 05A.wav",
			"src/sounds/Water Bowl 01A.wav" };

	private Player player;
	private Door door;
	private Candies candies;
	private BadBoys badBoys;

	private Labyrinth labyrinth;
	private Random random = new Random();
	private Timeline timeline;

	private int SOUNDS = 3;
	private Sound[] sounds;

	private boolean begin = false;

	private MainController() throws Exception {
		timeline = new Timeline();
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(125), EventHandlerTimeLine));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		labyrinth = new Labyrinth(WIDTH, HEIGHT);
		player = new Player();
		door = new Door();

		BadBoysFactory badBoysFactory = new BadBoysFactory();
		badBoys = new BadBoys(badBoysFactory);
		for (int i = 1; i <= NB_BADBOYS; ++i)
			badBoys.add();

		CandiesFactory candiesFactory = new CandiesFactory();
		candies = new Candies(candiesFactory);
		for (int i = 1; i <= NB_CANDIES; ++i) {
			candies.add(Math.abs((random.nextInt() % 4)) + 1);
		}
		sounds = new Sound[SOUNDS];
		initSounds(sounds, SOUND_FILES);
	}

	public static MainController makeInstance() throws Exception {
		if (instance == null)
			instance = new MainController();
		return instance;
	}

	private void initSounds(Sound[] sounds, String[] fileNames) {
		for (int i = 0; i < SOUNDS; ++i) {
			sounds[i] = new Sound(fileNames[i]);
		}
	}

	private void playSound(int i) {
		sounds[i].play();
	}

	public final EventHandler<ActionEvent> EventHandlerTimeLine = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			if (begin) {
				for (int i = 0; i < badBoys.size(); ++i) {
					if (random.nextInt(100) < BADBOYS_SPEED) {
						// MISprite modelPlayer = player.getModel();
						MVertex sourceVertex = labyrinth.getVertexByXY(badBoys.getX(i), badBoys.getX(i));
						MVertex targetVertex = labyrinth.getVertexByXY(player.getX(), player.getY());
						labyrinth.launchManhattan(sourceVertex, targetVertex);
						badBoys.moveManhattan(i, labyrinth);
						if (badBoys.collides(i, player)) {
							lose();
						}
					}
				}
			}
		}
	};

	public final EventHandler<ActionEvent> EventHandlerButtonsAndMenuItems = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			System.out.println(event);
		}
	};

	public final EventHandler<KeyEvent> EventHandlerOnKeypressed = new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			begin = true;
			if (event.getCode() == KeyCode.RIGHT)
				player.moveEast(labyrinth);
			else if (event.getCode() == KeyCode.LEFT)
				player.moveWest(labyrinth);
			else if (event.getCode() == KeyCode.UP)
				player.moveNorth(labyrinth);
			else if (event.getCode() == KeyCode.DOWN)
				player.moveSouth(labyrinth);
			if (player.collides(door)) {
				exit();
			}
			for (int i = 0; i < candies.size(); ++i) {
				if (candies.collides(i, player)) {
					eatCandy(i);
					break;
				}
			}
			for (int i = 0; i < badBoys.size(); ++i) {
				if (badBoys.collides(i, player)) {
					lose();
				}
			}

		}

		private void eatCandy(int i) {
			candies.remove(i);
			candies_cpt--;
			playSound(1);
		}
	};

	public void start(Stage stage) {
		labyrinth.start(stage);
		labyrinth.setOnKeyPressed(EventHandlerOnKeypressed);

		// Ajoute les petits carrés
		labyrinth.drawFrame();

		// ajoute les sprites
		labyrinth.addSprite(door);
		labyrinth.addSprite(player);

		for (int i = 0; i < badBoys.size(); ++i)
			labyrinth.addSprite(badBoys.get(i));
		for (int i = 0; i < candies.size(); ++i) {
			labyrinth.addSprite(candies.get(i));
		}

		init();
		this.timeline.play();

	}

	public void lose() {
		System.out.println("Perdu !");
		playSound(0);
		Platform.exit();
		System.exit(0);
	}

	public void exit() {
		if (candies_cpt <= 0) {
			playSound(2);
			System.out.println("Gagné !");
		} else {
			playSound(0);
			System.out.println("Perdu, il reste encore " + candies_cpt + " bonbons");
		}
		Platform.exit();
		System.exit(0);
	}

	public void reinit() {
		// supprime tous les objets
		labyrinth.removeAllWalls();
		// supprime tous les arcs
		labyrinth.removeAllEdges();
		// supprime tous les sommets
		labyrinth.removeAllVertices();
		init();
	}

	public void init() {
		int xDoor = random.nextInt(labyrinth.getWidth());
		int yDoor = random.nextInt(labyrinth.getHeight());
		door.move(xDoor, yDoor);
		MVertex vdoor = new MVertex(xDoor, yDoor);

		// Crée un labyrinthe parfait
		labyrinth.buildRandomPath(vdoor);

		// place le joueur à l'extrémité de ce labyrinthe parfait
		int max = Integer.MIN_VALUE;
		int maxx = 0, maxy = 0;
		for (MVertex vertex : labyrinth.vertexSet())
			if (max < vertex.getNbr()) {
				max = vertex.getNbr();
				maxx = vertex.getX();
				maxy = vertex.getY();
			}
		player.move(maxx, maxy);

		// partitionne le labyrinthe
		for (int i = 1; i <= NB_CLOSEDOOR; ++i) {
			labyrinth.closeDoorRandom();
		}

		// Y crée des sauts
		for (int i = 1; i <= NB_OPENDOOR; ++i)
			labyrinth.openDoorRandom();

		// dessine les murs
		for (MVertex source : labyrinth.vertexSet()) {
			for (MLabyrinth.Directions dir : MLabyrinth.Directions.values()) {
				if (labyrinth.inBorders(source, dir)) {
					MVertex target = labyrinth.getVertexByDir(source, dir);
					if (labyrinth.isWall(source, dir)) {
						labyrinth.drawWall(source.getX(), source.getY(), target.getX(), target.getY(),
								VLabyrinth.WALL_COLOR);
					}
					if (labyrinth.isClosedDoor(source, dir)) {
						labyrinth.drawWall(source.getX(), source.getY(), target.getX(), target.getY(),
								VLabyrinth.CLOSED_DOOR_COLOR);
					}
					if (labyrinth.isOpenedDoor(source, dir)) {
						labyrinth.drawWall(source.getX(), source.getY(), target.getX(), target.getY(),
								VLabyrinth.OPENED_DOOR_COLOR);
					}
				}
			}
		}

		MVertex vplayer = labyrinth.getVertexByXY(player.getX(), player.getY());
		// place les méchants n'importe où à partir de 5 au delà du joueur et de la
		// porte
		for (int i = 0; i < badBoys.size(); ++i) {
			int min = 5;
			max = vplayer.getNbr() - 5;
			MVertex vbadboy = labyrinth.getVertexByNbr(min + random.nextInt(max - min + 1));
			badBoys.move(i, vbadboy.getX(), vbadboy.getY());
		}

		// place les bonbons à peu près entre la porte et le joueur
		MVertex vcandy = labyrinth.getVertexByNbr((int) ((vplayer.getNbr() + vdoor.getNbr()) * 0.5));
		int min = vcandy.getNbr() - 15;
		max = vcandy.getNbr() + 15;
		for (int i = 0; i < candies.size(); ++i) {
			vcandy = labyrinth.getVertexByNbr(min + random.nextInt(max - min + 1));
			candies.move(i, vcandy.getX(), vcandy.getY());
		}

	}

}