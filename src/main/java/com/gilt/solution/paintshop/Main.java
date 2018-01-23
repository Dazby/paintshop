package com.gilt.solution.paintshop;

import com.gilt.solution.paintshop.models.Problem;
import com.gilt.solution.paintshop.models.generator.ProblemGenerator;
import com.gilt.solution.paintshop.solver.Solver;

/**
 * Created by darren on 21/01/2018.
 */
public class Main {

    /**
     * Main method. Generates a {@link Problem} object representing the given problem. Passes this into a
     * {@link Solver} instance to generate a solution.
     *
     * @param args array of one element containing the path to the problem file
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Please provide path to problem input file");
            System.exit(1);
        }

        Problem problem = new ProblemGenerator(args[0]).getProblem();
        Solver solver = new Solver(problem);
        System.out.println(solver.solve());
    }
}
