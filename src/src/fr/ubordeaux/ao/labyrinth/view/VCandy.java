package fr.ubordeaux.ao.labyrinth.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.image.Image;

public class VCandy extends VSprite {

	private static String CANDY_FILE = "src/images/candy-";

	public VCandy(int num) throws FileNotFoundException {
		super();
		String fileName = CANDY_FILE + String.valueOf(num) + ".png";
		File file = new File(fileName);
		InputStream inputStream = new FileInputStream(file);
		Image image = new Image(inputStream);
		this.initImage(image);
		double offsetx = (VLabyrinth.SPAN * VLabyrinth.CELL - image.getWidth()) * 0.5;
		double offsety = (VLabyrinth.SPAN * VLabyrinth.CELL - image.getHeight()) * 0.5;
		double scalex = (VLabyrinth.SPAN * VLabyrinth.CELL) / image.getWidth() * 0.85;
		double scaley = scalex;
		this.initScale(scalex, scaley);
		this.initOffset(offsetx, offsety);
 	}

}