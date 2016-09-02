package com.learning;

import java.util.Scanner;

/**
 * Created by maruthi on 02/09/16.
 * https://www.hackerrank.com/challenges/bomber-man
 */
public class Bomberman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r = scanner.nextInt();
        int c = scanner.nextInt();
        int n = scanner.nextInt();

        if (n == 0 || n == 1) {
        } else if (n % 2 == 0) {
            n = 2;
        } else if ((n - 1) % 4 == 0) {
            n = 5;
        } else if ((n + 1) % 4 == 0) {
            n = 3;
        }

        char[][] grid = new char[r][c];
        for (int i = 0; i < r; i++) {
            String row = scanner.next();
            for (int j = 0; j < c; j++) {
                grid[i][j] = row.charAt(j);
            }
        }
        getGridState(grid, n, 0, r, c);
    }

    private static void getGridState(char[][] grid, int n, int i, int r, int c) {
        if (n == 0 || n == 1) {
            printGrid(grid);
            return;
        } else if (i > 1 && i % 2 == 1) {
            char[][] newGrid = new char[r][c];
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    boolean bomb = true;
                    if (grid[j][k] == 79) {
                        newGrid[j][k] = '.';
                        bomb = false;
                    }

                    if ((j + 1 < r) && grid[j + 1][k] == 79) {
                        newGrid[j][k] = '.';
                        bomb = false;
                    }

                    if (j > 0 && grid[j - 1][k] == 79) {
                        newGrid[j][k] = '.';
                        bomb = false;
                    }

                    if (k + 1 < c && grid[j][k + 1] == 79) {
                        newGrid[j][k] = '.';
                        bomb = false;
                    }

                    if (k > 0 && grid[j][k - 1] == 79) {
                        newGrid[j][k] = '.';
                        bomb = false;
                    }

                    if (bomb) {
                        newGrid[j][k] = 79;
                    }
                }
            }
            grid = newGrid;
        }

        if (i == n) {
            if (i % 2 == 0) {
                printFullGrid(r, c);
            } else {
                printGrid(grid);
            }
            return;
        }

        getGridState(grid, n, i + 1, r, c);
    }

    private static void printFullGrid(int r, int c) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < c; i++) {
            builder.append('O');
        }

        String row = builder.toString();
        for (int i = 0; i < r; i++) {
            System.out.println(row);
        }
    }

    private static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            System.out.println(String.valueOf(grid[i]));
        }
    }
}
