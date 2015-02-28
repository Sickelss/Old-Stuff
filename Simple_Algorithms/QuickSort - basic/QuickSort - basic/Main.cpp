#include "Main.h"
#include <stdio.h>
#include <stdlib.h>
#include <io.h>
#include <iostream>
#include <vector>
#include <math.h>
using namespace std;

vector<int> populateArray(vector<int> arr)
{	// function to populate the array with random integers.
	for(int i=0; i< arr.size(); i++)
	{
		arr[i] = rand() % 100;	//random integers from 0-100
	}
	return arr;
}

void printArray(vector<int> arr)
{		//function to print the array to console output.
	cout << "Array: \n";
	for(int i=0; i<arr.size(); i++)
	{
		cout << arr[i] << " ";
	}
	cout << "\n";
}

vector<int> quicksort(vector<int> arr, int low, int high) 
{
	int pivot = ceil(static_cast<double>((high + low) / 2) );
	for (int i = high; i >= pivot; i--)
	{		// we will use two iterators, i moves from right-end to pivot, j moves from left-end to pivot.
		int j = 0;
		while (j < pivot)
		{
			if (arr[i]<arr[j])	// we'll be sorting from least to greatest with this.
			{
				int temp = arr[i];	// basic two element swap with temp.
				arr[i] = arr[j];
				arr[j] = temp;
				//printArray(arr);		//print on swap element for debug purposes... you know.
			}
			j++;
		}
	}
	if (high - low > 2)			//this is to stop the recursion when it gets to the point of checking tiny arrays.
	{
		arr = quicksort(arr, low, pivot);	//recursive call
		arr = quicksort(arr, pivot, high);
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

	arr = quicksort(arr,0,size-1);
	printArray(arr);
	cin.ignore();
}
