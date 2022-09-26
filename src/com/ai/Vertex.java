package com.ai;

public class Vertex {

	Vertex parent;
    int row;
    int col;
    boolean start;
    boolean goal;
    boolean open;
    
	public Vertex(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public void setStart() {
		start = true;
	}
	public void setGoal() {
		goal = true;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	

}
