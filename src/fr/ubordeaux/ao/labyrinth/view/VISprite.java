package fr.ubordeaux.ao.labyrinth.view;

import fr.ubordeaux.ao.labyrinth.model.MISprite;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public interface VISprite {

	Node getImageView();

	//public void init(Image image, double speed, double scalex, double scaley);
	void updateUI(MISprite sprite);
	public void initUI(MISprite sprite);
	//public ImageView getImageView();

}
