package fr.ubordeaux.ao.labyrinth.model;

import org.jgrapht.graph.DefaultEdge;

public class MEdge extends DefaultEdge implements Comparable<MEdge> {

	public enum Type{
		OPENED_DOOR("GREEN"),
		CLOSED_DOOR("RED"),
		CORRIDOR("BLACK");

		private String dotColor;

		Type(String dotColor){
			this.dotColor = dotColor;
		}
	};

	private static final long serialVersionUID = 1L;

	private Type type;

	public MEdge(Type type){
		super();
		this.type = type;
	}

	public MVertex getSource(){
		return (MVertex) super.getSource();
	}

	public MVertex getTarget(){
		return (MVertex) super.getTarget();
	}

	public String dotString() {
		return ((MVertex) super.getSource()).getLabel() +
				"--" + 
				((MVertex) super.getTarget()).getLabel() + 
				"[" +
				"color=\"" + 
				type.dotColor +
				"\"" +
				"]" +
				";\n";
	}

	public String toString() {
		return ((MVertex) super.getSource()).getLabel() +
				"--" + 
				((MVertex) super.getTarget()).getLabel();
	}

	public Type getType() {
		return type;
	}

	@Override
	public int compareTo(MEdge o) {
		int source = this.getSource().compareTo((o).getSource());
		if (source!=0)
			return source;
		else {
			return this.getTarget().compareTo((o).getTarget());
		}
	}
	
	public void setType(Type type) {
		this.type = type;
	}



}
