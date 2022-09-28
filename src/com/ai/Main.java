package com.ai;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        /*int[][] map = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 0, 1}
        };

        Vertex start = new Vertex(0, 0, null);
        Vertex end = new Vertex(3, 4, null);
        List<Vertex> path = FindPath(map, start, end);
        if (path != null) {
            for (Vertex v : path) {
                System.out.println(v);
            }
        } else
            System.out.println("No path found");
    }*/

    	/*File file = new File("C:\\Users\\Hunter\\eclipse-workspace\\Assignment 1\\src\\grid1");
        Scanner sc = new Scanner(file);
        int gridRow;
        int gridCol;
        int startRow;
        int startCol;
        int goalRow;
        int goalCol;
        int currRow;
        int currCol;
        int[][] grid = null;
        Vertex sVertex, gVertex, cVertex;
        
        while(sc.hasNextLine()) {
        	startCol = sc.nextInt();
        	startRow = sc.nextInt();
        	goalCol = sc.nextInt();
        	goalRow = sc.nextInt();
        	gridRow = sc.nextInt();
        	gridCol = sc.nextInt();
        	grid = new int[gridRow][gridCol];
        	while(sc.hasNextInt()) {
        		currRow = sc.nextInt();
        		currCol = sc.nextInt();
        		grid[currRow-1][currCol-1] = sc.nextInt();
        	}
        	
        }
        for (int i = 0; i < grid[0].length + 1; i++) {
            for (int j = 0; j < grid.length + 1; j++) {
            	if (i == startRow-1 && j == startCol-1) {
            		System.out.print("s    ");
            	}
            	else if (i == goalRow-1 && j == goalCol-1) {
            		System.out.print("g    ");
            	}
            	else {
            		System.out.print("*    ");
            	}
            }
            System.out.println();
            for (int[] ints : grid) {
                if (i != grid[0].length)
                    if (ints[i] == 0)
                        System.out.print("  0  ");
                    else
                        System.out.print("  1  ");
            }
            System.out.println();
        }
        
        Vertex[][] vertices = new Vertex[gridRow+1][gridCol+1];
        for (int i = 0; i < vertices.length; i++) {
        	for (int j = 0; j < vertices[0].length; j++) {
        		vertices[i][j] = new Vertex(i,j);
        	}	
        }
        sVertex = vertices[startRow-1][startCol-1];
        cVertex = sVertex;
        gVertex = vertices[goalRow-1][goalCol-1];
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Enter coordinates to display desired vertex info (enter the row then column):");
        int inputRow = sc2.nextInt();
        int inputCol = sc2.nextInt();
    }*/

    }
}
