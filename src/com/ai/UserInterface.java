package com.ai;

import java.util.Scanner;

public class UserInterface {

    private final Scanner gridScanner;
    private final Scanner inputScanner;

    public UserInterface(Scanner gridScanner, Scanner inputScanner) {
        this.gridScanner = gridScanner;
        this.inputScanner = inputScanner;
    }

    public void start() {
        int gridRow = gridScanner.nextInt();
        int gridCol = gridScanner.nextInt();
        int startRow = gridScanner.nextInt();
        int startCol = gridScanner.nextInt();
        int goalRow = gridScanner.nextInt();
        int goalCol = gridScanner.nextInt();

        Vertex start = new Vertex(startRow, startCol, null);
        Vertex goal = new Vertex(goalRow, goalCol, null);
        int[][] grid = new int[gridRow][gridCol];

        while(gridScanner.hasNextLine()) {
            while(gridScanner.hasNextInt()) {
                int currRow = gridScanner.nextInt();
                int currCol = gridScanner.nextInt();
                grid[currRow-1][currCol-1] = gridScanner.nextInt();
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

        /*Vertex[][] vertices = new Vertex[gridRow+1][gridCol+1];
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices[0].length; j++) {
                vertices[i][j] = new Vertex(i,j);
            }
        }

        System.out.println("Enter coordinates to display desired vertex info (enter the row then column):");
        int inputRow = inputScanner.nextInt();
        int inputCol = inputScanner.nextInt();*/
    }

}
