
/*
 * Seth Sickels
 * Project #2 - Stack ADT
 * CS 1181 - Section 90 (online) - Professor Ondrasek -
 * 
 */

public class Main {

	public static void main(String[] args) throws Exception {
		
		Main obj = new Main ();
		obj.testArray();
		System.out.println("");
		obj.testLinkedList();

	}
	
	// testArray method is holds all code for testing the ArrayStack implementation.
	public void testArray() throws Exception
	{
		//create 2 instances of ArrayStack with types Integer and String.
		ArrayStack<Integer> intstack = new ArrayStack<Integer>(1);
		ArrayStack<String> stringstack = new ArrayStack<String>(1);
		
		// Begin series of tests of class methods to console prints.
		System.out.println("**Test of ArrayStack with Integers** ");
		System.out.printf("Int Empty Size: %d Capacity:  %d %n", intstack.size, intstack.stack.length);
		intstack.push(new Integer(10));
		System.out.printf("After Adding 1 to Int, Size: %d Capacity:  %d %n", intstack.size, intstack.stack.length);
		intstack.push(new Integer(4));
		intstack.push(new Integer(500));
		intstack.push(new Integer(2));
		System.out.printf("After Adding 3 to Int, Size: %d Capacity:  %d %n", intstack.size, intstack.stack.length);
		System.out.printf("Peek at top value: %d %n", intstack.peek());
		System.out.printf("Pop top value: %d %n", intstack.pop());
		System.out.printf("After pop, Size: %d Capacity:  %d %n", intstack.size, intstack.stack.length);
		System.out.printf("Is Stack Empty: %b %n", intstack.isEmpty());
		intstack.push(new Integer(4));
		System.out.printf("After Adding 1 to Int, Size: %d Capacity:  %d %n", intstack.size, intstack.stack.length);
		intstack.clear();
		System.out.printf("After clearing Int, Size: %d Capacity:  %d %n", intstack.size, intstack.stack.length);
		
		System.out.println("");
		System.out.println("**Test of ArrayStack with Strings** ");
		System.out.printf("Int Empty Size: %d Capacity:  %d %n", stringstack.size, stringstack.stack.length);
		stringstack.push(new String("rawrew"));
		System.out.printf("After Adding 1 to String, Size: %d Capacity:  %d %n", stringstack.size, stringstack.stack.length);
		stringstack.push(new String("rweqtasg"));
		stringstack.push(new String(" sdasf fsa33"));
		stringstack.push(new String("FFVI"));
		System.out.printf("After Adding 3 to String, Size: %d Capacity:  %d %n", stringstack.size, stringstack.stack.length);
		System.out.printf("Peek at top value: %s %n", stringstack.peek());
		System.out.printf("Pop top value: %s %n", stringstack.pop());
		System.out.printf("After pop, Size: %d Capacity:  %d %n", stringstack.size, stringstack.stack.length);
		System.out.printf("Is Stack Empty: %b %n", stringstack.isEmpty());
		stringstack.push(new String("sga"));
		System.out.printf("After Adding 1 to String, Size: %d Capacity:  %d %n", stringstack.size, stringstack.stack.length);
		stringstack.clear();
		System.out.printf("After clearing String, Size: %d Capacity:  %d %n", stringstack.size, stringstack.stack.length);
		
		
	}
	
	// testLinkedList holds all code to test the LinkedListStack implementation. It is basically the same as testArray.
	public void testLinkedList() throws Exception
	{
		//Create 2 new instances of LinkedListStack with types Integer and String.
		LinkedListStack<Integer> intstack = new LinkedListStack<Integer>();
		LinkedListStack<String> stringstack = new LinkedListStack<String>();
		
		// Begin series of tests of class methods to console prints.
		System.out.println("**Test of LinkedListStack with Integers** ");
		System.out.printf("Int Empty Size: %d %n", intstack.size);
		intstack.push(new Integer(10));
		System.out.printf("After Adding 1 to Int, Size: %d  %n", intstack.size);
		intstack.push(new Integer(4));
		intstack.push(new Integer(500));
		intstack.push(new Integer(2));
		System.out.printf("After Adding 3 to Int, Size: %d  %n", intstack.size);
		System.out.printf("Peek at top value: %d %n", intstack.peek());
		System.out.printf("Pop top value: %d %n", intstack.pop());
		System.out.printf("After pop, Size: %d  %n", intstack.size);
		System.out.printf("Is Stack Empty: %b %n", intstack.isEmpty());
		intstack.push(new Integer(4));
		System.out.printf("After Adding 1 to Int, Size: %d %n", intstack.size);
		intstack.clear();
		System.out.printf("After clearing Int, Size: %d %n", intstack.size);
		
		System.out.println("");
		System.out.println("**Test of LinkedListStack with Strings** ");
		System.out.printf("Int Empty Size: %d  %n", stringstack.size);
		stringstack.push(new String("rawrew"));
		System.out.printf("After Adding 1 to String, Size: %d %n", stringstack.size);
		stringstack.push(new String("rweqtasg"));
		stringstack.push(new String(" sdasf fsa33"));
		stringstack.push(new String("FFVI"));
		System.out.printf("After Adding 3 to String, Size: %d  %n", stringstack.size);
		System.out.printf("Peek at top value: %s %n", stringstack.peek());
		System.out.printf("Pop top value: %s %n", stringstack.pop());
		System.out.printf("After pop, Size: %d  %n", stringstack.size);
		System.out.printf("Is Stack Empty: %b %n", stringstack.isEmpty());
		stringstack.push(new String("sga"));
		System.out.printf("After Adding 1 to String, Size: %d %n", stringstack.size);
		stringstack.clear();
		System.out.printf("After clearing String, Size: %d %n", stringstack.size);
		
		
	}

}
