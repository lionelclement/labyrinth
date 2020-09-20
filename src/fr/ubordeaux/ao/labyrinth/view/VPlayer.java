package fr.ubordeaux.ao.labyrinth.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.image.Image;

public class VPlayer extends VSprite {

	private static String PLAYER_FILE = "src/images/player.png";

	public VPlayer() throws FileNotFoundException {
		super();
		File file = new File(PLAYER_FILE);
		InputStream inputStream = new FileInputStream(file);
		this.initImage(new Image(inputStream));
		this.initSpeed(100);
	}

}