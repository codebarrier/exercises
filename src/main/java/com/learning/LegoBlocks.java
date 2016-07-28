package com.learning;

import java.util.Scanner;

/**
 * Created by maruthi on 28/07/16.
 * https://www.hackerrank.com/challenges/lego-blocks
 * <p/>
 * Status : TimeOut , This is initial brute force approach, needs optimization.
 */
public class LegoBlocks {

    private static class Lego {
        int value;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();
        for (int i = 0; i < testCount; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            System.out.println(getNumberOfWays(new Lego[n][m]));
        }
    }

    private static long getNumberOfWays(Lego[][] grid) {

        long count = 0;

        boolean gridComplete = true;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null) {
                    gridComplete = false;
                    break;
                }
            }
            if (!gridComplete) {
                break;
            }
        }

        if (gridComplete) {
            int solidVert = 0;
            for (int j = 0; j + 1 < grid[0].length; j++) {
                for (int i = 0; i < grid.length; i++) {
                    if (grid[i][j] == grid[i][j + 1]) {
                        solidVert++;
                        break;
                    }
                }
            }
            if (solidVert == grid[0].length - 1) {
                count++;
                return count;
            }
        }

        boolean gotin = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (j + 3 < grid[i].length && grid[i][j] == null && grid[i][j + 1] == null && grid[i][j + 2] == null && grid[i][j + 3] == null) {
                    Lego[][] newGrid = new Lego[grid.length][grid[0].length];
                    for (int k = 0; k < grid.length; k++) {
                        newGrid[k] = grid[k].clone();
                    }

                    Lego lego = new Lego();
                    lego.value = 4;

                    newGrid[i][j] = lego;
                    newGrid[i][j + 1] = lego;
                    newGrid[i][j + 2] = lego;
                    newGrid[i][j + 3] = lego;
                    count += getNumberOfWays(newGrid);
                    gotin = true;
                }

                if (j + 2 < grid[i].length && grid[i][j] == null && grid[i][j + 1] == null && grid[i][j + 2] == null) {
                    Lego[][] newGrid = new Lego[grid.length][grid[0].length];
                    for (int k = 0; k < grid.length; k++) {
                        newGrid[k] = grid[k].clone();
                    }
                    Lego lego = new Lego();
                    lego.value = 3;

                    newGrid[i][j] = lego;
                    newGrid[i][j + 1] = lego;
                    newGrid[i][j + 2] = lego;
                    count += getNumberOfWays(newGrid);
                    gotin = true;
                }

                if (j + 1 < grid[i].length && grid[i][j] == null && grid[i][j + 1] == null) {
                    Lego[][] newGrid = new Lego[grid.length][grid[0].length];
                    for (int k = 0; k < grid.length; k++) {
                        newGrid[k] = grid[k].clone();
                    }
                    Lego lego = new Lego();
                    lego.value = 2;

                    newGrid[i][j] = lego;
                    newGrid[i][j + 1] = lego;

                    count += getNumberOfWays(newGrid);
                    gotin = true;
                }

                if (grid[i][j] == null && j < grid[i].length) {
                    Lego[][] newGrid = new Lego[grid.length][grid[0].length];
                    for (int k = 0; k < grid.length; k++) {
                        newGrid[k] = grid[k].clone();
                    }
                    Lego lego = new Lego();
                    lego.value = 1;

                    newGrid[i][j] = lego;
                    count += getNumberOfWays(newGrid);
                    gotin = true;
                }

                if (gotin) {
                    break;
                }
            }
            if (gotin) {
                break;
            }
        }

        return count;
    }
}
