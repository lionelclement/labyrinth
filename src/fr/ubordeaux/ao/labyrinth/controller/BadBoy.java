package fr.ubordeaux.ao.labyrinth.controller;

import java.io.FileNotFoundException;

import fr.ubordeaux.ao.labyrinth.model.MBadBoy;
import fr.ubordeaux.ao.labyrinth.model.MISprite;
import fr.ubordeaux.ao.labyrinth.view.VBadBoy;
import fr.ubordeaux.ao.labyrinth.view.VISprite;

public class BadBoy extends Sprite {

	private VBadBoy view;
	private MBadBoy model;

	public BadBoy(){
		model = new MBadBoy();
		try {
			view = new VBadBoy();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
