//Marshall Anderson
//CS 480: Advanced Software Engineering
//Lab 1: Calculator

#include <iostream>
#include <cmath>
#include <stack>

using namespace std;

void printHelp();
int syntaxCheck(string input);

int main()
{
	string input;
	//Output welcome message and notice that help is available
	while(true)
	{
		cout << "Please enter your equation:" << endl;
		cout << "For help, please enter 'Help' to see list of operations" << endl;
		getline(cin, input);
		
		if(input == "Help")
		{
			printHelp();
		}
		else
		{
	
			//check for syntactical errors
			int flag = syntaxCheck(input);
			
			if(flag >= 0)
			{
			
				//Calculate
			
			}
	
		}
	}
	return 0;

}

//checks if equation follows proper syntax rules, ex: parentheses in proper places, operators in proper places
int syntaxCheck(string input)
{
	//char stack will be used to track () and {}
	stack<char> syntax;
	
	//iterate over input equation, when ( or { is encountered, push into stack, pop only when ) or } is encountered respectively
	for(int i = 0; i < input.size(); i++)
	{
		
		if(input[i] == '(') syntax.push(input[i]);
		else if(input[i] == '{') syntax.push(input[i]);
		
		if(input[i] == ')' && syntax.top() == '(') syntax.pop();
		else if(input[i] == '}' && syntax.top() == '{') syntax.pop();
	
	}
	
	//if there are any ( or { left in stack, then a corresponding ) or } wasn't found, so parentheses were invalid, report to user and try again
	if(!(syntax.empty()))
	{
		cout << "Error: Invalid Parentheses/Brackets, please correct and try again." << endl;
		return -1;
	}
	
	
	
	return 0;
}

void printHelp()
{

	cout << "The following arithmetic operators are available:" << endl;
	cout << "+, -, *, /, ^" << endl;
	cout << "Additionally, there are the following trigonometric/logarithmic functions" << endl;
	cout << "sin, cos, tan, cot, ln, log10." << endl;
	cout << "Additionally, you may use () parenthesis and {} curly brackets." << endl;

}
