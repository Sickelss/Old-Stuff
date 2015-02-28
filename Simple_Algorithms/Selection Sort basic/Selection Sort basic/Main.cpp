#include "Main.h"
#include <iostream>
#include <vector>
using namespace std;

vector<int> populateArray(vector<int> arr)
{	// function to populate the array with random integers.
	for (int i = 0; i< arr.size(); i++)
	{
		arr[i] = rand() % 100;	//random integers from 0-100
	}
	return arr;
}

void printArray(vector<int> arr)
{		//function to print the array to console output.
	cout << "Array: \n";
	for (int i = 0; i<arr.size(); i++)
	{
		cout << arr[i] << " ";
	}
	cout << "\n";
}

vector<int> selectionSort(vector<int> arr)
{
	for (int i = 0; i < arr.size(); i++)
	{
		int current = i;
		for (int j = i; j < arr.size(); j++)
		{
			if (arr[current] > arr[j])
			{
				current = j;
			}
		}
		if (current != i)
		{
			int temp = arr[current];	//basic swap
			arr[current] = arr[i];
			arr[i] = temp;
		}
	}
	return arr;
}

int main()
{
	const int size = 25;
	vector<int> arr;
	arr.resize(size);
	arr = populateArray(arr);
	printArray(arr);
	arr = selectionSort(arr);
	printArray(arr);
	cin.ignore();
}