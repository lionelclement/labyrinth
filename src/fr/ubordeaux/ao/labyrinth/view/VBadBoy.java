package fr.ubordeaux.ao.labyrinth.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.image.Image;

public class VBadBoy extends VSprite {

	private static String BADBOY_FILE = "src/images/bad.png";

    public VBadBoy() throws FileNotFoundException {
		super();
		File file = new File(BADBOY_FILE);
		InputStream inputStream = new FileInputStream(file);
		this.initImage(new Image(inputStream));
		this.initSpeed(250);
 	}

}