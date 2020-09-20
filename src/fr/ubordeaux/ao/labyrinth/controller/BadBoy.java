package fr.ubordeaux.ao.labyrinth.controller;

import java.io.FileNotFoundException;

import fr.ubordeaux.ao.labyrinth.model.MBadBoy;
import fr.ubordeaux.ao.labyrinth.view.VBadBoy;

public class BadBoy extends Sprite {

	public BadBoy(){
		model = new MBadBoy();
		try {
			view = new VBadBoy();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
