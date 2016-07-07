package com.delaroystudios.jigsaw;

import java.util.ArrayList;

/**
 * Puzz Entity
 * @author thangtb
 *
 */
public class PuzzEntity {
	private String alphabet;
	private String background;
	private String layout;
	private String outline;
	private ArrayList<String> parts;
	private ArrayList<Point> points;
	
	
	/**
	 * @param alphabet
	 * @param background
	 * @param layout
	 * @param outline
	 * @param parts
	 * @param points
	 */
	public PuzzEntity( String alphabet,
	 String background,
	 String layout,
	 String outline,
	 ArrayList<String> parts,
	 ArrayList<Point> points) {
		this.alphabet = alphabet;
		this.background = background;
		this.layout = layout;
		this.outline = outline;
		this.parts = parts;
		this.points = points;
	}


	public String getAlphabet() {
		return alphabet;
	}


	public String getBackground() {
		return background;
	}


	public String getLayout() {
		return layout;
	}


	public String getOutline() {
		return outline;
	}


	public ArrayList<String> getParts() {
		return parts;
	}


	public ArrayList<Point> getPoints() {
		return points;
	}

	
	
}
