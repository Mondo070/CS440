package com.ai;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
    	File file = new File("C:\\Users\\Hunter\\eclipse-workspace\\Assignment 1\\src\\grid1");
        Scanner sc = new Scanner(file);
        int gridRow;
        int gridCol;
        int startRow = 0;
        int startCol = 0;
        int goalRow = 0;
        int goalCol = 0;
        int currRow;
        int currCol;
        int[][] grid = null;
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
    }
}
