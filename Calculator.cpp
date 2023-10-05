//Marshall Anderson
//CS 480: Advanced Software Engineering
//Lab 1: Calculator

#include <iostream>
#include <cmath>
#include <stack>
#include <queue>

using namespace std;

int calculate(string expression);
string format(string expression);
bool isAnOperator(string op);

int main()
{
	string expression;
	//Output welcome message and notice that help is available
	while(true)
	{
		cout << "Please enter your equation:" << endl;
		cout << "The following arithmetic operators are available:" << endl;
		cout << "+, -, *, /, ^" << endl;
		cout << "Additionally, there are the following trigonometric/logarithmic functions" << endl;
		cout << "sin, cos, tan, cot, ln, log10." << endl;
		cout << "Additionally, you may use () parenthesis and {} curly brackets." << endl;
		getline(cin, expression);

		//format string so multi char operators like sin, cos, etc. are one character
		expression = format(expression);
		
		cout << expression << endl;

		double result = calculate(expression);
	}
	return 0;

}

string format(string expression)
{
	string formattedExpression;

	for (int i = 0; i < expression.size(); i++)
	{
		if (expression.substr(i, 3) == 'sin') formattedExpression.append("s");
		if (expression.substr(i, 3) == 'cos') formattedExpression.append("c");
		if (expression.substr(i, 3) == 'tan') formattedExpression.append("t");
		if (expression.substr(i, 3) == 'cot') formattedExpression.append("v");
		if (expression.substr(i, 3) == 'log') formattedExpression.append("l");
		if (expression.substr(i, 2) == 'ln') formattedExpression.append("n");
		else formattedExpression.append(expression[i]);
	}

	return formattedExpression;

}

//checks if the string pulled from the expression is any of the operators this calculator uses
bool isAnOperator(string op)
{

	switch (op)
	{
		case "+":
			return true;
		case "-":
			return true;
		case "*":
			return true;
		case "/":
			return true;
		case "^":
			return true;
		case "s":
			return true;
		case "c":
			return true;
		case "t":
			return true;
		case "v":
			return true;
		case "l":
			return true;
		case "g":
			return true;
		default:
			return false;
	}
}

//Uses a modified shunting yard algorithm to generate the solution to the expression, and gives an error if the given expression is invalid
double calculate(string expression)
{
	//stack will be used to track operators
	stack<char> operators;
	//queue will hold the postfix expression, and then will be calculated into solution
	queue<char> postfix;

	for (int i = 0; i < expression.size(); i++)
	{
		//if the current point in the infix is an operator
		if (expression[i].isAnOperator)
		{
			operators.push(expression[i]);
		}

	}
	
	
	return 0;
}
