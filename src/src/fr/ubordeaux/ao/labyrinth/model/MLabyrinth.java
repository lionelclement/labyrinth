package fr.ubordeaux.ao.labyrinth.model;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import fr.ubordeaux.ao.labyrinth.model.MEdge.Type;

public class MLabyrinth {

	public static final int SPAN_DETECTION = 5000;
	// distance à partir de laquelle un méchant détecte
	private int width, height;

	public enum Directions {
		NORTH, SOUTH, EAST, WEST;
	};

	private MGraph graph;
	private static Random random = new Random();

	public MLabyrinth(int width, int height) {
		graph = new MGraph(MEdge.class);
		this.width = width;
		this.height = height;
	}

	public void buildRandomPath(MVertex prec, MVertex actual) {
		graph.addVertex(actual);
		if (prec != null) {
			graph.addEdge(prec, actual, new MEdge(MEdge.Type.CORRIDOR));
		}
		// une liste aléatoire des 4 points cardinaux
		Vector<Directions> v = new Vector<Directions>();
		for (int i = 0; i < 4; ++i)
			v.add(Directions.values()[i]);
		Directions directions[] = new Directions[4];
		for (int i = 0; i < directions.length; ++i) {
			int index = random.nextInt(v.size());
			directions[i] = v.get(index);
			v.remove(index);
		}
		for (int i = 0; i < 4; ++i) {
			Directions dir = directions[i];
			if (inBorders(actual, dir) && graph.doesntExist(actual, dir)) {
				int xs = actual.getX();
				int ys = actual.getY();
				int xt = 0, yt = 0;
				switch (dir) {
				case NORTH:
					xt = xs;
					yt = ys - 1;
					break;
				case SOUTH:
					xt = xs;
					yt = ys + 1;
					break;
				case EAST:
					xt = xs + 1;
					yt = ys;
					break;
				case WEST:
					xt = xs - 1;
					yt = ys;
					break;
				}
				MVertex next = new MVertex(xt, yt, actual.getNbr() + 1);
				buildRandomPath(actual, next);
			}
		}
	}

	public boolean inBorders(MVertex vertex, Directions dir) {
		switch (dir) {
		case NORTH:
			return (vertex.getY() > 0);
		case SOUTH:
			return (vertex.getY() < (height - 1));
		case EAST:
			return (vertex.getX() < (getWidth() - 1));
		case WEST:
			return (vertex.getX() > 0);
		}
		return false;
	}

	private void calculateManhattanDistance(MVertex source, MVertex target){
		if ((source != null) && (target != null)) {
			Queue<MVertex> fifo = new ArrayDeque<MVertex>();
		target.setNbr(1);
		fifo.add(target);
		while(!fifo.isEmpty()){
			MVertex actual = fifo.remove();
			for (Directions dir : Directions.values()) {
				if (this.isOpened(actual, dir)){
					MVertex next = graph.getVertexByDir(actual, dir);
					if (next.getNbr()==0){
						next.setNbr(actual.getNbr()+1);
						if (next!=source && (next.getNbr() < SPAN_DETECTION))
							fifo.add(next);
					}
				}	
			}
		}
	}	
	}

	public void launchManhattan(MVertex source, MVertex target) {
		for (MVertex vertex : graph.vertexSet())
			vertex.setNbr(0);
		calculateManhattanDistance(source, target);
	}

	public boolean isWall(MVertex vertex, Directions dir) {
		MEdge edge = graph.getEdge(vertex, dir);
		return (edge == null);
	}

	public boolean isClosed(MVertex vertex, Directions dir) {
		MEdge edge = graph.getEdge(vertex, dir);
		return (edge == null || (edge.getType() == MEdge.Type.CLOSED_DOOR));
	}

	public boolean isOpened(MVertex vertex, Directions dir) {
		MEdge edge = graph.getEdge(vertex, dir);
		return ((edge != null) && ((edge.getType() != MEdge.Type.CLOSED_DOOR)));
	}

	public boolean isClosedDoor(MVertex vertex, Directions dir) {
		MEdge edge = graph.getEdge(vertex, dir);
		return (edge != null && edge.getType() == MEdge.Type.CLOSED_DOOR);
	}

	public boolean isOpenedDoor(MVertex vertex, Directions dir) {
		MEdge edge = graph.getEdge(vertex, dir);
		return ((edge != null) && ((edge.getType() == MEdge.Type.OPENED_DOOR)));
	}

	public void openDoor(MEdge edge) {
		edge.setType(MEdge.Type.OPENED_DOOR);
	}

	public void closeDoor(MEdge edge) {
		edge.setType(MEdge.Type.CLOSED_DOOR);
	}

	public void closeDoorRandom() {
		MEdge edge = graph.randomEdge();
		closeDoor(edge);
	}

	public void openDoorRandom() {
		for (int i = 1; i <= 1000; ++i) {
			MVertex vertex = graph.randomVertex();
			if (vertex != null) {
				MLabyrinth.Directions dir = Directions.values()[random.nextInt(Directions.values().length)];
				if (isWall(vertex, dir)) {
					MVertex vertex2 = graph.getVertexByDir(vertex, dir);
					if (vertex2 != null) {
						MEdge edge = graph.getEdge(vertex, vertex2);
						if (edge == null) {
							graph.addEdge(vertex, vertex2, new MEdge(Type.OPENED_DOOR));
							return;
						}
					}
				}
			}
		}
	}

	public void removeAllEdges() {
		Set<MEdge> outgoingEdges = new HashSet<MEdge>(graph.edgeSet());
		graph.removeAllEdges(outgoingEdges);
	}

	public void removeAllVertices() {
		Set<MVertex> outgoingVertex = new HashSet<MVertex>(graph.vertexSet());
		graph.removeAllVertices(outgoingVertex);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public MGraph getGraph() {
		return graph;
	}

}