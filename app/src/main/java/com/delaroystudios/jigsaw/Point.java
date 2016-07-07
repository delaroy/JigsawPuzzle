package com.delaroystudios.jigsaw;

/**
 * Point entity
 * 
 * @author thangtb
 *
 */
public class Point {

	int x;
	int y;
	/**
	 * 
	 */
	public Point() {
		// TODO Auto-generated constructor stub
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
}
