package fr.ubordeaux.ao.labyrinth.model;

public abstract class MSprite implements MISprite {

	private int x, y, oldX, oldY;

	protected MSprite(){
		//this.x = x;
		//this.y = y;
		//this.oldX = x;
		//this.oldY = y;
	}

	public int getOldX() {
		return oldX;
	}

	public int getOldY() {
		return oldY;
	}

	public void move(int x, int y){
		this.oldX=this.x;
		this.oldY=this.y;
		this.x=x;
		this.y=y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.oldX=this.x;
		this.oldY=this.y;
		this.x = x;
	}

	public void setY(int y) {
		this.oldX=this.x;
		this.oldY=this.y;
		this.y = y;
	}

}
