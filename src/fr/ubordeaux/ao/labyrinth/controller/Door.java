package fr.ubordeaux.ao.labyrinth.controller;

import java.io.FileNotFoundException;

import fr.ubordeaux.ao.labyrinth.model.MExit;
import fr.ubordeaux.ao.labyrinth.view.VExit;

public class Door extends Sprite {

	public Door() throws FileNotFoundException{
		super();
		model = new MExit();
		view = new VExit();
	}

}
