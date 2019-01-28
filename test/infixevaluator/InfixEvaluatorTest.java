/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infixevaluator;

import java.util.List;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author matthew.towles
 */
public class InfixEvaluatorTest {
    
    public InfixEvaluatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of process method, of class InfixEvaluator.
     */
    @Test
    public void testProcess() throws Exception {
        System.out.println("process");
        InfixEvaluator instance = null;
        int expResult = 0;
        int result = instance.process();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of debug method, of class InfixEvaluator.
     */
    @Test
    public void testDebug() {
        System.out.println("debug");
        InfixEvaluator instance = null;
        instance.debug();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValidArguments method, of class InfixEvaluator.
     */
    @Test
    public void testGetValidArguments() {
        System.out.println("getValidArguments");
        InfixEvaluator instance = null;
        String[] expResult = null;
        String[] result = instance.getValidArguments();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumbers method, of class InfixEvaluator.
     */
    @Test
    public void testGetNumbers() {
        System.out.println("getNumbers");
        InfixEvaluator instance = null;
        Stack<Integer> expResult = null;
        Stack<Integer> result = instance.getNumbers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOperators method, of class InfixEvaluator.
     */
    @Test
    public void testGetOperators() {
        System.out.println("getOperators");
        InfixEvaluator instance = null;
        Stack<String> expResult = null;
        Stack<String> result = instance.getOperators();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserInputChars method, of class InfixEvaluator.
     */
    @Test
    public void testGetUserInputChars() {
        System.out.println("getUserInputChars");
        InfixEvaluator instance = null;
        char[] expResult = null;
        char[] result = instance.getUserInputChars();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTokens method, of class InfixEvaluator.
     */
    @Test
    public void testGetTokens() {
        System.out.println("getTokens");
        InfixEvaluator instance = null;
        List<String> expResult = null;
        List<String> result = instance.getTokens();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
