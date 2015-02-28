#include "Main.h"
#include <vector>
#include <iostream>

using namespace std;

vector<int> populateArray(vector<int> arr)
{		// function to populate array with random ints
	for (int i = 0; i< arr.size(); i++)
	{
		arr[i] = rand() % 100;
	}
	return arr;
}

void printArray(vector<int> arr)
{	// function to output array to console
	cout << "Array: \n";
	for (int i = 0; i<arr.size(); i++)
	{
		cout << arr[i] << " ";
	}
	cout << "\n";
}

vector<int> insertionSort(vector<int> arr)
{
	for (int i = 1; i < arr.size(); i++)
	{	// iterate left to right through array.
		int j = i-1;	//here I'll make j the index to the left of our current index, i. It's a matter of preference...
		while (j>=0 && arr[j + 1] < arr[j])
		{
			int temp = arr[j + 1];
			arr[j + 1] = arr[j];
			arr[j] = temp;
			j--;
		}
		//printArray(arr);	//print array each iteration.
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
	arr = insertionSort(arr);
	printArray(arr);
	cin.ignore();
}

