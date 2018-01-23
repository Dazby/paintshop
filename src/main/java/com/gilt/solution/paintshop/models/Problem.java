package com.gilt.solution.paintshop.models;

import java.util.List;

/**
 * Simple pojo that contains the number of colours and the customers for the problem.
 *
 * Created by darren on 23/01/2018.
 */
public class Problem {

    private int numColours;
    private List<Customer> customers;

    public int getNumColours() {
        return numColours;
    }

    public void setNumColours(int numColours) {
        this.numColours = numColours;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
