/*
 * Matthew Towles
 * 01/20/2019
 * Infix Evaluator
 */
package infixevaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * InfixEvaluator
 
 Loops through tokens - formatted user input
 Adds only getValidArguments to one of two stacks
 
 If a token is an integer - add to number stack
 
 If token is an operator, add to operator stack
  Unless token is an operator that is RIGHT_PAREN
 
 If token is RIGHT_PAREN:
  check if top of operator stack is matching LEFT_PAREN
  if it is not then pop top two numbers from number stack
  check if operation and if it is higher/lower precedence
  pop top operation from operation
  perform the operation on the popped numbers
  add new number to numbers stack
 
 Valid end state is with one number left on the numbers stack
  and nothing on the operations stack -- all parentheses matched
 
 Return the last number on the number stack - fin.
 * @author matthew.towles
 */
public class InfixEvaluator {

    // class constants
    private static final String LEFT_PAREN = "(";
    private static final String RIGHT_PAREN = ")";
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String TIMES = "*";
    private static final String DIVIDED = "/";

    // instance variables
    private Stack<Integer> numbers = new Stack<>();
    private Stack<String> operators = new Stack<>();
    
    /**
     * User input broken into char[] for evaluation
     */
    private char[] userInputChars;
    
    /**
     * String list with each number and operation
     * userInputChars processed into this for multi-digit numbers
     */
    private List<String> tokens = new ArrayList<>();


    /**
     * Constructor
     * @param userInput - from InfixGUI::infixField
     */
    public InfixEvaluator(String userInput) {
        userInputChars = removeSpaces(userInput).toCharArray();
        tokenize();
    }
    

    /**
     * Runs all processes for InfixEvaluator to get solution
     *
     * @return integer - last number on the numbers stack
     * @throws DivideByZero - from solveRemaining(), 
     *                             evaluateOperator(), 
     *                             calculateInsideParentheses()
     */
    public int process() throws DivideByZero {
        for (String tkn : tokens) {
            if (isNumeric(tkn)) {
                numbers.push(Integer.parseInt(tkn));
            }
            else if (tkn.equals(LEFT_PAREN)) {
                operators.push(tkn);
            }
            else if (tkn.equals(RIGHT_PAREN)) {
                calculateInsideParentheses();
            }
            else if (isOperator(tkn)) {
                evaluateOperator(tkn);
            }
            else {
                String msg = "Argument: \"" + tkn + "\" not valid. Valid args:\n";
                throw new IllegalArgumentException(getIllegalArgMsg(msg));
            }
        }
        return solveRemaining();
    }

    
    /**
     * Process ran to solve part of equation inside of parentheses
     * Ran when right parentheses found until matching left found
     * 
     * @throws DivideByZero - from solveNext()
     */
    private void calculateInsideParentheses() throws DivideByZero {
        while(!Objects.equals(operators.peek(), LEFT_PAREN)) {
            numbers.push(solveNext());
        }
        operators.pop();
    }
    
    
    /**
     * Process ran to evaluate an operation
     *
     * Iterates if:
     * 
     * There are operators left on stack
     * Next operator is not LEFT_PAREN
     * Next operator on stack has higher or equal priority
     * 
     * Pushes result of solveNext() to numbers stack
     * Upon exit loop - pushes op to operator stack
     * 
     * @param tkn
     * @throws DivideByZero - from solveNext()
     */
    private void evaluateOperator(String op) throws DivideByZero {
        while ( !operators.empty() && !operators.peek().equals(LEFT_PAREN)
                && getPriority(operators.peek()) >= getPriority(op)) {
            
            numbers.push(solveNext());
        }
        operators.push(op);
    }
    
    
    /**
     * Solves remaining Numbers and Operators on stacks
     * Returns last remaining number on numbers stack
     * The result is the answer to the equation entered by user
     * 
     * @return integer - last number on numbers stack
     * @throws DivideByZero - from solveNext()
     */
    private int solveRemaining() throws DivideByZero {
        while(!operators.empty()) {
            numbers.push(solveNext());
        }
        return numbers.pop();
    }
    

    /**
     * Returns priority value for operation
     * Higher priority value -> solve before lower priority value
     * Follows PEMDAS rules for priority
     * @param op
     * @return integer - priority value of given op
     */
    private int getPriority(String op) {
        int priority = 0;
        switch(op) {
            case PLUS:
            case MINUS:
                priority = 1;
                break;
            case TIMES:
            case DIVIDED:
                priority = 2;
                break;
        }
        return priority;
    }


    /**
     * Returns the solution for next equation
     * Built from top of operators and numbers stacks
     * @return integer
     * @throws DivideByZero - from calculate
     */
    private int solveNext() throws DivideByZero {
        if (numbers.size() < 2 || operators.size() < 1) {
            throw new IllegalArgumentException(
                    getIllegalArgMsg("Invalid format. Valid args:\n")
            );
        }
        return calculate(numbers.pop(), numbers.pop(), operators.pop());
    }


    /**
     * Returns result of given operation on two numbers given
     *
     * @param num2
     * @param num1
     * @param op
     * @return
     * @throws DivideByZero - from divide()
     */
    private int calculate(int num2, int num1, String op) throws DivideByZero {
        int out = 0;

        switch (op) {
            case PLUS:
                out = add(num2, num1);
                break;
            case MINUS:
                out = substract(num2, num1);
                break;
            case TIMES:
                out = multiply(num2, num1);
                break;
            case DIVIDED:
                out = divide(num2, num1);
                break;
        }
        return out;
    }


    /**
     * Returns sum of two numbers
     * @param num2
     * @param num1
     * @return integer - sum
     */
    private int add(int num2, int num1) {
        return (num1 + num2);
    }


    /**
     * Returns the difference of num2 from num1
     * @param num2
     * @param num1
     * @return integer - difference
     */
    private int substract(int num2, int num1) {
        return (num1 - num2);
    }


    /**
     * Returns the product of num2 and num1
     * @param num2
     * @param num1
     * @return integer - product
     */
    private int multiply(int num2, int num1) {
        return (num1 * num2);
    }


    /**
     * Returns the quotient of num1 divided by num2
     * @param num2
     * @param num1
     * @return integer - num1/num2
     */
    private int divide(int num2, int num1) throws DivideByZero {
        if (num2 == 0) {
            throw new DivideByZero("Denominator cannot be 0. ");
        }
        return (num1 / num2);
    }


    /**
     * Removes spaces from user input
     */
    private String removeSpaces(String input) {
        return input.replaceAll("\\s","");
    }


    /**
     * Calls getToken() to get all chars into token form
     * All tokens added to tokens
     */
    private void tokenize() {
        for (int i = 0; i < userInputChars.length; i++) {
            String tkn = getNextToken(i);
            tokens.add(tkn);
            i += tkn.length() - 1;
        }
    }


    /**
     * Returns next token in userInputChars
     * Accounts for integers with more than one digit
     * Uses Recursion
     *
     * @param i - iterator
     * @return string - the token at position i
     */
    private String getNextToken(int i) {
        // current character at time of calling:
        String token = Character.toString(userInputChars[i]);

        // check if base case reach and both chars are digits
        if (i < userInputChars.length - 1
                && Character.isDigit(userInputChars[i])
                && Character.isDigit(userInputChars[i + 1])
           ) {
            return token.concat(getNextToken(i + 1));
        }
        // base case
        return token;
    }


    /**
     * Returns true if argument is a number
     * @param tkn - string
     * @return boolean
     */
    private boolean isNumeric(String tkn) {
        return tkn.matches("^[0-9]*$");
    }


    /**
     * Returns true if argument is PLUS, MINUS, TIMES, or DIVIDED
     * @param tkn - string
     * @return boolean
     */
    private boolean isOperator(String tkn) {
        return tkn.matches("^[+*/\\-]*$");
    }


    /**
     * Prints out each stack and a separator
     */
    public void debug() {
        System.out.println(getNumbers());
        System.out.println(getOperators());
        System.out.println("------------------------");
    }
    

    /**
     * Message used to show user all valid argument symbols
     * @param msg
     * @return
     */
    private String getIllegalArgMsg(String msg) {
        String[] validArgs = getValidArguments();
        for (String arg : validArgs) {
            msg += "\t\"" + arg + "\", \n";
        }
        return msg.substring(0, msg.lastIndexOf(","));
    }
    

    /**
     * Returns an array of valid user arguments
     * Use for read/message output
     * @return String[] of valid arguments
     */
    public String[] getValidArguments() {
        String[] validArguments = {
            "Unsiged integers", 
            PLUS, 
            MINUS, 
            TIMES, 
            DIVIDED, 
            LEFT_PAREN, 
            RIGHT_PAREN
        };
        return validArguments;
    }
    

    /**
     * Returns the numbers stack
     * @return
     */
    public Stack<Integer> getNumbers() {
        return numbers;
    }

    /**
     * Returns the operators stack
     * @return
     */
    public Stack<String> getOperators() {
        return operators;
    }

    /**
     * Returns a char[] of the characters entered by user
     * @return
     */
    public char[] getUserInputChars() {
        return userInputChars;
    }

    /**
     * Returns a List of strings of user input
     * @return
     */
    public List<String> getTokens() {
        return tokens;
    }

}
