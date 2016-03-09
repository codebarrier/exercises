package com.learning.recursion;


import java.util.Arrays;

/**
 * Created by maruthi on 09/03/16.
 */
public class CribbageCount {


    public static void main(String[] args) {
        String[] hand = {"AD", "5C", "10S", "4H", "9C"};
        System.out.println(countFifteens(hand));
    }

    private static int countFifteens(String[] cards) {
        return countFifteens(new String[0], cards);
    }

    private static int countFifteens(String[] order, String[] cards) {
        int count = 0;
        int orderCount = 0;
        for (int i = 0; i < order.length; i++) {
            orderCount += getNumberFromCard(order[i]);
        }

        if (orderCount == 15) {
            System.out.println(Arrays.toString(order));
            return 1;
        }

        if (cards.length != 0) {

            for (int i = 0; i < cards.length; i++) {
                String card = cards[i];
                String[] restOfCards = new String[cards.length - (i + 1)];
                int restCardCntr = 0;
                for (int j = i + 1; j < cards.length; j++) {
                    restOfCards[restCardCntr++] = cards[j];
                }
                String[] newOrder = Arrays.copyOf(order, order.length + 1);
                newOrder[order.length] = card;
                count = count + countFifteens(newOrder, restOfCards);
            }
        }
        return count;
    }


    private static int getNumberFromCard(String card) {
        String number = card.substring(0, card.length() - 1);
        if (number.equals("A")) {
            return 1;
        } else if (number.equals("K") || number.equals("Q") || number.equals("J")) {
            return 10;
        } else {
            return Integer.valueOf(number);
        }

    }
}
