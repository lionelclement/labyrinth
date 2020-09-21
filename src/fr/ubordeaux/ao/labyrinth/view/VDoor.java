package fr.ubordeaux.ao.labyrinth.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.image.Image;

public class VDoor extends VSprite {

	private static String DOOR_OPEN_FILE = "src/images/door_open.png";

    public VDoor() throws FileNotFoundException {
    	super();
    	File file = new File(DOOR_OPEN_FILE);
    	InputStream inputStream = new FileInputStream(file);
    	this.initImage(new Image(inputStream));
 	}

}