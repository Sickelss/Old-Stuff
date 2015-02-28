// generic class ArrayStack of Type T.
public class ArrayStack <T>
{
	
	public Object stack []; // Object stack of type array.
	public int size;	// size will keep track of the number of elements in the stack.
	
	public ArrayStack(int initialsize) 
	{
		stack = new Object [initialsize]; //Initial Array Size, to be passed in when creating an instance of ArrayStack.
		size = 0;		// next available index of the array.
	}
	
	// push method
	// takes Generic Type for argument i.
	// adds new value, i, to array at index size, which is the top of stack.
	// no returns
	public void push (T i)
	{
		if (size == stack.length) //If there is no space left in the array for new elements
		{
			moreSpace();	// call method moreSpace, which increases the capacity of the array.
		}
		
		stack[size]= i;		// store argument i at index size of the array.
		size++;				// increment size
		// 
	}
	
	
	// pop method
	// takes no arguments
	// removes top of stack from stack... last index (size-1) from array.
	// returns value at index of size.
	@SuppressWarnings("unchecked")
	public T pop() throws Exception
	{
		if(!isEmpty())	//as long as the array isn't empty, we can return values.
		{
			int lastindex = size - 1;	// remember size actually points to the next available index in the array.
			--size;
			//Shrink if its too small... optionally
			return (T)stack[lastindex];		
		}
		throw new Exception("Nothing in the stack.");	//if the array is empty, then throw an exception.
	}
	
	
	//peek method
	// takes no args
	// does not alter stack.
	// returns the value at top
	@SuppressWarnings("unchecked")
	public T peek() throws Exception
	{
		if (!isEmpty()){return (T)stack[size-1];}
		throw new Exception("Nothing in the stack.");
	}
	
	//isEmpty method
	// takes no args
	// does not alter the stack
	// returns boolean t/f for if there is anything in the stack.
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	// moreSpace method
	// takes no args
	// increases the array capacity (length) in the event that more space is needed for the stack.
	// no returns
	private void moreSpace()
	{
		int newlength = stack.length * 2;	//create new array length with double the original length, double has something to do with amortized constant time... constants are good.
		Object [] newstack = new Object [newlength];	// create a new array.
		for (int i = 0; i < size; ++i)		// iterative copy
		{
			newstack[i] = stack[i];
		}
		stack = newstack;	// replace original stack with the new one.
	}
	
	// clear method
	// takes no args
	// removes all elements from stack
	// no returns
	public void clear()
	{
		size = 0; // you don't actually have to null the data in the array or anything. Any method to alter the stack will overwrite it and you can only peek the "existing" elements.
	}
	
}
