package com.learning.recursion;

/**
 * Created by maruthi on 01/03/16.
 */
public class StringSubSet {

    public static void main(String[] args) {
        StringSubSet main = new StringSubSet();
        main.listSubSets("ABCD");
    }

    private void listSubSets(String value) {
        listSubSets("", value);
    }

    private void listSubSets(String pivot, String set) {
        if (set.length() == 0) {
            //Terminate god damn it!;
        } else {
            for (int i = 0; i < set.length(); i++) {
                String local = String.valueOf(set.charAt(i));
                String localSet = set.substring(i + 1);
                System.out.println(pivot + local);
                listSubSets(pivot + local, localSet);
            }
        }
    }
}
