package com.company;

import java.util.ArrayList;

/**
 * Created by Will on 2/22/16.
 */
public class BagItem {
    char letter;
    int weight;

    static char bagValue = ' ';

    ArrayList<Character> allowedBags;
    ArrayList<Character> disallowedBags;

    ArrayList<Character> getallowedbags(){
        return allowedBags;
    }
    ArrayList<Character> getDisallowedbags(){
        return disallowedBags;
    }
    static char getBagI(){
        return bagValue;
    }
}
