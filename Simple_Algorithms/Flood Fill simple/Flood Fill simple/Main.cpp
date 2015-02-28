#include "Main.h"
#include <stdio.h>
#include <stdlib.h>
#include <io.h>
#include <iostream>

using namespace std;

const int width = 20;	//X direction, sorry
const int length = 20;	//Y direction
int area[width][length];	//array we store everything in. we're only using 1 array in this sample.
int floodHeight = 3;	

// this function is to avoid having to hand-type an array.
void plainGen()
{
	for (int i=0; i < width; i++)
	{
		for (int j=0; j < length; j++)
		{
			(i==0 || j==0 || i==width-1 || j==length-1) ? area[i][j] = 0 : area[i][j] = rand() % 6 ;	//ternary allows me to leave an outer edge to ensure flood insertion.
		}
	}
}

// this will print the array area[][] to the console.
void showPlain()
{
	for (int i=0; i < width; i++)
	{
		for (int j=0; j < length; j++)
		{
			cout << area[j][i] << " ";
		}
		cout << "\n";
	}
}

// args: x,y coords for array.
void flood(int x,int y)
{
	if ((area[x+1][y] <= floodHeight) && (x+1 < width) && (area[x+1][y] != 9)) //not too tall, not out of bounds, not already flooded
	{
		area[x+1][y] = 9;
		flood(x+1, y);
	}
	if ((area[x-1][y] <= floodHeight) && (x-1 >= 0) && (area[x-1][y] != 9)) //not too tall, not out of bounds, not already flooded
	{
		area[x-1][y] = 9;
		flood(x-1, y);
	}
	if ((area[x][y+1] <= floodHeight) && (y+1 < length) && (area[x][y+1] != 9)) //not too tall, not out of bounds, not already flooded
	{
		area[x][y+1] = 9;
		flood(x, y+1);
	}
	if ((area[x][y-1] <= floodHeight) && (y-1 >= 0) && (area[x][y-1] != 9)) //not too tall, not out of bounds, not already flooded
	{
		area[x][y-1] = 9;
		flood(x, y-1);
	}
}

int main()
{
	plainGen();
	showPlain();
	cout << "\n" << "\n";
	flood(0,0);
	showPlain();
	cin.ignore();	// enter 
}