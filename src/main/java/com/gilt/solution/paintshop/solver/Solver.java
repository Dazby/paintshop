package com.gilt.solution.paintshop.solver;

import com.gilt.solution.paintshop.models.Customer;
import com.gilt.solution.paintshop.models.Problem;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Solver class. Uses recursion to build all possible solutions and checks each against the customer preferences.
 * There can be no early stopping because it is possible that a later value in the solution can satisfy the customer
 * constraint.
 *
 * Created by darren on 21/01/2018.
 */
public class Solver {

    private static final Logger log = LoggerFactory.getLogger(Solver.class);

    public static final String GLOSS = "G";
    public static final String MATTE = "M";
    public static final String NO_SOLUTION_EXISTS = "No solution exists";

    private final int numColours;
    private final List<Customer> customers;

    public Solver(Problem problem) {
        this.numColours = problem.getNumColours();
        this.customers = problem.getCustomers();
    }

    /**
     * Generates all possible solutions starting with gloss. Once all combinations are computed, new solutions starting
     * with matte are computed.
     *
     * Solutions that satisfy each customers preferences are saved in the solution set.
     *
     * The optimal solution is formatted to match problem description.
     *
     * @return The optimal solution or no solution exists.
     */
    public String solve() {

        if (numColours == 0) {
            log.info("Problem has 0 colours");
            return NO_SOLUTION_EXISTS;
        }

        Set<String> solutions = new HashSet<>(numColours);

        generateSolution(GLOSS, solutions);
        generateSolution(MATTE, solutions);

        return getSolution(solutions);
    }

    /**
     * Generate a set of accepted solutions and return the optimal solution.
     *
     * @param solutions Set of acceptable solutions to be populated.
     * @return Optimal solution.
     */
    private String getSolution(Set<String> solutions) {

        String solution = NO_SOLUTION_EXISTS;
        if (solutions.size() > 0) {
            solution = formatSolution(findMinimalCostBatch(solutions));
        }
        log.info("Solution is [{}]", solution);
        return solution;
    }

    /**
     * Format the string to match problem description (a whitespace between types).
     *
     * @param solution A solution string.
     * @return A formatted solution string.
     */
    private String formatSolution(String solution) {
        return solution.replace("", " ").trim();
    }

    /**
     * It is possible to have multiple solutions to the problem. If that is the case then we need to find the solution
     * which maximizes the number of gloss paint types. This can also be found by finding the solution with the
     * minimum number of matte types.
     *
     * It is also possible that there are more than one optimal solutions...
     *
     * @param solutions Set of possible solutions.
     * @return Optimal solution.
     */
    private String findMinimalCostBatch(Set<String> solutions) {

        return solutions.stream().sorted(
                Comparator.comparingInt(o -> StringUtils.countMatches(o, MATTE))
        ).findFirst().get();
    }

    /**
     * Recursively generate a solution. Must wait until a complete solution is computed before testing for acceptance.
     *
     * @param solution Solution string.
     * @param solutions Set of accepted solutions.
     */
    private void generateSolution(String solution, Set<String> solutions) {

        if (solution.length() == numColours) {
            if (acceptSolution(solution)) {
                solutions.add(solution);
                log.info("Accepting solution [{}], current solution count is {}", solution, solutions.size());
            }
            return;
        }

        generateSolution(solution + GLOSS, solutions);
        generateSolution(solution + MATTE, solutions);
    }

    /**
     * For each customer check whether the solution is acceptable.
     *
     * @param solution Solution string.
     * @return boolean indicating solution is accepted.
     */
    private boolean acceptSolution(String solution) {

        for (Customer customer : customers) {
            if(!customer.isAcceptable(solution)) {
                return false;
            }
        }
        return true;
    }
}
