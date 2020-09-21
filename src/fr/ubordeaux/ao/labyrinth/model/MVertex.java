package fr.ubordeaux.ao.labyrinth.model;

import javafx.scene.text.Text;

public class MVertex implements Comparable<MVertex> {

	private int x, y;
	private String label;
	private Text text; // pour déboguer;
	private boolean flag;
	private int nbr;

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public MVertex(int x, int y, int nbr) {
		this.x=x;
		this.y=y;
		this.nbr = nbr;
		this.label="N_" + x + "_" + y;
	}	

	public MVertex(int x, int y) {
		this.x=x;
		this.y=y;
		this.nbr = 0;
		this.label="N_" + x + "_" + y;
	}

	public String dotString(){
		return label + "[label=\""+String.valueOf(x)+" "+String.valueOf(y)+"(" + String.valueOf(nbr) + ")\"];\n";
	}

	public String toString(){
		return "("+String.valueOf(x)+" "+String.valueOf(y)+")";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getLabel() {
		return label;
	}

	public boolean isNeighbor(MVertex vertex) {
		int dx =(Math.abs(this.getX()-vertex.getX()));
		int dy =(Math.abs(this.getY()-vertex.getY()));
		return (((dx==0) && (dy==1))
				||((dx==1) && (dy==0)));
	}

	@Override
	public int compareTo(MVertex o) {
		if (x != o.x)
			return o.x-x;
		else
			return o.y-y;
	}

	public int getNbr() {
		return nbr;
	}

	public void setNbr(int nbr) {
		this.nbr = nbr;		
	}

}
