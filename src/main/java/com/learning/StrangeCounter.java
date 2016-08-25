package com.learning;

import java.util.Scanner;

/**
 * Created by maruthi on 25/08/16.
 * https://www.hackerrank.com/challenges/strange-code
 */
public class StrangeCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long time = scanner.nextLong();
        long startTime = 1;
        long startValue = 3;
        while (startTime < 1000000000000L) {
            long nextTime = startTime + startValue;
            long nextValue = 2 * startValue;
            if (nextTime > time) {
                break;
            }
            startValue = nextValue;
            startTime = nextTime;
        }

        long timeDiff = time - startTime;
        System.out.println(startValue - timeDiff);
    }
}
