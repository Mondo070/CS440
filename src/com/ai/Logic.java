package com.ai;

import java.lang.Object;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Logic {

    private int[][] grid;
    private Vertex start;
    private Vertex goal;
    private PriorityQueue<Vertex> fringe;
    private ArrayList<Vertex> visited;

    public Logic(int[][] grid, Vertex start, Vertex goal) {
        this.grid = grid;
        this.start = start;
        this.goal = goal;
        this.fringe = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return Double.compare(v1.f, v2.f);
            }
        });
        this.visited = new ArrayList<>();
    }

    public List<Vertex> FindPath() {
        start.g = 0;
        start.parent = start;
        calcH(start, goal);
        calcF(start);

        fringe.add(start);

        while (!fringe.isEmpty()) {
            Vertex s = fringe.remove();

            visited.add(s);

            if (s.equals(goal)) {
                break;
            }

            for (Vertex neighbor : FindNeighbors(grid, s)) {
                if (!visited.contains(neighbor)) {
                    if (!fringe.contains(neighbor)) {
                        neighbor.parent = null;
                        neighbor.g = Double.POSITIVE_INFINITY;
                    }
                    UpdateVertex(s, neighbor, goal);
                }
            }
        }

        //visited.forEach(System.out::println);

        List<Vertex> path = new ArrayList<>();
        Vertex v = visited.get(visited.size() - 1);
        while(v.parent != start) {
            path.add(0, v);
            v = v.parent;
        }

        return path;
    }

    private static void calcF(Vertex v) {
        v.f = v.g + v.h;
    }

    private static boolean IsValidMove(int[][] grid, Vertex v, Vertex v1) {
        if (v1.row < 0 || v1.row > grid[0].length) return false;
        if (v1.col < 0 || v1.col > grid.length) return false;
        //return grid[v.col][v.row] == 0;
        
        return true;
    }

    private static List<Vertex> FindNeighbors(int[][] grid, Vertex v) {
        /*for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[j][i]);
            }
            System.out.println();
        }*/

        // Loop through all elements of current row
        /*for (int[] ints : grid) {
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }*/


        List<Vertex> neighbors = new ArrayList<>();
        Vertex toEast = v.offset(1, 0);
        Vertex toNorthEast = v.offset(1, 1);
        Vertex toNorth = v.offset(0, 1);
        Vertex toNorthWest = v.offset(-1, 1);
        Vertex toWest = v.offset(-1, 0);
        Vertex toSouthWest = v.offset(-1, -1);
        Vertex toSouth = v.offset(0 , -1);
        Vertex toSouthEast = v.offset(1, -1);

        if (IsValidMove(grid, v, toEast)) neighbors.add(toEast);
        if (IsValidMove(grid, v, toNorthEast)) neighbors.add(toNorthEast);
        if (IsValidMove(grid, v, toNorth)) neighbors.add(toNorth);
        if (IsValidMove(grid, v, toNorthWest)) neighbors.add(toNorthWest);
        if (IsValidMove(grid, v, toWest)) neighbors.add(toWest);
        if (IsValidMove(grid, v, toSouthWest)) neighbors.add(toSouthWest);
        if (IsValidMove(grid, v, toSouth)) neighbors.add(toSouth);
        if (IsValidMove(grid, v, toSouthEast)) neighbors.add(toSouthEast);

        return neighbors;
    }

    private static void setValues(Vertex v) {

    }

    private void UpdateVertex(Vertex s, Vertex neighbor, Vertex goal) {
        if (s.f < neighbor.g) {
            System.out.println(neighbor);
            neighbor.parent = s;
            System.out.println(neighbor.parent);
            calcG(neighbor);
            System.out.println(neighbor.g);
            calcH(neighbor, goal);
            System.out.println(neighbor.h);
            /*calcF(neighbor);
            System.out.println(neighbor.f);*/
            fringe.remove(neighbor);
            neighbor.f = neighbor.g + neighbor.h;
            System.out.println(neighbor.f);
            fringe.add(neighbor);
        }
    }
    private void UpdateVertexTheta(int grid[][], Vertex s, Vertex neighbor, Vertex goal) {
    	if (LineOfSight(grid,s.parent,neighbor)) {
    		if (s.parent.f < neighbor.g) {
    			neighbor.parent = s.parent;
    			calcG(s.parent);
    			calcH(s.parent, goal);
    			fringe.remove(neighbor);
    			neighbor.f = neighbor.g + neighbor.h;
    			fringe.add(neighbor);
    		}
    	}
    	else {
    		if (s.f < neighbor.g) {
    			neighbor.parent = s;
    			calcG(neighbor);
    			calcH(neighbor, goal);
    			fringe.remove(neighbor);
    			neighbor.f = neighbor.g + neighbor.h;
    			fringe.add(neighbor);
    		}
    	}
    }
    
    public static boolean LineOfSight(int[][] grid, Vertex v0, Vertex v1) {
    	int x0 = v0.row;
    	int y0 = v0.col;
    	int x1 = v1.row;
    	int y1 = v1.col;
    	int f = 0;
    	int dx = x1-x0;
    	int dy = y1-y0;
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

    private static void calcG(Vertex v) {
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

    private static boolean IsAdjacent(Vertex v) {
        return (v.parent.col == v.col || v.parent.row == v.row);
    }

    private static void calcH(Vertex v, Vertex goal) {
        v.h = Math.sqrt(2) * Math.min(Math.abs(v.row - goal.row), Math.abs(v.col - goal.col)) +
                Math.max(Math.abs(v.row - goal.row), Math.abs(v.col - goal.col)) -
                Math.min(Math.abs(v.row - goal.row), Math.abs(v.col - goal.col));
    }

}
