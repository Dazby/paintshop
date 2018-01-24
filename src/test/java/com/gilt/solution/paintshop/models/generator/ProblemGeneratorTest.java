package com.gilt.solution.paintshop.models.generator;

import com.gilt.solution.paintshop.models.Problem;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by darren on 23/01/2018.
 */
public class ProblemGeneratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void invalidFilePath_illegalArgumentException() throws Exception {
        // When
        new ProblemGenerator("abcd");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullFilePath_illegalArgumentException() throws Exception {
        // When
        new ProblemGenerator(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidFileFormat() throws Exception {
        // When
        new ProblemGenerator("src/test/resources/input_06.txt");

    }

    @Test
    public void problemGeneratedSuccessfully() throws Exception {
        // Given
        ProblemGenerator problemGenerator;
        Problem problem;

        // When
        problemGenerator = new ProblemGenerator("src/test/resources/input_01.txt");
        problem = problemGenerator.getProblem();

        // Then
        assertThat(problem, notNullValue());
        assertThat(problem.getNumColours(), is(5));
        assertThat(problem.getCustomers().size(), is(3));
    }
}