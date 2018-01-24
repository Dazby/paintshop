package com.gilt.solution.paintshop.models.generator;

import com.gilt.solution.paintshop.models.Customer;
import com.gilt.solution.paintshop.models.Problem;
import com.gilt.solution.paintshop.solver.Solver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Class that works like a builder to create a {@link Problem} object that represents the problem to solve.
 * Using this pattern avoids the need for a utility class with a number of static methods.
 *
 * Created by darren on 23/01/2018.
 */
public class ProblemGenerator {

    private static final Logger log = LoggerFactory.getLogger(ProblemGenerator.class);

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
    public void init(String filePath) {

        if (filePath == null) {
            throw new IllegalArgumentException("Path to file cannot be null");
        }

        try (Scanner scanner = new Scanner(new File(filePath))) {
            problem.setNumColours(getNumberOfColours(scanner));
            log.debug("Problem has {} colours", problem.getNumColours());
            problem.setCustomers(getCustomers(scanner));
            log.debug("Problem has {} customers", problem.getCustomers().size());
        } catch (FileNotFoundException fnfe) {
            log.error("File {} not found", filePath);
            throw new IllegalArgumentException(fnfe);
        } catch (IOException ioe) {
            log.error("Error parsing file: {}", ioe.getMessage());
            throw new IllegalArgumentException(ioe);
        }
    }

    private int getNumberOfColours(Scanner scanner) throws IOException {
        if (scanner.hasNext()) {
            return Integer.parseInt(scanner.nextLine());
        } else {
            throw new IOException("Cannot parse for number of colours");
        }

    }

    private List<Customer> getCustomers(Scanner scanner) throws IOException {

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
    private Customer buildCustomer(String customerString) throws IOException {

        Customer customer = new Customer();
        String[] prefs = customerString.split("\\s+");
        for (int i = 0; i < prefs.length; i += 2) {
            int colour = Integer.parseInt(prefs[i]);
            char type = prefs[i + 1].charAt(0);
            validateFileInput(colour, type);
            customer.addPreference(colour, type);
        }
        return customer;
    }

    private void validateFileInput(int colour, char type) throws IOException {
        if (colour > problem.getNumColours()) {
            throw new IOException("Could not parse file for number of colours");
        }

        if ((Solver.GLOSS.charAt(0) != type) && (Solver.MATTE.charAt(0) != type)) {
            throw new IOException("Could not parse file for colour type");
        }
    }
}
