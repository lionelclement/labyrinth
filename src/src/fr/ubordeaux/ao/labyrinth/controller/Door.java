package fr.ubordeaux.ao.labyrinth.controller;

import java.io.FileNotFoundException;

import fr.ubordeaux.ao.labyrinth.model.MDoor;
import fr.ubordeaux.ao.labyrinth.view.VDoor;

public class Door extends Sprite {

	public Door() throws FileNotFoundException{
		super();
		model = new MDoor();
		view = new VDoor();
	}

}
