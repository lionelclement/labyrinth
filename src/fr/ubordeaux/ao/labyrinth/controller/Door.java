package fr.ubordeaux.ao.labyrinth.controller;

import java.io.FileNotFoundException;

import fr.ubordeaux.ao.labyrinth.model.MDoor;
import fr.ubordeaux.ao.labyrinth.model.MISprite;
import fr.ubordeaux.ao.labyrinth.view.VDoor;
import fr.ubordeaux.ao.labyrinth.view.VISprite;

public class Door extends Sprite {

	private VDoor view;
	private MDoor model;

	public Door() throws FileNotFoundException{
		super();
		model = new MDoor();
		view = new VDoor();
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
