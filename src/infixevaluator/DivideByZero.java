/*
 * Matthew Towles
 * 01/20/2019
 * DivideByZero - an exception
 */
package infixevaluator;

/**
 * Exception that is thrown if division by zero detected
 * 
 * @author matthew.towles
 */
public class DivideByZero extends Exception {

    DivideByZero(String ex) {
        super(ex);
    }
    
}
