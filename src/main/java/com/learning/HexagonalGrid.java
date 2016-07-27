package com.learning;

import java.util.Scanner;

/**
 * Created by maruthi on 27/07/16.
 * https://www.hackerrank.com/challenges/hexagonal-grid
 * SOLVED.
 */
public class HexagonalGrid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();
        for (int i = 0; i < testCount; i++) {
            int n = scanner.nextInt();
            int[][] grid = new int[2][n];
            for (int j = 0; j < 2; j++) {
                String line = scanner.next();
                for (int k = 0; k < n; k++) {
                    grid[j][k] = line.charAt(k) - 48;
                }
            }
            System.out.println(isGridFillPossible(grid, n) ? "YES" : "NO");
        }
    }

    private static boolean isGridFillPossible(int[][] grid, int n) {

        if (isGridFilled(grid, n)) {
            return true;
        }

        boolean response = false;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                //Yellow
                if ((j < n - 1) && grid[i][j] == 0 && grid[i][j + 1] == 0) {
                    int[][] newGrid = new int[2][n];
                    for (int k = 0; k < 2; k++) {
                        for (int l = 0; l < n; l++) {
                            newGrid[k][l] = grid[k][l];
                        }
                    }
                    newGrid[i][j] = 1;
                    newGrid[i][j + 1] = 1;
                    response = isGridFillPossible(newGrid, n);
                    if (response) {
                        return true;
                    }
                }


                //Red
                if ((i == 0) && grid[i][j] == 0 && grid[i + 1][j] == 0) {
                    int[][] newGrid = new int[2][n];
                    for (int k = 0; k < 2; k++) {
                        for (int l = 0; l < n; l++) {
                            newGrid[k][l] = grid[k][l];
                        }
                    }
                    newGrid[i][j] = 1;
                    newGrid[i + 1][j] = 1;
                    response = isGridFillPossible(newGrid, n);
                    if (response) {
                        return true;
                    }
                }

                //Maroon
                if ((i == 0 && j > 0) && grid[i][j] == 0 && grid[i + 1][j - 1] == 0) {
                    int[][] newGrid = new int[2][n];
                    for (int k = 0; k < 2; k++) {
                        for (int l = 0; l < n; l++) {
                            newGrid[k][l] = grid[k][l];
                        }
                    }
                    newGrid[i][j] = 1;
                    newGrid[i + 1][j - 1] = 1;
                    response = isGridFillPossible(newGrid, n);
                    if (response) {
                        return true;
                    }
                }
            }
        }

        return response;
    }

    private static boolean isGridFilled(int[][] grid, int n) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
