package fr.ubordeaux.ao.labyrinth.controller;

import java.io.FileNotFoundException;

import fr.ubordeaux.ao.labyrinth.model.MCandy;
import fr.ubordeaux.ao.labyrinth.model.MISprite;
import fr.ubordeaux.ao.labyrinth.view.VCandy;
import fr.ubordeaux.ao.labyrinth.view.VISprite;

public class Candy extends Sprite {

	private VISprite view;
	private MISprite model;

	public Candy(int num) throws FileNotFoundException {
		model = new MCandy();
		view = new VCandy(num);
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
