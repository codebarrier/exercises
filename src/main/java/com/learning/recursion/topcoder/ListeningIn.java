package com.learning.recursion.topcoder;

/**
 * Created by maruthi on 11/03/16.
 */
public class ListeningIn {

    public static void main(String[] args) {
        ListeningIn listen = new ListeningIn();
        System.out.println(listen.probableMatch("cptr", "capture"));
        System.out.println(listen.probableMatch("port to me", "teleport to me"));
        System.out.println(listen.probableMatch("back  to base", "back to base"));
        System.out.println(listen.probableMatch("port  to me", "teleport to me"));

    }

    public String probableMatch(String typed, String phrase) {
        String returnValue = "";
        int charCntr = 0;
        if (typed.length() < phrase.length()) {
            for (int i = 0; i < phrase.length(); i++) {
                if (phrase.charAt(i) != (typed.charAt(charCntr)) && i >= charCntr) {
                    returnValue = returnValue + phrase.charAt(i);
                } else {
                    charCntr = charCntr == typed.length() - 1 ? charCntr : charCntr + 1;
                }
            }
        }
        return returnValue.isEmpty() ? "UNMATCHED" : returnValue;
    }
}
