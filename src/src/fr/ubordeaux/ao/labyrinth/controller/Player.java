package fr.ubordeaux.ao.labyrinth.controller;

import java.io.FileNotFoundException;

import fr.ubordeaux.ao.labyrinth.model.MPlayer;
import fr.ubordeaux.ao.labyrinth.view.VPlayer;

public class Player extends Sprite {

	public Player() throws FileNotFoundException{
		super();
		model = new MPlayer();
		view = new VPlayer();
	}

}
