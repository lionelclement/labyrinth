package fr.ubordeaux.ao.labyrinth.view;

import fr.ubordeaux.ao.labyrinth.model.MISprite;
import javafx.scene.Node;

public interface VISprite {

	Node getImageView();
	void updateUI(MISprite sprite);
	public void initUI(MISprite sprite);

}
