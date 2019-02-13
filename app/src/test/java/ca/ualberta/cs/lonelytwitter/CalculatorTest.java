/*
 * Developed by Zhizhou Lu on $Today.
 * Last modified 12/02/19 5:32 PM.
 * Copyright (c) 2019. All right reserved.
 */

package ca.ualberta.cs.lonelytwitter;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void testAddition(){
        Calculator c = new Calculator();
        int num = c.add(5,6);
        assertEquals(11,num);
    }
}
