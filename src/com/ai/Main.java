package com.ai;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[][] grid = new int[4][3];
        grid[0][1] = 1;
        grid[1][1] = 1;
        grid[3][1] = 1;

        for (int i = 0; i < grid[0].length + 1; i++) {
            for (int j = 0; j < grid.length + 1; j++) {
                System.out.print("*    ");
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
