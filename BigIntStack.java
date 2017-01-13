/* File: Stack.java
 * Date: 2/17/16
 * Author:  Megan Horan (mrhoran@bu.edu)
 * Class: CS 112, Spring 2016
 * Purpose: This is part of the code distribution for HW 04, Problem B.2; this class is an Abstract Data
 *      Type for a stack, a data structure in which you can push integers on one end, pop them
 *      of the same end, and check if the stack is empty.
 * 
 * Warning: No error checking is done for overflow or underflow!
 */

public class BigIntStack  { 
    
    // default size, can be changed by the constructor
    private int SIZE = 50;            
    
    private BigInt[] A = new BigInt[SIZE];
    
    private int next = 0;             // index of next available slot in the array
    
    // default constructor, nothing to do!
    // call it like this:
    //       IntStack S = new IntStack();
    //       S.push(3); 
    //       etc.
    
    public BigIntStack() {
    }
    
    // constructor to change size of the array
    // call it like this:
    //       IntStack S = new IntStack(50);         // now you have an array of size 50
    //       S.push(3); 
    //       etc.
    public BigIntStack(int n) {
        SIZE = n;
        A = new BigInt[SIZE];
    }
    
    // return the number of integers in the stack
    
    public int size() {
        return next; 
    }
    
    // put a new integer on the top of the stack
    // don't worry about overflow!
    
    public void push(BigInt n) {
        
        A[next] = n;
        ++next;
    }
    
    // remove and return the top element on the stack
    // don't worry about error from underflow!
    
    public BigInt pop() {
        --next;
        
        return A[next];
    }
    
    // return top element of stack WITHOUT removing it
    // don't worry about error from underflow!
    
    public BigInt top() {
        return A[next-1];
    }
    
    // just check if stack is empty
    
    public boolean isEmpty() {
        return (next == 0);
    }
    
    // This will return a String representation of the stack
    // Call it like this:
    //       IntStack S = new IntStack(); 
    //       System.out.println( S.toString() )
    // or--the preferred way--just use the name of the stack and Java will know to use toString():
    //       System.out.println( S );
    
    public String toString() {
        String s = "| ";
        for(int i = 0; i < next; ++i) {
            s += (A[i] + " ");
        }
        return s;    
    }
    
    // Unit Tests
    
    public static void main(String [] args) {
        
        System.out.println("\nUnit Test for IntStack Class");
        
        
        BigInt A = new BigInt("999999999999999999");
        BigInt B = new BigInt("44444444444444");
        BigInt C = new BigInt("11111111111111111111111");
        
        BigIntStack S = new BigIntStack(100);
        
        System.out.println("\nTest 1: -- Is Stack Empty: should be:");
        System.out.println("true");
        System.out.println( S.isEmpty() );

        //pushing A, B, C on the stack...
         S.push(A);
         S.push(B);
         S.push(C);

        
          System.out.println("\nTest 1: Should be (printed twice):\n| 999999999999999999 44444444444444 11111111111111111111111");
          System.out.println( S.toString() ); 
                  
          System.out.println("\nTest 2 -- Is Stack Empty: Should be:");
          System.out.println("false");
          System.out.println( S.isEmpty() );
          
          System.out.println("\nTest 2: Should be:\n");
          System.out.println("11111111111111111111111");
          System.out.println( S.top()); 
         
          
          System.out.println("\nTest 3: Should be:");
          System.out.println("| 999999999999999999  n = 11111111111111111111111  m = 44444444444444");
          
          // create string representation of the big ints from the pop
          String x = (S.pop().toString());
          String y = (S.pop().toString());
         
          System.out.println( S.toString() + " n = " + x + " m = "+ y); 
    }    
    
}
