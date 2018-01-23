package com.gilt.solution.paintshop.models.generator;

import com.gilt.solution.paintshop.models.Customer;
import com.gilt.solution.paintshop.models.Problem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that works like a builder to create a {@link Problem} object that represents the problem to solve.
 * Using this pattern avoids the need for a utility class with a number of static methods.
 *
 * Created by darren on 23/01/2018.
 */
public class ProblemGenerator {

    private Problem problem;

    public ProblemGenerator(String filePath) {
        this.problem = new Problem();
        init(filePath);
    }

    public Problem getProblem() {
        return problem;
    }

    /**
     * Initialize method for loading and reading the input file.
     *
     * @param filePath path to file.
     */
    private void init(String filePath) {

        try (Scanner scanner = new Scanner(new File(filePath))) {
            problem.setNumColours(getNumberOfColours(scanner));
            problem.setCustomers(getCustomers(scanner));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int getNumberOfColours(Scanner scanner) {
        return Integer.parseInt(scanner.nextLine());
    }

    private List<Customer> getCustomers(Scanner scanner) {

        List<Customer> customers = new LinkedList<>();
        while (scanner.hasNext()) {
            customers.add(buildCustomer(scanner.nextLine()));
        }

        return customers;
    }

    /**
     * Create a customer containing a map of preferences.
     * This is read from the scanner (input file) and should match the format as described in the problem.
     *
     * @param customerString
     * @return
     */
    private Customer buildCustomer(String customerString) {

        Customer customer = new Customer();
        String[] prefs = customerString.split("\\s+");
        for (int i = 0; i < prefs.length; i += 2) {
            // TODO tidy
            customer.addPreference(Integer.valueOf(prefs[i]), (prefs[i + 1]).charAt(0));
        }
        return customer;
    }
}
