package com.gilt.solution.paintshop.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by darren on 23/01/2018.
 */
public class CustomerTest {

    @Test
    public void isAcceptable() throws Exception {

        // Given
        Customer customer = new Customer();
        customer.addPreference(1, 'M');
        customer.addPreference(3, 'G');
        customer.addPreference(5, 'G');

        // When
        boolean acceptable = customer.isAcceptable("GGGGG");

        // Then
        assertThat(acceptable, is(true));
    }

    @Test
    public void isNotAcceptable() throws Exception {
// Given
        Customer customer = new Customer();
        customer.addPreference(1, 'M');
        customer.addPreference(3, 'G');
        customer.addPreference(5, 'G');

        // When
        boolean acceptable = customer.isAcceptable("GMMMM");

        // Then
        assertThat(acceptable, is(false));
    }

}