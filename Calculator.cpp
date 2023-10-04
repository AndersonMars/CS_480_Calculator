//Marshall Anderson
//CS 480: Advanced Software Engineering
//Lab 1: Calculator

#include <iostream>;
#include <cmath>;

using namespace std;

void printHelp();
string removeSpaces();
int syntaxCheck();

int main()
{
	string input;
	//Output welcome message and notice that help is available
	while(true)
	{
		cout << "Please enter your equation:" << endl;
		cout << "For help, please enter 'Help'" << endl;
		getLine(cin, input);
		
		//remove spaces from input string
		removeSpaces(input);
	
		if(input == "Help")
		{
	
			printHelp();
	
		}
		else
		{
	
			//check for syntactical errors
			input = syntaxCheck(input);
	
		}
	}
	return 0;

}
//function to remove all spaces from the input string, for easier parsing
string removeSpaces(string input)
{

	int i = 0, j = 0;
	while(input[i])
	{
	
		if(str[i] != ' ') str[j++] = str[i];
		i++
	
	}
	str[j] = '\0';
	return input;

}

//checks if equation follows proper syntax rules, ex: parentheses in proper places, operators in proper places
int syntaxCheck(string input)
{
	int lParenthesis = 1, rParenthesis = 0;
	int lBracket = 1, rBracket = 0;
	for(int i = 0; i < input.size(); i++)
	{
	
		if(input[i] == '(') lParenthesis++;
		if(input[i] == ')') rParenthesis++;
		if(input[i] == '{') lBracket++;
		if(input[i] == '}') rBracket++;
		
		//Checks if there are extra parentheses
		if(lParenthesis == rParenthesis && i < input.size() - 1)
		{
		
			cout << "Error: Extra Parenthesis" << endl;
			return 0;
		}
		//Checks if there is a misplaced closing parentheses
		else if (lParenthesis < rParenthesis)
		{
		
			cout << "Error: Misplaced closing parenthesis" << endl;
			return 0;
		}
		//Checks for missing closing parentheses
		else if(lParenthesis > rParenthesis && i == input.size() - 1)
		{
		
			cout << "Error: Missing closing parenthesis" << endl;
			return 0;
		}
		
		//Checks if there are extra brackets
		if(lBracket == rBracket && i < input.size() - 1)
		{
		
			cout << "Error: Extra Bracket" << endl;
			return 0;
		}
		//Checks if there is a misplaced closing bracket
		else if (lBracket < rBracket)
		{
		
			cout << "Error: Misplaced closing bracket" << endl;
			return 0;
		}
		//Checks for missing closing bracket
		else if(lBracket > rBracket && i == input.size() - 1)
		{
		
			cout << "Error: Missing closing bracket" << endl;
			return 0;
		}
	
	}
	
	

}

void printHelp()
{

	cout << "The following arithmetic operators are available:" << endl;
	cout << "+, -, *, /, ^" << endl;
	cout << "Additionally, there are the following trigonometric/logarithmic functions" << endl;
	cout << "sin, cos, tan, cot, ln, log10." << endl;
	cout << "Additionally, you may use () parenthesis and {} curly brackets." << endl;

}
