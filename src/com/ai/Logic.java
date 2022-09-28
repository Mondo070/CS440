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
