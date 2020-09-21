package fr.ubordeaux.ao.labyrinth.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import org.jgrapht.graph.SimpleGraph;

import fr.ubordeaux.ao.labyrinth.model.MLabyrinth.Directions;

public class MGraph extends SimpleGraph<MVertex, MEdge> {

	Random random = new Random();

	public MGraph(Class<? extends MEdge> edgeClass) {
		super(edgeClass);
	}

	public MVertex getVertexByXY(int x, int y) {
		Iterator<MVertex> iterator = vertexSet().iterator();
		while (iterator.hasNext()) {
			MVertex v = iterator.next();
			if ((v.getX() == x) && (v.getY() == y))
				return v;
		}
		return null;
	}

	public MVertex getVertexByNbr(int nbr) {
		Iterator<MVertex> iterator = vertexSet().iterator();
		while (iterator.hasNext()) {
			MVertex v = iterator.next();
			if (v.getNbr() == nbr)
				return v;
		}
		return null;
	}

	public MVertex getVertexByDir(MVertex vertex, Directions dir) {
		int x, y;
		if (vertex == null) {
			return null;
		}

		x = vertex.getX();
		y = vertex.getY();
		switch (dir) {
		case NORTH:
			return getVertexByXY(x, y - 1);
		case SOUTH:
			return getVertexByXY(x, y + 1);
		case EAST:
			return getVertexByXY(x + 1, y);
		case WEST:
			return getVertexByXY(x - 1, y);
		}
		return null;
	}

	public boolean doesntExist(MVertex vertex, Directions dir) {
		return getVertexByDir(vertex, dir) == null;
	}

	public MEdge randomEdge() {
		MEdge result = null;
		int index = random.nextInt(edgeSet().size());
		Iterator<MEdge> iterator = edgeSet().iterator();
		;
		while (iterator.hasNext() && index-- != 0) {
			result = iterator.next();
		}
		return result;
	}

	public MVertex randomVertex() {
		MVertex result = null;
		int index = random.nextInt(vertexSet().size());
		Iterator<MVertex> iterator = vertexSet().iterator();
		;
		while (iterator.hasNext() && index-- != 0) {
			result = iterator.next();
		}
		return result;
	}

	public MEdge getEdge(MVertex vertex, Directions dir) {
		MVertex target = getVertexByDir(vertex, dir);
		return this.getEdge(vertex, target);
	}

	public boolean isConnected(MVertex source, MVertex target) {
		return this.containsEdge(source, target);
	}

	public void toDot() {
		try {
			BufferedWriter out = null;
			FileWriter fstream = new FileWriter("./graph.dot", false);
			out = new BufferedWriter(fstream);
			out.write("Graph D{\n");
			for (MVertex vertex : this.vertexSet())
				out.write(vertex.dotString());
			for (MEdge edge : this.edgeSet())
				out.write(edge.dotString());
			out.write("}\n");
			out.close();
		} catch (IOException exception) {

		}
	}

}
