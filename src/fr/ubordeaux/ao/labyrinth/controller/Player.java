package fr.ubordeaux.ao.labyrinth.controller;

import java.io.FileNotFoundException;

import fr.ubordeaux.ao.labyrinth.model.MISprite;
import fr.ubordeaux.ao.labyrinth.model.MPlayer;
import fr.ubordeaux.ao.labyrinth.view.VISprite;
import fr.ubordeaux.ao.labyrinth.view.VPlayer;

public class Player extends Sprite {

	private VPlayer view;
	private MPlayer model;

	public Player() throws FileNotFoundException{
		super();
		model = new MPlayer();
		view = new VPlayer();
	}

	@Override
	protected VISprite getView() {
		return view;
	}

	@Override
	protected MISprite getModel() {
		return model;
	}

}
