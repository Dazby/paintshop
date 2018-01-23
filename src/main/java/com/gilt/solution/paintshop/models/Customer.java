package com.gilt.solution.paintshop.models;

import java.util.HashMap;
import java.util.Map;

/**
 * POJO that represents a customer and their preferences.
 *
 * Created by darren on 21/01/2018.
 */
public class Customer {

    private Map<Integer, Character> preferences;

    public Customer() {
        preferences = new HashMap<>();
    }

    public void addPreference(int colour, char type) {
        preferences.put(colour, type);
    }

    /**
     * Checks that there is at least one customer preference in the solution.
     * Colour numbers start at 1.
     *
     * @param solution The solution string.
     * @return if the solution satisfies the problem constraint at least once.
     */
    public boolean isAcceptable(String solution) {
        for (Map.Entry<Integer, Character> combination : preferences.entrySet()) {
            if (solution.charAt(combination.getKey() - 1) == combination.getValue().charValue()) {
                return true;
            }
        }
        return false;
    }
}
