package com.ai;

import java.lang.Object;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Logic {

    private int[][] grid;
    private Vertex start;
    private Vertex goal;

    public Logic(int[][] grid, Vertex start, Vertex goal) {
        this.grid = grid;
        this.start = start;
        this.goal = goal;
    }

    public void start() {

    }

    public static boolean IsValidMove(int[][] grid, Vertex v) {
        if (v.row < 0 || v.row > grid.length - 1) return false;
        if (v.col < 0 || v.col > grid[0].length - 1) return false;
        return grid[v.col][v.row] == 0;
    }

    public static List<Vertex> FindNeighbors(int[][] grid, Vertex v) {
        List<Vertex> neighbors = new ArrayList<>();
        Vertex toLeft = v.offset(-1, 0);
        Vertex toRight = v.offset(1, 0);
        Vertex toAbove = v.offset(0, 1);
        Vertex toBelow = v.offset(0 , -1);
        if (IsValidMove(grid, toLeft)) neighbors.add(toLeft);
        if (IsValidMove(grid, toRight)) neighbors.add(toRight);
        if (IsValidMove(grid, toAbove)) neighbors.add(toAbove);
        if (IsValidMove(grid, toBelow)) neighbors.add(toBelow);

        return neighbors;
    }

    public List<Vertex> FindPath(int[][] map, Vertex start, Vertex goal) {
        PriorityQueue<Vertex> fringe = new PriorityQueue<>();
        start.parent = start;
        fringe.add(start);

        List<Vertex> visited = new ArrayList<>();

        while (!fringe.isEmpty()) {
            Vertex s = fringe.peek();
            if (s == goal) {
                return visited;
            }

            visited.add(s);

            //List<Vertex> neighborList = new ArrayList<>();
            for (Vertex neighbor : FindNeighbors(map, s)) {
                if (!visited.contains(neighbor)) {
                    if (fringe.contains(neighbor)) {
                        neighbor.parent = null;
                        neighbor.g = 0;
                    }
                    UpdateVertex(s, neighbor);
                }


                /*if (!visited.contains(neighbor) && !neighborList.contains(neighbor)) {
                    neighborList.add(neighbor);
                }*/
            }


        }

        /*while (!finished) {
            List<Vertex> neighborList = new ArrayList<>();
            for (Vertex v: visited) {
                for (Vertex neighbor : FindNeighbors(map, v)) {
                    if (!visited.contains(neighbor) && !neighborList.contains(neighbor)) {
                        neighborList.add(neighbor);
                    }
                }
            }
            for(Vertex v: neighborList) {
                visited.add(v);
                if (goal.equals(v)) {
                    finished = true;
                    break;
                }
            }
            if (!finished && neighborList.isEmpty())
                return null;*/

        List<Vertex> path = new ArrayList<>();
        Vertex v = visited.get(visited.size() - 1);
        while(v.parent != null) {
            path.add(0, v);
            v = v.parent;
        }
        return path;
    }

    private void UpdateVertex(Vertex s, Vertex neighbor) {
        if (s.f < neighbor.g) {
            calcG(neighbor);
            calcH(neighbor);
            neighbor.parent = s;
            neighbor.f = neighbor.g + neighbor.h;
        }
    }
    
    private void UpdateVertexTheta(int grid[][], Vertex s, Vertex neighbor) {
    	if (LineOfSight(grid,s.parent,neighbor)) {
    		if (s.parent.f < neighbor.g) {
    			calcG(s.parent);
    			calcH(s.parent);
    			neighbor.parent = s.parent;
    			 s.parent.f = s.parent.g + s.parent.h;
    		}
    	}
    	else {
    		if (s.f < neighbor.g) {
    			calcG(neighbor);
    			calcH(neighbor);
    			neighbor.parent = s;
    			neighbor.f = neighbor.g + neighbor.h;
    		}
    	}
    }
    
    public static boolean LineOfSight(int[][] grid, Vertex v0, Vertex v1) {
    	int x0 = v0.row;
    	int y0 = v0.col;
    	int x1 = v1.row;
    	int y1 = v1.col;
    	int f = 0;
    	int dx = Math.abs(x1-x0);
    	int dy = Math.abs(y1-y0);
    	int sy,sx;
    	if (dy < 0) {
    		dy = -dy;
    		sy = -1;
    	}
    	else {
    		sy = 1;
    	}
    	if (dx < 0) {
    		dx = -dx;
    		sx = -1;
    	}
    	else {
    		sx = 1;
    	}
    	if (dx >= dy) {
    		while (x0 != x1) {
    			f = f + dy;
    			if (f >= dx) {
    				if (grid[x0 + ((sx - 1) / 2)][y0 + ((sy -  1) / 2)] == 1) {
    					return false;
    				}
    				y0 = y0 + sy;
    				f = f - dx;
    			}
    			if (f != 0 && grid[x0 + ((sx - 1) / 2)][y0 + ((sy -  1) / 2)] == 1) {
    				return false;
    			}
    			if (dy == 0 && grid[x0 + ((sx - 1) / 2)][y0] == 1 && grid[x0 + ((sx - 1) / 2)][y0 - 1] == 1) {
    				return false;
    			}
    			x0 = x0 + sx;
    		}
    	}
    	else {
    		while (y0 != y1) {
    			f = f + dx;
    			if (f >= dy) {
    				if (grid[x0 + ((sx - 1) / 2)][y0 + ((sy -  1) / 2)] == 1) {
    					return false;
    				}
    				x0 = x0 + sx;
    				f = f - dy;
    			}
    			if (f != 0 && grid[x0 + ((sx - 1) / 2)][y0 + ((sy -  1) / 2)] == 1) {
    				return false;
    			}
    			if (dy == 0 && grid[x0][y0 + ((sy - 1) / 2)] == 1 && grid[x0 - 1][y0 + ((sy - 1) / 2)] == 1) {
    				return false;
    			}
    			y0 = y0 + sy;
    		}
    	}
    	return true;
    }

    private void calcG(Vertex v) {
        if (v.parent == null) {
            if (IsAdjacent(v)) {
                v.g = 1;
            } else {
                v.g = Math.sqrt(2);
            }
        } else {
            if (IsAdjacent(v)) {
                v.g = v.parent.g + 1;
            } else {
                v.g = v.parent.g + Math.sqrt(2);
            }
        }
    }

    private boolean IsAdjacent(Vertex v) {
        return (v.parent.col == v.col || v.parent.row == v.row);
    }

    private void calcH(Vertex v) {
        v.h = Math.sqrt(2) * Math.min(Math.abs(v.row - goal.row), Math.abs(v.col - goal.col)) +
                Math.max(Math.abs(v.row - goal.row), Math.abs(v.col - goal.col)) -
                Math.min(Math.abs(v.row - goal.row), Math.abs(v.col - goal.col));
    }

}
