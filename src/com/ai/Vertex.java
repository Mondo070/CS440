package com.ai;

public class Vertex {

	Vertex parent;
    int row;
    int col;
//    boolean start;
//    boolean goal;
//    boolean open;
    
	public Vertex(int row, int col, Vertex parent) {
		this.row = row;
		this.col = col;
        this.parent = parent;
	}
/*	public void setStart() {
		start = true;
	}
	public void setGoal() {
		goal = true;
	}*/
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}

    @Override
    public boolean equals(Object o) {
        Vertex v = (Vertex) o;
        return row == v.row && col == v.col;
    }

    public Vertex offset(int offsetX, int offsetY) {
        return new Vertex(row + offsetX, col + offsetY, this);
    }

    @Override
    public String toString() { return String.format("(%d, %d)", row, col); }

}
