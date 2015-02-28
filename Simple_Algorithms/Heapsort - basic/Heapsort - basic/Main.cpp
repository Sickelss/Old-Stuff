#include "Main.h"
#include <iostream>
#include <vector>
#include <math.h>
using namespace std;



/*
A simple version of heapsort. This does NOT implement the C++ standard library class for heap. 
Instead, I will use an inplace, sift-down variant sort of an array.

*/

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

vector<int> removeTop(vector<int> arr, int end)
{
	int temp = arr[0];		// swap the root node (highest priority), with the end node.
	arr[0] = arr[end];		// we will now consider the end node out of the heap, and in the sorted array.
	arr[end] = temp;
	int count = 0;
	int child;
	bool stop = false;
	while ((count * 2 + 1) < end && !stop)		//as long as there is at least 1 child node to check
	{
		if ((count * 2 + 2) < end)		//bound check on the right child node
		{
			(arr[count * 2 + 1] > arr[count * 2 + 2]) ? child = (count * 2 + 1) : child = (count * 2 + 2);	//select the greater of the two children as child.
		}
		else	// only left node in bound
		{
			child = count * 2 + 1;
		}
		if (arr[count] < arr[child])	//if the val at count is less than the val at the greater of the children.
		{
			temp = arr[count];		//swap the node with the greater of it's children
			arr[count] = arr[child];
			arr[child] = temp;
			count = child;		//count is now that child node index, and we continue to ripple down.
		}
		else
		{
			stop = true;
		}
	}
	return arr;
}

vector<int> buildHeap(vector<int> arr)
{
	long size = floor(log2(arr.size()));
	for (int i = exp2(size); i >= 0; i--)
	{
		int count = i;
		bool stop = false;
		while (!stop && count * 2 + 1 < arr.size() -1 )
		{
			int child;
			if (count * 2 + 2 < arr.size() -1)
			{
				(arr[count*2+1] > arr[count*2+2]) ? child = count*2+1 : child = count*2+2;
			}
			else
			{
				child = count * 2 + 1;
			}
			if (arr[count] < arr[child])
			{
				int temp = arr[count];
				arr[count] = arr[child];
				arr[child] = temp;
				count = child;
				printArray(arr);
			}
			else
			{
				stop = true;
			}
		}
	}

	/* This section does a sift-up, 
	//instead I implemented a sift-down which omits testing the last order of children nodes

	for (int i = arr.size() - 1; i > 0; i--)
	{
		int count = i;
		bool stop = false;
		while (count > 0 && !stop)
		{
			if (arr[count] > arr[floor((count - 1) / 2)])	// if the node val is greater than it's parent node val
			{
				int temp = arr[count];
				arr[count] = arr[floor((count - 1) / 2)];
				arr[floor((count - 1) / 2)] = temp;
				count = floor((count - 1) / 2);
			}
			else
			{
				stop = true;
			}
		}
	}
	*/
	return arr;
}

vector<int> heapsort(vector<int> arr)
{
	arr = buildHeap(arr);		// first, make sure that the heap is built properly.
	printArray(arr);		// I'll leave this in, it's fun to see the heap.
	for (int i = arr.size() - 1; i > 0; i--)
	{
		arr = removeTop(arr, i);
		//printArray(arr);
	}
	return arr;
}

int main()
{
	const int size = 25;		//This is the size of the array.
	vector<int> arr;
	arr.resize(size);
	arr = populateArray(arr);		//fill the array with psuedo-random numbers.
	printArray(arr);
	arr = heapsort(arr);
	printArray(arr);
	cin.ignore();
}