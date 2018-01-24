package com.gilt.solution.paintshop;

import com.gilt.solution.paintshop.models.Problem;
import com.gilt.solution.paintshop.models.generator.ProblemGenerator;
import com.gilt.solution.paintshop.solver.Solver;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by darren on 21/01/2018.
 */
public class SolverTest {

    private Solver solver;

    private Solver getSolver(String filePath) {
        Problem problem = new ProblemGenerator(filePath).getProblem();
        solver = new Solver(problem);
        return solver;
    }

    private String getExpectedOutput(String filePath) {
        String expectedOutput = "No solution exists";
        try (Scanner in = new Scanner(new File(filePath))) {
            expectedOutput = in.nextLine();
        } catch (FileNotFoundException e) {
            fail(e.getMessage());
        }
        return expectedOutput;
    }

    private void assertOutput(String inputFilePath, String outputFilePath) {

        // Given
        String expectedOutput = getExpectedOutput(outputFilePath);

        // When
        String solution = getSolver(inputFilePath).solve();

        // Then
        assertThat(expectedOutput, is(solution));
    }

    @Test
    public void solve_success() throws Exception {
        assertOutput("src/test/resources/input_01.txt", "src/test/resources/output_01.txt");
        assertOutput("src/test/resources/input_03.txt", "src/test/resources/output_03.txt");
        assertOutput("src/test/resources/input_04.txt", "src/test/resources/output_04.txt");
    }

    @Test
    public void solve_notFound() throws Exception {
        assertOutput("src/test/resources/input_02.txt", "src/test/resources/output_02.txt");
        assertOutput("src/test/resources/input_05.txt", "src/test/resources/output_05.txt");
    }
}