	package fr.ubordeaux.ao.labyrinth.view;

import fr.ubordeaux.ao.labyrinth.model.MISprite;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class VSprite implements VISprite {

	private ImageView imageView;
	private PathTransition transition;
	private int x;
	private int y;
	private double offsetx, offsety;
	private double scalex, scaley;
	private double speed;
	
	public VSprite(){
		this.scalex = 1;
		this.scaley = 1;
		this.speed = 1;
	}

	public void initOffset(double offsetx, double offsety){
		this.offsetx = offsetx;
		this.offsety = offsety;
	}

	public void initImage(Image image){
		imageView = new ImageView(image);
	}

	public void initScale(double scalex, double scaley){
		this.scalex = scalex;
		this.scaley = scaley;
		imageView.setScaleX(scalex);
		imageView.setScaleY(scaley);
	}

	public void initSpeed(double speed){
		this.speed = speed;
	}

	public void updateUI(MISprite sprite) {
		double xs = (int) ((VLabyrinth.WALL + sprite.getOldX() * (VLabyrinth.WALL+VLabyrinth.CELL)) * VLabyrinth.SPAN) + offsetx;
		double ys = (int) ((VLabyrinth.WALL + sprite.getOldY() * (VLabyrinth.WALL+VLabyrinth.CELL)) * VLabyrinth.SPAN) + offsety;
		double xt = (int) ((VLabyrinth.WALL + sprite.getX() * (VLabyrinth.WALL+VLabyrinth.CELL)) * VLabyrinth.SPAN) + offsetx;
		double yt = (int) ((VLabyrinth.WALL + sprite.getY() * (VLabyrinth.WALL+VLabyrinth.CELL)) * VLabyrinth.SPAN) + offsety;

		TranslateTransition translateTransition =
				new TranslateTransition(Duration.millis(speed), imageView);
		translateTransition.setFromX(xs);
		translateTransition.setFromY(ys);
		translateTransition.setToX(xt);
		translateTransition.setToY(yt);
		translateTransition.setInterpolator(Interpolator.EASE_BOTH);
		translateTransition.setAutoReverse(false);
		translateTransition.play();
	}

	@Override
	public void initUI(MISprite sprite) {
		double xt = (int) ((VLabyrinth.WALL + x * (VLabyrinth.WALL+VLabyrinth.CELL)) * VLabyrinth.SPAN) + offsetx;
		double yt = (int) ((VLabyrinth.WALL + y * (VLabyrinth.WALL+VLabyrinth.CELL)) * VLabyrinth.SPAN) + offsety;
		imageView.setX(xt);
		imageView.setY(yt);
	}

	@Override
	public Node getImageView() {
		return this.imageView;
	}

}