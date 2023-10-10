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
        //call the input function
        String output = callInput();
        System.out.println(output);
    }
    
    public static String callInput()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to my calculator!");
        while(true)
        {
            //create a scanner and ask for user input
            System.out.println("Enter your equation or Exit to quit: ");
        
            //collect user input (infix) to convert to postfix
            String infix = scan.nextLine();
            //if the exit call was made
            if(infix.equals("Exit") || infix.equals("exit")) return "Thank you for using my calculator!";
            System.out.println("Infix Expression: " + infix);
        
            infix = format(infix);
            System.out.println(infix);
            String output = "";
            //output = infixToPostfix(infix);
            //check if any () or {} are left over in string, if they are then there was a formatting error
            for(int i = 0; i < output.length(); i++)
            {
                switch(output.charAt(i))
                {
                    case '(' -> output = "Error: Mismatched Parenthesis or Brackets. Please try agian";
                    case ')' -> output = "Error: Mismatched Parenthesis or Brackets. Please try agian";
                    case '{' -> output = "Error: Mismatched Parenthesis or Brackets. Please try agian";
                    case '}' -> output = "Error: Mismatched Parenthesis or Brackets. Please try agian";
                }
            }
        
            System.out.println("Postfix: " + output);
            }
    }
    
    public static String format(String infix)
    {
        String output = "";
        
        int i = 0;
        while(i < infix.length() - 1)
        {
            char token = infix.charAt(i);
            
            //if the token isn't a letter (number or symbol), just continue
            if(!Character.isLetter(token))
            {
                output+=token;
                i++;
            }
            //token is a letter, check which statement it is, and then shorten it to its respective char form
            else
            {
                switch(token)
            {
                case 's':
                    if("sin".equals(infix.substring(i, i+3)))
                    {
                        output+=token;
                        i+=2;
                    }
                case 't':
                    if(infix.substring(i, i+3).equals("tan"))
                    {
                        output+=token;
                        i+=2;
                    }
                case 'c':
                    if(infix.substring(i, i+3).equals("cos"))
                    {
                        output+=token;
                        i+=2;
                    }
                    
                    else if(infix.substring(i, i+3).equals("cot"))
                    {
                        output+='h';
                        i+=2;
                    } 
                case 'l':
                    if(infix.substring(i, i+3).equals("log"))
                    {
                        output+='g';
                        i+=2;
                    }
                    else if(infix.substring(i, i+2).equals("ln"))
                    {
                        output+=token;
                        i++;
                    }
            }
            }
            
        }
        
        return output;
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
            else if (token == '+' || token == '-' || token == '*' || token == '/' || token == '^' || token == 's' || token == 'c' || token == 't' || token == 'l')
            {  
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && getPrec(operatorStack.peek()) >= getPrec(token))
                {  
                    postfix.append(operatorStack.pop());  
                }
                
                switch(token)
                {
                    //if sin
                    case 's': 
                    if(infix.substring(i, i+3).equals("sin"))
                    {
                        operatorStack.push(token);
                        i+=2;
                    }
                    //if cos or cot
                    case 'c':
                    if(infix.substring(i, i+3).equals("cos"))
                    {
                        operatorStack.push(token);
                        i+=2;
                    }
                    else if(infix.substring(i, i+3).equals("cot"))
                    {
                        operatorStack.push('h');
                        i+=2;
                    }
                    
                    //if tan
                    case 't':
                    if(infix.substring(i, i+3).equals("tan"))
                    {
                        operatorStack.push(token);
                        i+=2;
                    }
                    
                    //if ln or log
                    case 'l':
                    if(infix.substring(i, i+2).equals("ln"))
                    {
                        operatorStack.push(token);
                        i++;
                    }
                    else if(infix.substring(i, i+3).equals("log"))
                    {
                        //g represents log
                        operatorStack.push('g');
                        i+=2;
                    }
                    case '+', '-', '*', '/', '^':
                        operatorStack.push(token);
                }
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
            
            //check for trig/log functions, as well as error detection for errouneous characters
            /*
            else if(Character.isLetter(token))
            {
                switch(token)
                {
                    //if sin
                    case 's': 
                    if(infix.substring(i, i+3).equals("sin"))
                    {
                        operatorStack.push(token);
                        i+=2;
                    }
                    //if cos or cot
                    case 'c':
                    if(infix.substring(i, i+3).equals("cos"))
                    {
                        operatorStack.push(token);
                        i+=2;
                    }
                    else if(infix.substring(i, i+3).equals("cot"))
                    {
                        operatorStack.push('h');
                        i+=2;
                    }
                    
                    //if tan
                    case 't':
                    if(infix.substring(i, i+3).equals("tan"))
                    {
                        operatorStack.push(token);
                        i+=2;
                    }
                    
                    //if ln or log
                    case 'l':
                    if(infix.substring(i, i+2).equals("ln"))
                    {
                        operatorStack.push(token);
                        i++;
                    }
                    else if(infix.substring(i, i+3).equals("log"))
                    {
                        //g represents log
                        operatorStack.push('g');
                        i+=2;
                    }
                }   
            }
            */
        }  
         
        while (!operatorStack.isEmpty())
        {  
            postfix.append(operatorStack.pop());  
        }  
       return postfix.toString();  
    }  
    
    private static int getPrec(char op)
    {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/', 's', 'c', 't', 'n', 'g', 'l' -> 2;
            case '^' -> 3;
            default -> 0;
        };
    }
    
    
}
