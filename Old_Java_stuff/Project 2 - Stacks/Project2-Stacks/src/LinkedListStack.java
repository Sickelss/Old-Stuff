//Generic Class of LinkedListStack of Type T.
public class LinkedListStack <T>
{
	
	public class ListNode
	{
		public T listData; // data stored in the node.
		public ListNode nextNode; //point to next node "down" in stack
		
		public ListNode (T data)
		{
			listData = data;
		}
		
	}
	
	public int size; // The size in # of nodes of the Stack.
	public ListNode topNode; // point to top of stack.
	
	// push method
	//takes in generic Type for newData. newData is the data to be stored in the node.
	//creates a new node pushed on top of the stack.
	//No return needed.
	public void push (T newData) 
	{
		++size;
		ListNode newnode = new ListNode(newData); 
		newnode.nextNode = topNode;
		topNode = newnode;
	}

	// Pop Method
	// no args
	// removes top of stack 
	// Returns the value held in the node returned, listData.
	public T pop () throws Exception 
	{
		if (isEmpty())
			throw new Exception("Nothing in the stack."); // in the event that there are no nodes in the stack, throw an exception.
		
		--size; 
		ListNode popedNode = topNode; //popedNode is a temporary node copied from topNode since we will be deleting topNode before return.
		topNode = topNode.nextNode;
		return popedNode.listData;
	}
	
	// Peek method
	// no args
	// does not alter the stack
	// returns the value, listData, held in the top node on the stack.
	public T peek() throws Exception  
	{
		if (isEmpty())
			throw new Exception("Nothing in the stack.");
		
		return topNode.listData;
	}
	
	// isEmpty method
	// no args
	// does not alter the stack.
	// Returns a boolean true/false for if there are nodes present in the stack.
	public boolean isEmpty() 
	{
		return size == 0;
	}
	
	// clear method.
	// no args
	// removes all nodes from stack.
	public void clear() 
	{
		topNode = null;	//simply setting the topNode to null and size to 0 means that the garbage collection process should automatically get rid of everything.
		size = 0;
	}
	
}
