//Marshall Anderson
//CS 480
//Lab 2: Calculator
package com.mycompany.calculator;

import java.util.*;
import java.lang.*;
import java.lang.Math;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator
{
    private static boolean error = false;
    
    public static void main(String[] args)
    {
        String output = getInput();
        System.out.println(output);
    }
    
    private static String getInput()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to my calculator!");
        
        while(true)
        {
            System.out.println("Please Enter Your Equation or \"EXIT\" to exit the calculator:");
            String expression = scan.nextLine();
            if(expression.equals("EXIT"))
            {
                return "Thank you for using my calculator!";
            }
            expression = formatExpression(expression);
            String postfix = shuntingYard(expression);
            String result = evaluate(postfix);
            //if there was a divide by zero error, notify user and let them try again
            if(error) System.out.println("There was an error with your previous calculation. Please try again");
            else
            {
                //close scanner and return
                scan.close();
                return result;
            }
        }
    }
    
    //pop numbers until an operator is popped from the tokens[], then use that operator on the previous 2 numbers if binary, previous number if unary
    private static String evaluate(String expression)
    {
        Deque<String> expressionStack = new LinkedList<>();
        
        String[] tokens = expression.split("\\s+");
        
        for(int i = 0; i < tokens.length; i++)
        {
            String token = tokens[i];
            
            //if token is a number, push to stack
            if(isNumeric(token))
            {
                expressionStack.push(token);
            }
            //if an operator, some number of elements from the stack will pop
            else if(isOperator(token))
            {
                //if it is a unary operator, one elementt will pop
                if(isUnary(token))
                {
                    expressionStack.push(unaryMath(expressionStack.pop(), token));
                }
                //if binary, two elements will pop
                else
                {
                    expressionStack.push(binaryMath(expressionStack.pop(), expressionStack.pop(), token));
                }
                
            }
        }
        //Whatever is left at the end is the final result, so parse to double and return
        return expressionStack.pop();
    }

    private static String shuntingYard(String expression)
    {
        //splits our expression string into an array based on the spaces in the expression
        //ex: 4-2+3/5 would now be [4][-][2][+][3][/][5]
        String[] tokens = expression.split("\\s+");
        boolean previousOperator = true; //if this is true, its a unary negative, otherwise its subtraction
        
        StringBuilder postfix = new StringBuilder();
        Deque<String> stack = new LinkedList<>();
        
        for(int i = 0; i < tokens.length; i++)
        {
            //pull the current token
            String token = tokens[i];
            
            int j = 1 + 2;
            //if its a number it goes directly to the postfix
            if(isNumeric(token))
            {
                //add the number to the postfix (and a space for formatting)
                postfix.append(token).append(" ");
                
                // since this was a number the previous token being an operator is false
                previousOperator = false;
            }
            else if(isOperator(token))
            {
                //check if its the unary negative
                if(previousOperator == true && token.equals("-"))
                {
                    //we use ~ to represent the unary negative in the postfix expression
                    stack.push("~");
                }
                //if it is an operator that is not the unary negative
                else
                {
                    //if there is SOME operator in the stack and that operator has higher or equal precedence to the token
                    while(!stack.isEmpty() && getPrec(stack.peek()) >= getPrec(token))
                    {
                        //pop that operator to the stack, add a space for formatting
                        postfix.append(stack.pop()).append(" ");
                    }
                    //once all operators of equal or lesser precedence are added to postfix, push our token to the operator stack
                    stack.push(token); 
                }
                //since we just did something with an operator, set the operator boolean to true
                previousOperator = true;
            }
            else if(token.equals("("))
            {
                stack.push(token);
            }
            else if(token.equals("{"))
            {
               stack.push(token);
            }
            else if(token.equals(")"))
            {
                while(!stack.isEmpty() && !stack.peek().equals("("))
                {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop(); //pop the (
            }
            else if(token.equals("}"))
            {
                while(!stack.isEmpty() && stack.peek().equals("{"))
                {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop(); //pop the {
            }
        }
        
        //if there is stuff left in the operatorStack, pop it to the postfix with a space for formatting.
        while(!stack.isEmpty())
        {
            postfix.append(stack.pop()).append(' ');
        }
        
        //return our StringBuilder after trimming it.
        return postfix.toString().trim();
    }

    //helper function to determine precedence
    private static int getPrec(String token)
    {
        switch(token)
        {
            case "~":
                return 3;
            case "^":
               return 3;
            case "sin":
                return 3;
            case "cos":
                return 3;
            case "tan":
                return 3;
            case "cot":
                return 3;
            case "log":
                return 3;
            case "ln":
                return 3;
            case "*":
               return 2;
            case "/":
               return 2;
            case "+":
                return 1;
            case "-":
                return 1;
            default:
                return 0;
        }
    }
    
    //helper function to determine if the string is an operator
    private static boolean isOperator(String token)           
    {
        switch(token)
        {
            case "+":
                return true;
            case "-":
                return true;
            case "~":
                return true;
            case "/":
                return true;
            case "*":
                return true;
            case "sin":
                return true;
            case "cos":
                return true;
            case "tan":
                return true;
            case "cot":
                return true;
            case "log":
                return true;
            case "ln":
                return true;
            case "^":
                return true;
            default:
                return false;
        }
    }
    
    private static boolean isNumeric(String token)
    {
        try
        {
            Double num = Double.parseDouble(token);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    
    //checks if our operation is unary, and returns true if so, useful for evaluating the postfix expression
    private static boolean isUnary(String token)
    {
        switch(token)
        {
            case "~":
                return true;
            case "sin":
                return true;
            case "cos":
                return true;
            case "tan":
                return true;
            case "cot":
                return true;
            case "log":
                return true;
            case "ln":
                return true;
            default:
                return false;
        }
    }
    
    private static String unaryMath(String token, String operator)
    {
        double result = Double.parseDouble(token);
        
        switch(operator)
        {
            case "~":
                result *= -1.0;
                break;
            case "sin":
                result = Math.sin(result);
                break;
            case "cos":
                result  = Math.cos(result);
                break;
            case "tan":
                result = Math.tan(result);
                break;
            case "cot": //cot = cos(x)/sin(x)
                result = (Math.cos(result) / Math.sin(result));
                break;
            case "log":
                result = Math.log10(result);
                break;
            case "ln": //ln = log(x)/log(10)
                result  = Math.log(result);
                break;
            default:
                result = 0.0;
        }
        
        return String.valueOf(result);
    }
    
    private static String binaryMath(String val1, String val2, String operator)
    {
        double result;
        
        //the first value is the one that was on the bottom of the two in the stack, so it is val2
        double first = Double.parseDouble(val2);
        double second = Double.parseDouble(val1);
        
        switch(operator)
        {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            case "/":
                //check for division by zero
                try
                {
                    result = first / second;
                    break;
                }
                catch(ArithmeticException ex)
                {
                    System.out.println("Cannot divide by zero.");
                    error = true;
                }
            case "^":
                result = Math.pow(first, second);
                break;
            default:
                result = 0.0;
        }
        
        return String.valueOf(result);
    }
    
    //Takes a expression string and turns into an expression string with white spaces between every number, operator, function and symbol, makes parsing significantly easier
    public static String formatExpression(String equation) {
        // Define regular expressions to match mathematical operators and functions
        String operators = "[+\\-*/^]";
        String functions = "(sin|cos|tan|log|cot|ln)";

        // Use regex to insert whitespaces around operators and functions
        equation = equation.replaceAll("("+operators+")", " $1 ");
        equation = equation.replaceAll("("+functions+")", " $1 ");
        equation = equation.replaceAll("\\(", " ( ");
        equation = equation.replaceAll("\\)", " ) ");
        equation = equation.replaceAll("\\{", " { ");
        equation = equation.replaceAll("\\}", " } ");
        
        // Remove extra whitespaces
        equation = equation.replaceAll("\\s+", " ").trim();

        return equation;
    }
}