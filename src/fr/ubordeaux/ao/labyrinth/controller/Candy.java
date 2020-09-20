package fr.ubordeaux.ao.labyrinth.controller;

import java.io.FileNotFoundException;

import fr.ubordeaux.ao.labyrinth.model.MCandy;
import fr.ubordeaux.ao.labyrinth.view.VCandy;

public class Candy extends Sprite {

		public Candy(int num) throws FileNotFoundException{
			model = new MCandy();
			view = new VCandy(num);
		}
	}

