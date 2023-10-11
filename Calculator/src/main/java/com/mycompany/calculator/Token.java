package com.mycompany.calculator;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

//Token class to represent the tokens within a mathematical expression
public class Token 
{
    String value;
    Type type;
    
    Token(String value, Type type)
    {
        this.value = value;
        this.type = type;
    }
    
    //enum for different types of Tokens
    enum Type
    {
        UNARY_OPERATOR,
        BINARY_OPERATOR,
        GROUP_OPERATOR,
        OPERAND
    }
    
    //enum for associativity of tokens, none for numbers, left and right for operators/functions
    enum Associativity
    {
        NONE,
        LEFT,
        RIGHT
    }
    
    private static final EnumMap<Type, String[]> OPERATORS = new EnumMap<>(Map.of(
            Type.UNARY_OPERATOR, new String[] {"-", "sin", "cos", "tan", "cot", "log", "ln"},
            Type.BINARY_OPERATOR, new String[] {"^", "*", "/", "+", "-"},
            Type.GROUP_OPERATOR, new String[] {"(", ")", "{", "}"}
    
    ));
}
