/*
 * Project 3 - Flood Fill
 * CS 1181 Section 90 - distance learning
 * 
 * Seth Sickels
 * 
 */


import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Main {
	
	int row;	//stores size in rows/y of data[][]
	int col;	//stores size in columns/x of data[][]
	int data[][] = new int[row][col];	
	int regionCount = 0;	//counts number of regions. Initially 0 regions.
	List<Integer> regionSize = new ArrayList<Integer>();	//made an ArrayList to store the region sizes.
	int n;		//this will be named something like currentRegionSize. I should have renamed it, but it's everywhere already.
	
	/*
	 * Method: loadFile
	 * inputs: f (string)
	 * outputs: none
	 * purpose: Loads properly formatted data from a file into a 2d array.
	 * exception: If the file is not found or not formatted properly, will throw an exception.
	 */
	public void loadFile(String f) throws FileNotFoundException
	{
		
		File file = new File (f);
		Scanner sc = new Scanner(file);
		row = sc.nextInt();
		col = sc.nextInt();
		data = new int [row][col];		//initialize data, where we will put the 2d array of data.
		
		for (int r = 0; r < row; ++r)		// iterate through rows
		{
			for (int c = 0; c < col; ++c )		//iterate through columns, we'll be seeing these nested loops a lot.
			{
				data[r][c] = sc.nextInt();
			}
		}
		sc.close();
		
	}
	
	/*
	 * Method: invertArray
	 * inputs: none
	 * outputs: none
	 * purpose: Because 1 will be a region label, all 1 areas from the input will be converted to -1.
	 * This allows the detection of -1 as an unlabeled region, and 1 as a labeled region.
	 * 
	 */
	public void invertArray()
	{
		//first thing, change all things not 0 to -1 in the array because we need those other numbers.
		for (int r = 0; r < row; ++r) // iterate rows
		{
			for (int c = 0; c < col; ++c)	//iterate columns
			{
				if (data[r][c] != 0) { data[r][c] = -1; } // I'm making it so that 0 is not a region and everything else is. Ideally, it'll be formatted with 0 and 1 anyway.
			}
		}
	}
	
	
	/*
	 * Method: flood
	 * inputs: r (int, a temp row pointer), c (int, a temp column pointer)
	 * outputs: none
	 * purpose: The flood-fill implementation is called once an unlabeled (-1) region is detected.
	 * The flood method will recursively check all adjacent indexes in the 2d array for an unlabeled index. 
	 * 
	 */
	public void flood(int r, int c)
	{
		if ( r > 0 && c > 0 && data[r-1][c-1] == -1 ) // up left
		{
			data[r-1][c-1] = regionCount;		//label the array at check index
			flood(r-1, c-1);					// recursive flood call on the checked array index. Keep flooding.
			n++; 								// increment the current region size.
		}
		
		if ( r > 0 && data[r-1][c] == -1 ) //up
		{
			data[r-1][c] = regionCount;
			flood(r-1, c);
			n++;
		}
		
		if ( r > 0 && c < col-1 && data[r-1][c+1] == -1) // up right
		{
			data[r-1][c+1] = regionCount;
			flood(r-1, c+1);
			n++;
		}
		
		if ( c > 0 && data[r][c-1] == -1 ) // left
		{
			data[r][c-1] = regionCount;
			flood(r, c-1);
			n++;
		}
		
		if (c < col-1 && data[r][c+1] == -1 ) //right
		{
			data[r][c+1] = regionCount;
			flood(r, c+1);
			n++;
		}
		
		if ( r < row-1 && c > 0 && data[r+1][c-1] == -1 ) //down left
		{
			data[r+1][c-1] = regionCount;
			flood(r+1, c-1);
			n++;
		}
		
		if (r < row-1 && data[r+1][c] == -1 ) //down
		{
			data[r+1][c] = regionCount;
			flood(r+1, c);
			n++;
		}
		
		if (r < row-1 && c < col-1 && data[r+1][c+1] == -1 ) //down right
		{
			data[r+1][c+1] = regionCount;
			flood(r+1, c+1);
			n++;
		}
		
	}
	
	
	/*
	 * Method: findRegions
	 * inputs: none
	 * outputs: none
	 * purpose: Iterate through all indexes in the 2d array to find unlabeled region (-1).
	 * If an unlabeled region is found, call flood(), save next regionSize, increment regionCount.
	 */
	public void findRegions()
	{
		
		//then we can start actually finding regions
		for (int r = 0; r < row; ++r)		//this again
		{
			for (int c = 0; c < col; ++c)
			{
				if (data[r][c] == -1) 	// A wild unlabeled region appears!
				{
					data[r][c] = ++regionCount;	//remember regionCount is initialized as 0. increment before.
					flood(r, c);			//call flood
					regionSize.add(n);		//add the n to the regionSize ArrayList
					n = 1;					//reset current region size counter.
				}
			}
		}
	}
	
	
	/*
	 * Method: saveFile
	 * inputs: outFile (string, filename to save output)
	 * outputs: void, but file at target location outFile
	 * purpose: Write formatted outputs to file.
	 * 
	 */
	public void saveFile(String outFile) throws IOException
	{
		Writer writer = new FileWriter (outFile);
		String newLine = System.getProperty("line.separator");		// Newline delimiter portability. That's some important stuff right there.
		
		writer.write("Region         Size" + newLine);		//This will output a table of Regions and their respective sizes
		writer.write("======         ====" + newLine);
		
		if (regionCount == 0)		//just in case you input an ocean... no regions... no land... adrift in a sea of nothing
		{
			writer.write("0               0" + newLine);
		}
		else if (regionCount > 0)		//otherwise there'll be regions
		{
			for (int r = 0; r < regionCount-1; r++)
			{
				writer.write(r+1 + "               " + regionSize.get(r) + newLine);
			}
		}
		
		
		writer.write(newLine);
		writer.write("Labelled Regions" + newLine);			//This part is to output the actual 2d array of labeled regions.
		writer.write("================" + newLine);
		
		for (int r = 0; r < row; ++r)
		{
			for (int c = 0; c < col; ++c)
			{
				writer.write(data[r][c] + " ");
			}
			writer.write(newLine);
		}
		writer.close();
		
	}
		
	/*
	 * Method: Main
	 */
	
	public static void main(String[] args) throws IOException 
	{
		
		Main obj = new Main();
		
		Scanner input = new Scanner(System.in);			// to read command line input.
		System.out.print("Input the filename for your data: ");			
		String fileIn  = input.nextLine();			
		
		System.out.println();
		System.out.print("Input the filename to save the file: ");
		String fileOut = input.nextLine();
		System.out.println();
		
		//obj.loadFile("testData.txt");
		obj.loadFile(fileIn);
		
		obj.invertArray();
		obj.findRegions();
		//obj.saveFile("output.txt");
		obj.saveFile(fileOut);
		
		input.close();
		
	}

}
