//Marshall Anderson
//CS 480
//Lab 2: Calculator
package com.mycompany.calculator;

import java.io.*;
import java.util.*;

public class Calculator 
{
    Stack myStack = new Stack();
    
    Queue myQueue = new LinkedList<>();
    
    
    
    public static void main(String[] args) 
    {
        //create a scanner and ask for user input
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to my calculator!" + "\n" + "Enter your equation: ");
        
        //collect user input (infix) to convert to postfix
        String infix = scan.nextLine();
        System.out.println("Infix Expression: " + infix);
        System.out.println("Postfix: " + infixToPostfix(infix));
    }
    
    //Shunting Yard Algorithm to convert format to postfix
     public static String infixToPostfix(String infix) 
     {  
         //StringBuilder to format output and stack for Shunting Yard
        StringBuilder postfix = new StringBuilder();  
        Stack<Character> operatorStack = new Stack<>();  
        
        //iterate over the infix
         for (int i = 0; i < infix.length(); i++) 
         {  
            char token = infix.charAt(i);
            
            if (Character.isDigit(token)) 
            {  
                postfix.append(token);  
            }
            //check for operators
            else if (token == '+' || token == '-' || token == '*' || token == '/' || token == '^')
            {  
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && getPrec(operatorStack.peek()) >= getPrec(token)) {  
                    postfix.append(operatorStack.pop());  
                }  
                operatorStack.push(token);  
            } 
            
            //check for parenthesis
            else if (token == '(') 
            {  
                operatorStack.push(token);  
            } 
            else if (token == ')')
            {  
                while (operatorStack.peek() != '(')
                {  
                    postfix.append(operatorStack.pop());  
                }  
                operatorStack.pop();  
            }
            
            //check for trig/log functions
            else if(Character.isLetter(token))
            {
                
            }
        }  
         
        while (!operatorStack.isEmpty())
        {  
            postfix.append(operatorStack.pop());  
        }  
       return postfix.toString();  
    }  
    
    private static int getPrec(char op)
    {
        if(op == '+' || op == '-') return 1;
        else if(op == '*' || op == '/') return 2;
        else if(op == '^') return 3;
        else return 0;
    }
    
    
}
